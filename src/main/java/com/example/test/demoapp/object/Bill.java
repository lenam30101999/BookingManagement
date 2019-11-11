package com.example.test.demoapp.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Scanner;

@Entity
@Table(name = "bill")
public class Bill {
    Scanner scanner = new Scanner(System.in);

    @Id
    private String id_Bill;

    @Column(name = "b_money")
    private long money_Bill;

    @Column(name = "b_date")
    private String date_Bill;
    
    private String employee_Bill;
    
    public Bill(){

    }

    public Bill(String id_Bill, long money_Bill, String date_Bill, String employee_Bill) {
        this.id_Bill = id_Bill;
        this.money_Bill = money_Bill;
        this.date_Bill = date_Bill;
        this.employee_Bill = employee_Bill;
    }

    public String getId_Bill() {
        return id_Bill;
    }

    public void setId_Bill(String id_Bill) {
        this.id_Bill = id_Bill;
    }

    public long getMoney_Bill() {
        return money_Bill;
    }

    public void setMoney_Bill(long money_Bill) {
        this.money_Bill = money_Bill;
    }

    public String getDate_Bill() {
        return date_Bill;
    }

    public void setDate_Bill(String date_Bill) {
        this.date_Bill = date_Bill;
    }

    public String getEmployee_Bill() {
        return employee_Bill;
    }

    public void setEmployee_Bill(String employee_Bill) {
        this.employee_Bill = employee_Bill;
    }

}
