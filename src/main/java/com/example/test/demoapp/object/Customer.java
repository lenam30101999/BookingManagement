package com.example.test.demoapp.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends People{

    @Column(name = "c_address")
    private String address;

    @Column(name = "c_phone")
    private String phoneNumber;

    public Customer(){

    }

    public String getAddress() {
        return address;
    }

    public Customer(String id, String name, int age, String address, String phoneNumber) {
        super(id, name, age);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void input(){
        System.out.println("ID: ");
        id = scanner.nextLine();
        System.out.println("Name: ");
        name = scanner.nextLine();
        System.out.println("Age: ");
        age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Address: ");
        address = scanner.nextLine();
        System.out.println("Phone number: ");
        phoneNumber = scanner.nextLine();
    }
}
