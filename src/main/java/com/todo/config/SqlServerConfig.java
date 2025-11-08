package com.todo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("sqlserver")
public class SqlServerConfig {

    @Bean
    public DataSource sqlServerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=kyc;encrypt=true;trustServerCertificate=true");
        dataSource.setUsername("LAPTOP-Q6QQ0KJP\\me");
        dataSource.setPassword(""); // 🔐 Secure this in environment files
        return dataSource;
    }

    @Bean
    public JdbcTemplate sqlServerJdbcTemplate(@Qualifier("sqlServerDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
