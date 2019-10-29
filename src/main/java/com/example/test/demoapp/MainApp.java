package com.example.test.demoapp;

import com.example.test.demoapp.controller.UserController;
import com.example.test.demoapp.service.User;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        User user = new User();
        user.run();
    }
}
