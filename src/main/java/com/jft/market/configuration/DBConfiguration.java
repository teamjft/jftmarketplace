package com.jft.market.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DBConfiguration {

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}

	@Bean("dataSource")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		dataSource.setUrl("jdbc:mysql://localhost:3306/market");
		return dataSource;
	}

	@Bean("sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setHibernateProperties(getHibernateProperties());
		bean.setPackagesToScan("com.jft.market.model");
		return bean;
	}

	@Bean(name = "hibernateTemplate")
	@DependsOn("sessionFactory")
	public HibernateTemplate getHibernateTemplate() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		hibernateTemplate.setSessionFactory(sessionFactory().getObject());
		return hibernateTemplate;
	}

	@Bean(name = "transactionManager")
	@DependsOn("sessionFactory")
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean("transactionTemplate")
	public TransactionTemplate transactionTemplate() throws PropertyVetoException {
		return new TransactionTemplate(transactionManager());
	}

	@Bean("session")
	@DependsOn("sessionFactory")
	public Session getSession() {
		return sessionFactory().getObject().openSession();
	}
}
