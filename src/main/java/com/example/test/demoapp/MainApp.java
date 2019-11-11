 package com.example.test.demoapp;


import com.example.test.demoapp.service.UserRun;
import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        UserRun user = new UserRun();
        user.run();
    }
}