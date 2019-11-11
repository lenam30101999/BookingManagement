package com.example.test.demoapp.service;

import com.example.test.demoapp.controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class UserRun {
    private UserController controller = new UserController();
    private Scanner scanner = new Scanner(System.in);

    public void run() throws SQLException {
        int choice = 0;

        do {
            System.out.println("\n1.Book room");
            System.out.println("2.Delete booking");
            System.out.println("3.Show");
            System.out.println("4.Search");
            System.out.println("0.Exit");
            System.out.println("Choice:");
            choice = scanner.nextInt();

            switch (choice){
                case 1:{
                    controller.book();
                    break;
                }

                case 2:{
                    controller.checkOut();
                    break;
                }

                case 3:{
                    controller.show();
                    break;
                }

                case 4:{
                    controller.search();
                    break;
                }
                
                case 5:{
                    controller.suggest();
                    break;
                }
            }

        }while (choice != 0);
    }
}
