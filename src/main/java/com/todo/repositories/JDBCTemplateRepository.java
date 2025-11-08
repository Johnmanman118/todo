package com.todo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("sqlserver")
public class JDBCTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired(required = false)
    public JDBCTemplateRepository(@Qualifier("sqlServerJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertData(int id, String name, String familyName, String address) {
        String sql = "INSERT INTO employees (id, name, familyName, address) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, name, familyName, address);
    }
}
