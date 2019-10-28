package com.example.test.demoapp.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Employee extends People{

    @Column(name = "e_salary")
    private long salary;

    @Column(name = "e_phone")
    private String phoneNumber;

    public Employee(){

    }

    public Employee(String id, String name, int age, long salary, String phoneNumber) {
        super(id, name, age);
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
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
        System.out.println("Phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.println("Salary: ");
        salary = scanner.nextLong();
        System.out.println("Age: ");
        age = scanner.nextInt();
        scanner.nextLine();
    }
}
