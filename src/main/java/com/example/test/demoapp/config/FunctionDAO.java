package com.example.test.demoapp.config;

import com.example.test.demoapp.dataSource.Template;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public interface FunctionDAO {
    
    public Template connect();

    public void show() throws SQLException;

    public void book() throws SQLException;

    public void checkOut();

    public void search();

    public void suggest();

}
