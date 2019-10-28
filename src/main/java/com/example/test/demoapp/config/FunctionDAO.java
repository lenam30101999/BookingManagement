package com.example.test.demoapp.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public interface FunctionDAO {

    public void show();

    public void book();

    public void checkOut();

    public void search();

    public void suggest();

}
