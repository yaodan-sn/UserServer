package com.example.user.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.incrementer.AbstractColumnMaxValueIncrementer;
import org.springframework.stereotype.Service;

/**
 * 基于数据库表的业务主键自增生成器
 * 必要配置:
 * dataSource数据源
 * incrementerTableName 表名称 eg:incrementer.table.name=seq_config
 * incrementerColumnName 自增业务主键存放的字段名   eg: incrementer.column.name=code
 * cacheSize 默认1000
 * 
 * table eg:
 * create table seq_config (
 *  optimistic           int(11) default 0 comment '乐观锁',
 *  code                 varchar(50) comment '唯一编码',
 *  value                varchar(200) default NULL comment '参数值',
 *  primary key (code)
 * )
 * @author dan
 *
 */
@Service
public class IncrementerService implements InitializingBean {

	@Resource
	private DataSource dataSource;

	/** The name of the sequence/table containing the sequence */
	@Value("${incrementer.table.name}")
	private String incrementerTableName;

	/** The name of the sequence/key containing the sequence */
	@Value("${incrementer.column.name}")
	private String incrementerColumnName;

	/** Whether or not to use a new connection for the incrementer */
	private boolean useNewConnection = true;

	/**
	 * 默认缓存大小
	 */
	private int cacheSize = 1000;

	private final Map<String, SQLMaxValueIncrementer> incrementers = new HashMap<String, SQLMaxValueIncrementer>();

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.incrementerTableName == null) {
			throw new IllegalArgumentException("Property 'incrementerTableName' is required");
		}
		if (this.incrementerColumnName == null) {
			throw new IllegalArgumentException("Property 'incrementerColumnName' is required");
		}
	}

	public SQLMaxValueIncrementer init(DataSource dataSource, String incrementerName,
			String columnName, String keyName, int cacheSize, boolean useNewConnection) {
		synchronized (keyName.intern()) {
			SQLMaxValueIncrementer inc = incrementers.get(keyName);
			if (inc != null) {
				return inc;
			}
			inc = new SQLMaxValueIncrementer();
			inc.setDataSource(dataSource);
			inc.setIncrementerName(incrementerName);
			inc.setColumnName(columnName);
			inc.setColumnValue(keyName);
			inc.setCacheSize(cacheSize);
			inc.setUseNewConnection(useNewConnection);
			incrementers.put(keyName, inc);
			return inc;
		}
	}

	public long nextLongValue(String keyName) {
		SQLMaxValueIncrementer inc = incrementers.get(keyName);
		if (inc == null) {
			inc = init(dataSource, incrementerTableName, incrementerColumnName, keyName, cacheSize,
					useNewConnection);
		}
		return inc.nextLongValue();
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getIncrementerTableName() {
		return incrementerTableName;
	}

	public void setIncrementerTableName(String incrementerTableName) {
		this.incrementerTableName = incrementerTableName;
	}

	public String getIncrementerColumnName() {
		return incrementerColumnName;
	}

	public void setIncrementerColumnName(String incrementerColumnName) {
		this.incrementerColumnName = incrementerColumnName;
	}

	public boolean isUseNewConnection() {
		return useNewConnection;
	}

	public void setUseNewConnection(boolean useNewConnection) {
		this.useNewConnection = useNewConnection;
	}

	class SQLMaxValueIncrementer extends AbstractColumnMaxValueIncrementer {

		private static final String SELECT_SQL = "select optimistic, value from %s where %s='%s'";
		private static final String UPDATE_SQL = "update %s set optimistic=optimistic+1, value=%s where %s='%s' and optimistic=%s";

		/** The value of the column for this sequence name */
		private String columnValue;

		/** Whether or not to use a new connection for the incrementer */
		private boolean useNewConnection = false;

		/** The next id to serve */
		private long nextId = 0;

		/** The max id to serve */
		private long maxId = 0;

		@Override
		public void afterPropertiesSet() {
			super.afterPropertiesSet();
			if (this.columnValue == null) {
				throw new IllegalArgumentException("Property 'columnValue' is required");
			}
		}

		public SQLMaxValueIncrementer() {
		}

		public SQLMaxValueIncrementer(DataSource dataSource, String incrementerName,
				String columnName) {
			super(dataSource, incrementerName, columnName);
		}

		@Override
		protected synchronized long getNextKey() throws DataAccessException {
			if (this.maxId == this.nextId) {
				/*
				 * If useNewConnection is true, then we obtain a non-managed
				 * connection so our modifications are handled in a separate
				 * transaction. If it is false, then we use the current
				 * transaction's connection relying on the use of a
				 * non-transactional storage engine like MYISAM for the
				 * incrementer table. We also use straight JDBC code because we
				 * need to make sure that the insert and select are performed on
				 * the same connection (otherwise we can't be sure that
				 * last_insert_id() returned the correct value).
				 */
				Connection con = null;
				Statement stmt = null;
				boolean mustRestoreAutoCommit = false;
				try {
					if (this.useNewConnection) {
						con = getDataSource().getConnection();
						if (con.getAutoCommit()) {
							mustRestoreAutoCommit = true;
							con.setAutoCommit(false);
						}
					} else {
						con = DataSourceUtils.getConnection(getDataSource());
					}
					stmt = con.createStatement();

					if (!this.useNewConnection) {
						DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
					}
					// Increment the sequence column...
					String columnName = getColumnName();

					ResultSet qrs = stmt.executeQuery(String.format(SELECT_SQL,
							getIncrementerName(), getColumnName(), this.columnValue));
					long optimistic;
					long max;
					try {
						if (!qrs.next()) {
							throw new DataAccessResourceFailureException(
									"select optimistic, value failed after executing an update");
						}
						optimistic = qrs.getLong(1);
						max = qrs.getLong(2);
					} finally {
						JdbcUtils.closeResultSet(qrs);
					}

					try {

						int executeUpdate = stmt.executeUpdate(String.format(UPDATE_SQL,
								getIncrementerName(), (max + getCacheSize()), getColumnName(),
								this.columnValue, optimistic));

						if (executeUpdate != 1) {
							throw new DataAccessResourceFailureException("Could not increment "
									+ columnName + " for " + getIncrementerName()
									+ " sequence table, reason: executeUpdate=" + executeUpdate);
						}
					} catch (SQLException ex) {
						throw new DataAccessResourceFailureException("Could not increment "
								+ columnName + " for " + getIncrementerName() + " sequence table",
								ex);
					}
					this.maxId = max + getCacheSize();
					this.nextId = this.maxId - getCacheSize() + 1;
				} catch (SQLException ex) {
					throw new DataAccessResourceFailureException("Could not increment", ex);
				} finally {
					JdbcUtils.closeStatement(stmt);
					if (con != null) {
						if (this.useNewConnection) {
							try {
								con.commit();
								if (mustRestoreAutoCommit) {
									con.setAutoCommit(true);
								}
							} catch (SQLException ignore) {
								throw new DataAccessResourceFailureException(
										"Unable to commit new sequence value changes for "
												+ getIncrementerName());
							}
							JdbcUtils.closeConnection(con);
						} else {
							DataSourceUtils.releaseConnection(con, getDataSource());
						}
					}
				}
			} else {
				this.nextId++;
			}
			return this.nextId;
		}

		public String getColumnValue() {
			return columnValue;
		}

		public void setColumnValue(String columnValue) {
			this.columnValue = columnValue;
		}

		public boolean isUseNewConnection() {
			return useNewConnection;
		}

		public void setUseNewConnection(boolean useNewConnection) {
			this.useNewConnection = useNewConnection;
		}

	}
}
