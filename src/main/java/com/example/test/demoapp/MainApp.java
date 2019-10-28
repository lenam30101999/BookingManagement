package com.example.test.demoapp;

import com.example.test.demoapp.controller.UserController;
import com.example.test.demoapp.service.User;

public class MainApp {
    public static void main(String[] args) {
        User user = new User();
        user.run();
    }
}
