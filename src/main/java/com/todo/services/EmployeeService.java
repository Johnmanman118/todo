package com.todo.services;

import com.todo.entities.Employee;
import com.todo.repositories.JDBCTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("sqlserver")
@Service
public class EmployeeService {
    @Autowired(required = false)
    private JDBCTemplateRepository jdbcTemplateRepository;

    public void saveEmployee(Employee employee) {
        jdbcTemplateRepository.insertData(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getAddress());

    }

}
