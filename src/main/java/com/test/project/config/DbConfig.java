package com.test.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.test.project")
@EnableTransactionManagement
public class DbConfig {

    @Value(value = "${dataBaseUrl}")
    private String dataBaseUrl;
    @Value(value = "${username1}")
    private String username;
    @Value(value = "${password}")
    private String password;
    @Value(value = "${driver}")
    private String driver;
    @Value(value = "${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value(value = "${hibernate.dialect}")
    private String hibernateDialect;
    @Value(value = "${show_sql}")
    private String showSql;
    @Value(value = "${hibernate.enable_lazy_load_no_trans}")
    private String hibernateLazyLoadNoTrans;
    @Value(value = "${hibernate.temp.use_jdbc_metadata_defaults}")
    private String hibernateTempUseJdbcMetadataDefaults;


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(dataBaseUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPackagesToScan("com.test.project.entity","com.test.project.security.model");
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setDataSource(getDataSource());
        return entityManagerFactoryBean;
    }
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory);
        return transactionManager;
    }


    private Properties getJpaProperties(){
        Properties properties= new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.setProperty("hibernate.dialect",hibernateDialect);
        properties.setProperty("show_sql",showSql);
        properties.setProperty("hibernate.enable_lazy_load_no_trans", hibernateLazyLoadNoTrans);
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults",hibernateTempUseJdbcMetadataDefaults);
        return properties;
    }

}
