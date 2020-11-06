package com.example.demo.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//설정을 위한 클래스 Bean이 만들어진다 각각의 클래스 이름으로
@Configuration
@EnableTransactionManagement
public class PostgreConfig {

	private static final Logger logger = LoggerFactory.getLogger(PostgreConfig.class);
	
	@Value("${spring.profiles.active:local}")
	private String activeProfile;
	
	@Value("${spring.datasource.hikari.jdbc-url:dummy}")
	private String appJdbcUrl;
	
	@Value("${spring.datasource.hikari.username:dummyuser}")
	private String appJdbcUser;
	
	@Value("${spring.datasource.hikari.password:dummypswd}")
	private String appJdbcPswd;
	
	@Value("${spring.datasource.hikari.driver-class-name:dummy}")
	private String appJdbcDriverClassName;
	
	@Value("${spring.datasource.jndi-name:none}")
	private String poolJndiName;
	
	@Value("${appl.path.sqlmap}")
	private String sqlMapResourcesPath;
	
	
	@Bean(name = "applicationDataSource")
	public DataSource applicationDataSource() throws URISyntaxException {
		DataSource ds = null;
		
		logger.debug("Set datasource using jdbc-url : {}", appJdbcUrl);
		DataSourceBuilder<?> builder = DataSourceBuilder.create();
		builder.driverClassName(appJdbcDriverClassName);  // org.postgresql.Driver
		builder.username(appJdbcUser);
		builder.password(appJdbcPswd);
		builder.url(appJdbcUrl);
		
		ds = builder.build();
		return ds;
	}
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory applicationSessionFactory(@Qualifier("applicationDataSource")DataSource applicationDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		sqlSessionFactoryBean.setDataSource(applicationDataSource);
		sqlSessionFactoryBean.setMapperLocations(
				applicationContext.getResources(sqlMapResourcesPath)
				);
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);	
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate applSqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory applicationSessionFactory) throws Exception {
		return new SqlSessionTemplate(applicationSessionFactory);
	}
	
	

}
