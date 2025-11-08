package com.todo.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;

@Configuration
@EntityScan(basePackages = "com.todo.entities")
@Profile("mysql")
@EnableJpaRepositories(
        basePackages = "com.todo.repositories",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
public class MysqlJpaConfig {

//    @Primary
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource mysqlDataSource() {
//        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
//    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")  // bind all spring.datasource.* properties
    public HikariDataSource mysqlDataSource(@Autowired DataSourceProperties mysqlDataSourceProperties) {
        // Use DataSourceProperties to create the DataSource (handles URL vs. JDBC URL internally)
        return mysqlDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)  // explicitly use HikariCP
                .build();
    }

    @Primary
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            @Qualifier("mysqlDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.todo.entities");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        emf.setJpaPropertyMap(properties);

        return emf;
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
        assert mysqlEntityManagerFactory.getObject() != null;
        return new JpaTransactionManager(mysqlEntityManagerFactory.getObject());
    }

    @Bean
    public JpaTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
