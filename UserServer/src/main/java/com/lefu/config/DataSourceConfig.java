package com.lefu.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author dan
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceConfig {

	@Value("${db.driver}")
	private String dbDriver;
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	@Value("${db.max.active}")
	private int dbMaxActive;
	
	@Value("${db.filters}")
	private String dbFilters;
	
	@Value("${db.publickey}")
	private String dbPublickey;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws SQLException {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(dbDriver);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUsername);
		ds.setPassword(dbPassword);
		ds.setMaxActive(dbMaxActive);
		ds.setFilters(dbFilters);
		ds.setConnectionProperties("config.decrypt=true;config.decrypt.key=" + dbPublickey);
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}