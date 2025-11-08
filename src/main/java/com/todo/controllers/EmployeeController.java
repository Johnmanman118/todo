package com.todo.controllers;

import com.todo.entities.Employee;
import com.todo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jdbc")
public class EmployeeController    {

    @Autowired(required = false)
    EmployeeService employeeService;

    @PostMapping("/create")
    public String addNewEmployee (@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee.toString() + " successfully created";
    }
}
