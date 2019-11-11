 package com.example.test.demoapp;


import com.example.test.demoapp.view.RunView;
import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        RunView runView = new RunView();
        runView.setVisible(true);
    }
}