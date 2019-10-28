package com.example.test.demoapp.core;

import com.example.test.demoapp.object.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getString("e_id"));
        employee.setName(resultSet.getString("e_name"));
        employee.setAge(resultSet.getInt("e_age"));
        employee.setSalary(resultSet.getLong("e_salary"));
        employee.setPhoneNumber(resultSet.getString("e_phone"));
        return employee;
    }
}
