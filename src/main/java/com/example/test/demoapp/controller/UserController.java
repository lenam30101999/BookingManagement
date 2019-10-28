package com.example.test.demoapp.controller;

import com.example.test.demoapp.config.FunctionDAO;
import com.example.test.demoapp.dataSource.Template;
import com.example.test.demoapp.object.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserController implements FunctionDAO {

    private Scanner scanner = new Scanner(System.in);

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private Template jdbcTemplate = (Template) context.getBean("template");

    private Customer customer;
    private Bill bill;
    private Room room;
    private Employee employee;

    private void init(){
        customer = new Customer();
        bill = new Bill();
        room = new Room();
        employee = new Employee();
    }

    private long calculating(long price, int day, int choice){
        long totalMoney = 0;

        if (day >= 7){
            if (choice == 1){
                totalMoney = (day * price + 2500000) / 10;
            }else {
                totalMoney = (day * price + 200000) / 10;
            }
        }else {
            if (choice == 1){
                totalMoney = (day * price + 2500000);
            }else {
                totalMoney = (day * price + 200000);
            }
        }
        return totalMoney;
    }

    @Override
    public void book() {
        int day, service;
        String date, idRoom;
        Room room;
        init();

        jdbcTemplate.print();
        System.out.println("Today is (dd/mm/yyyy): ");
        date = scanner.nextLine();
        System.out.println("-----Booking-----");
        customer.input();
        System.out.println("Input room: ");
        idRoom = scanner.nextLine();
        System.out.println("Select services:");
        service = scanner.nextInt();
        System.out.println("How much days: ");
        day = scanner.nextInt();
        room = jdbcTemplate.getRoom(idRoom);
        bill.setId_Bill(customer.getId());
        bill.setMoney_Bill(calculating(
                room.getPrice_Room(),day,service));
        bill.setDate_Bill(date);

        System.out.println("Total money payment: " + bill.getMoney_Bill());
        jdbcTemplate.insertBooking(customer , bill, "01",
                idRoom, date);
    }

    @Override
    public void show() {
        jdbcTemplate.print();
    }

    @Override
    public void search() {
        long start, end;

        System.out.println("Start from: ");
        start = scanner.nextLong();
        System.out.println("To: ");
        end = scanner.nextLong();

        System.out.println("List: ");
        for (Room room : jdbcTemplate.searchByPrice(start,end)){
            System.out.printf("%-20s%-20d\n",room.getId_Room(),
                    room.getPrice_Room());
        }
    }

    @Override
    public void suggest() {

    }

    @Override
    public void checkOut() {
        String id;
        Bill bill;
        System.out.println("Customer id: ");
        scanner.nextLine();
        id = scanner.nextLine();
        bill = jdbcTemplate.getBill(id);
        System.out.println("Bill: " + bill.getMoney_Bill());

        jdbcTemplate.deleteBooking(id, jdbcTemplate.findRoom(id));
    }
}
