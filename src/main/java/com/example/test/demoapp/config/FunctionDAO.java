package com.example.test.demoapp.config;

import com.example.test.demoapp.dataSource.Template;
import com.example.test.demoapp.object.Bill;
import com.example.test.demoapp.object.Customer;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;

@Configuration
public interface FunctionDAO {
    
    public Template connect();

    public void show() throws SQLException;

    public void book(Customer customer, Bill bill, ArrayList<String> idRoom) throws SQLException;

    public void checkOut(String id);

    public void search(long start, long end);

    public void suggest();

}
