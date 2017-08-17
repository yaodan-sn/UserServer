package com.example.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = { "com.lefu.**.dao" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		org.springframework.core.io.Resource[] resources = resolver
				.getResources("classpath*:com/lefu/**/dao/**/*.xml");
		
		sqlSessionFactoryBean.setMapperLocations(resources);
		return sqlSessionFactoryBean.getObject();
	}
}
