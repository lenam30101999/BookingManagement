package com.example.test.demoapp.dataSource;

import com.example.test.demoapp.core.BillMapper;
import com.example.test.demoapp.core.CustomerMapper;
import com.example.test.demoapp.core.EmployeeMapper;
import com.example.test.demoapp.core.RoomMapper;
import com.example.test.demoapp.object.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class Template {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    private List<Customer> listCustomer() {
        String query = "SELECT * FROM customer";
        SqlQuery<Customer> sqlQuery = new SqlQuery<Customer>() {
            @Override
            protected RowMapper<Customer> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new CustomerMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(query);
        List<Customer> lists = sqlQuery.execute();
        return lists;
    }

    public List<Room> listRoom() {
        String query = "SELECT * FROM room";
        SqlQuery<Room> sqlQuery = new SqlQuery<Room>() {
            @Override
            protected RowMapper<Room> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new RoomMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(query);
        List<Room> lists = sqlQuery.execute();
        return lists;
    }

    private List<Employee> listEmployee() {
        String SQL_Query = "SELECT * FROM employee";
        SqlQuery<Employee> sqlQuery = new SqlQuery<Employee>() {
            @Override
            protected RowMapper<Employee> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new EmployeeMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(SQL_Query);
        List<Employee> lists = sqlQuery.execute();
        return lists;
    }

    private String typeOfRoom(long price){
        if (price >= 2000000){
            return "VIP";
        }else {
            return "Normal";
        }
    }

    public void print(){
        System.out.println("-----LIST-----");
        System.out.println("List Room: ");
        for (Room room : this.listRoom()){
            if (room.getType_Room().equalsIgnoreCase("not")){
                System.out.printf("%-20s%-20d%-20s\n",room.getId_Room(),
                        room.getPrice_Room(), typeOfRoom(room.getPrice_Room()));
            }
        }

        System.out.println("List Employee: ");
        for (Employee employee : this.listEmployee()){
            System.out.printf("%-20s%-20s%-20d%-20d%-20s\n",employee.getId(),
                    employee.getName(),employee.getAge(),employee.getSalary(),
                    employee.getPhoneNumber());
        }

        System.out.println("List services: ");
        System.out.println("1.VIP       : 2500000");
        System.out.println("2.Manual    : 200000");
    }

    public void addRoom(Room room){
        String query = "INSERT INTO room VALUES(?,?,?)";
        jdbcTemplateObject.update(query, room.getId_Room(),
                room.getPrice_Room(), "not");
        System.out.println("Success!");
    }

    public void deleteRoom(String id) {
        String query = "DELETE FROM room WHERE r_id = ?";
        jdbcTemplateObject.update(query,id);
        System.out.println("Success!");
    }

    public Room getRoom(String id) {
        String query = "SELECT * FROM room WHERE r_id = ?";
        Room room = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new RoomMapper());
        return room;
    }

    public void addEmployee(Employee employee){
        String query = "INSERT INTO employee VALUES(?,?,?)";
        jdbcTemplateObject.update(query, employee.getId(), employee.getName(),
                employee.getAge(), employee.getSalary(), employee.getPhoneNumber());
        System.out.println("Success!");
    }

    public void insertBooking(Customer customer, Bill bill, String id_Employee,
                               String id_Room, String date){
        String query1 = "INSERT INTO customer VALUES(?,?,?,?,?)";
        String query2 = "INSERT INTO bill VALUES(?,?,?)";
        String query4 = "UPDATE room SET r_type = 'yes' WHERE (r_id = ?)";
        String query5 = "INSERT INTO booking VALUES(?,?,?,?,?)";


        jdbcTemplateObject.update(query1, customer.getId(), customer.getName(),
                customer.getAddress(), customer.getAge(), customer.getPhoneNumber());
        jdbcTemplateObject.update(query2, bill.getId_Bill(),
                bill.getMoney_Bill(), bill.getDate_Bill());
        jdbcTemplateObject.update(query4, id_Room);
        jdbcTemplateObject.update(query5, customer.getId(),
                id_Employee, bill.getId_Bill(), id_Room, date);
        System.out.println("Success!");
    }

    public void deleteBooking(String id,String id_Room) {
        String query3 = "DELETE FROM booking WHERE c_id = ?";
        String query1 = "DELETE FROM customer WHERE c_id = ?";
        String query2 = "DELETE FROM bill WHERE b_id = ?";
        String query4 = "UPDATE room SET r_type = 'not' WHERE r_id = ?";

        jdbcTemplateObject.update(query3, id);
        jdbcTemplateObject.update(query4, id_Room);
        jdbcTemplateObject.update(query2, id);
        jdbcTemplateObject.update(query1, id);
        System.out.println("Success!");
    }

    public Customer getCustomer(String id) {
        String query = "SELECT * FROM customer " +
                "WHERE c_id = ?";
        Customer customer = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new CustomerMapper());
        return customer;
    }

    public Bill getBill(String id) {
        String query = "SELECT * FROM bill " +
                "WHERE b_id = ?";
        Bill bill = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new BillMapper());
        return bill;
    }

    public List<Room> searchByPrice(long start, long end){
        return jdbcTemplateObject.query("SELECT * FROM room " +
                "WHERE (r_price >= ? AND r_price <= ? AND r_type = 'not')",
                new RoomMapper(),
                start, end);
    }

    public String findRoom(String id) {
        String query = "SELECT r_id FROM booking WHERE c_id = ?";
        String roomID = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, String.class);
        return roomID;
    }
}
