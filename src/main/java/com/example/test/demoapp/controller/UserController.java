package com.example.test.demoapp.controller;

import com.example.test.demoapp.config.FunctionDAO;
import com.example.test.demoapp.dataSource.Template;
import com.example.test.demoapp.object.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class UserController implements FunctionDAO {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private final Template jdbcTemplate = (Template) context.getBean("template");

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

    @Override
    public void book(Customer customer, Bill bill, ArrayList<String> idRoom) throws SQLException {
        init();
        jdbcTemplate.insertBooking(customer , bill, bill.getEmployee_Bill(),
                idRoom, bill.getDate_Bill());
    }

    @Override
    public void search(long start, long end) {
        for (Room room : jdbcTemplate.searchByPrice(start,end)){
            System.out.printf("%-20s%-20d\n",room.getId_Room(),
                    room.getPrice_Room());
        }
    }

    @Override
    public void update(String sql, String id, long price) {
        
    }

    

    @Override
    public void checkOut(String id) {
        
    }

    @Override
    public Template connect() {
        return jdbcTemplate;
    }
}
