package com.example.test.demoapp.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Scanner;

@Entity
public class People {
    Scanner scanner = new Scanner(System.in);

    @Id
    protected String id;

    @Column(name = "name")
    protected String name;

    @Column(name = "age")
    protected int age;

    public People(){

    }

    public People(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
