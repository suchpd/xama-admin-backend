//package com.xama.backend.api.configurations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.web.client.RestTemplate;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@ComponentScan(basePackages ={"com.xama.backend.application.service","com.xama.backend.domain","com.xama.backend.dao","com.xama.backend.infrastructure"} )
//@EnableJpaRepositories(
//        basePackages = "com.xama.backend.dao.repository"
//)
//public class XamaBackendRepositoryConfig {
//    @Autowired
//    private Environment environment;
//
//    @Primary
//    @Bean(name = "xamaDataSource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "xamaEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.xama.backend.domain.entity");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.hibernate.dialect"));
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
//
//    @Primary
//    @Bean(name = "xamaTransactionMangerFactory")
//    public PlatformTransactionManager platformTransactionManager() {
//
//        JpaTransactionManager transactionManager  = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(localContainerEntityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
//        httpRequestFactory.setConnectTimeout(30 * 3000);
//        httpRequestFactory.setReadTimeout(30 * 3000);
//        restTemplate.setRequestFactory(httpRequestFactory);
//        return restTemplate;
//    }
//}
