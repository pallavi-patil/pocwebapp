package com.capgemini.pocwebapp.spring.config.hibernate;

import static org.hibernate.cfg.AvailableSettings.C3P0_ACQUIRE_INCREMENT;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;
import static org.hibernate.cfg.AvailableSettings.C3P0_MIN_SIZE;
import static org.hibernate.cfg.AvailableSettings.C3P0_TIMEOUT;
import static org.hibernate.cfg.AvailableSettings.CACHE_REGION_FACTORY;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;
import static org.hibernate.cfg.AvailableSettings.USE_SECOND_LEVEL_CACHE;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.capgemini.pocwebapp.constants.IApplicationConstants;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.capgemini.pocwebapp.spring.config.hibernate" })
@PropertySource("classpath:/config/database/myappdb_${config}.properties")
public class HibernateConfig {

	@Autowired
	private Environment env;
	
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.capgemini.pocwebapp.dao.entity" });
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    
    private Properties getHibernateProperties() {
    	Properties props = new Properties();

		// Setting JDBC properties
		/*props.put(DRIVER, env.getProperty("jdbc.driverClassName"));
		props.put(URL, env.getProperty("jdbc.url"));
		props.put(USER, env.getProperty("jdbc.username"));
		props.put(PASS, env.getProperty("jdbc.password"));*/

		// Setting Hibernate properties
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		// props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

		// Setting C3P0 properties
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		props.put(USE_SECOND_LEVEL_CACHE, env.getProperty(IApplicationConstants.HBT_USE_2ND_CACHE));
		props.put(CACHE_REGION_FACTORY, env.getProperty(IApplicationConstants.HBT_CACHE_RGN_FACTORY_CLS));
		props.put(IApplicationConstants.HBT_CACHE_PROVIDER, env.getProperty(IApplicationConstants.HBT_CACHE_PROVIDER));
        return props;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager getTransactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
	
	/**
	 * DEVELOPER PROFILE (activate with: spring.profiles.active=developer)
	 */
	/*
	 * @Configuration
	 * 
	 * @Profile("developer")
	 * 
	 * @PropertySources({
	 * 
	 * @PropertySource(value = "classpath:META-INF/config/mts-default.properties",
	 * ignoreResourceNotFound = false),
	 * 
	 * @PropertySource(value = "classpath:mts-env.properties",
	 * ignoreResourceNotFound = true),
	 * 
	 * @PropertySource(value =
	 * "classpath:META-INF/config/local/mts-local.properties",
	 * ignoreResourceNotFound = true),}) static class DeveloperProps { }
	 */
}

