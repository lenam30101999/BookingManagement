package com.example.test.demoapp.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Scanner;

@Entity
@Table(name = "room")
public class Room {
    Scanner scanner = new Scanner(System.in);

    @Id
    private String id_Room;

    @Column(name = "r_price")
    private long price_Room;

    @Column(name = "r_type")
    private String type_Room;

    public Room(){

    }

    public Room(String id_Room, long price_Room, String type_Room) {
        this.id_Room = id_Room;
        this.price_Room = price_Room;
        this.type_Room = type_Room;
    }

    public String getId_Room() {
        return id_Room;
    }

    public void setId_Room(String id_Room) {
        this.id_Room = id_Room;
    }

    public long getPrice_Room() {
        return price_Room;
    }

    public void setPrice_Room(long price_Room) {
        this.price_Room = price_Room;
    }

    public String getType_Room() {
        return type_Room;
    }

    public void setType_Room(String type_Room) {
        this.type_Room = type_Room;
    }

    public void input(){
        int choice = 0;
        System.out.println("Enter ID Room: ");
        id_Room = scanner.nextLine();

        System.out.println("Enter price room: ");
        price_Room = scanner.nextLong();

        System.out.println("----Type Room---- ");
        System.out.println("1.VIP");
        System.out.println("2.MANUAL");
        System.out.println("0.Exit");
        System.out.println("Enter choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:{
                type_Room = "VIP";
                break;
            }
            case 2:{
                type_Room = "MANUAL";
                break;
            }
        }
    }
}
