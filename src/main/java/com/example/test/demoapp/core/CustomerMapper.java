package com.example.test.demoapp.core;

import com.example.test.demoapp.object.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        Customer customer = new Customer();
        customer.setId(resultSet.getString("c_id"));
        customer.setName(resultSet.getString("c_name"));
        customer.setAddress(resultSet.getString("c_address"));
        customer.setAge(resultSet.getInt("c_age"));
        customer.setPhoneNumber(resultSet.getString("c_phone"));
        return customer;
    }
}
