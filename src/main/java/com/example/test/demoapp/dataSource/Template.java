package com.example.test.demoapp.dataSource;

import com.example.test.demoapp.core.*;
import com.example.test.demoapp.object.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    private void createTable(){
        String queryCreateBill = "create table if not exists bill(" +
                "b_id   VARCHAR(50) NOT NULL PRIMARY KEY," +
                "b_money    LONG    NOT NULL," +
                "b_date VARCHAR(20) NOT NULL);";

        String queryCreateCustomer = "create table if not exists customer(" +
                "c_id VARCHAR(50) NOT NULL PRIMARY KEY," +
                "c_name VARCHAR(50) NOT NULL," +
                "c_address VARCHAR(50) NOT NULL," +
                "c_age INTEGER NOT NULL," +
                "c_phone VARCHAR(15) NOT NULL);";

        String queryCreateEmployee = "create table if not exists employee(" +
                "e_id VARCHAR(50) NOT NULL PRIMARY KEY," +
                "e_name VARCHAR(50) NOT NULL," +
                "e_age INTEGER NOT NULL," +
                "e_salary LONG NOT NULL," +
                "e_phone VARCHAR(15) NOT NULL);";

        String queryCreateRoom = "create table if not exists room(" +
                "r_id   VARCHAR(50) NOT NULL PRIMARY KEY," +
                "r_price    LONG    NOT NULL," +
                "r_type VARCHAR(20) NOT NULL);";

        String queryCreateServices = "create table if not exists services(" +
                "ser_id   VARCHAR(50) NOT NULL PRIMARY KEY," +
                "ser_name    VARCHAR(35)    NOT NULL," +
                "ser_price LONG NOT NULL);";

        String queryCreateBooking = "create table if not exists booking(" +
                "c_id   VARCHAR(50) NOT NULL," +
                "e_id   VARCHAR(50) NOT NULL," +
                "b_id   VARCHAR(50) NOT NULL," +
                "r_id   VARCHAR(50) NOT NULL," +
                "bk_date  VARCHAR(25)   NOT NULL," +
                "bk_quantity    INTEGER NOT NULL," +
                "foreign key (b_id) references bill(b_id)," +
                "foreign key (c_id) references customer(c_id)," +
                "foreign key (e_id) references employee(e_id)," +
                "foreign key (r_id) references room(r_id));";
        
        String queryCreateLogin = "create table if not exist login(" +
"                   username	VARCHAR(15)	NOT NULL	PRIMARY KEY," +
"                   password	VARCHAR(15)     NOT NULL);";

        jdbcTemplateObject.update(queryCreateBill);
        jdbcTemplateObject.update(queryCreateEmployee);
        jdbcTemplateObject.update(queryCreateCustomer);
        jdbcTemplateObject.update(queryCreateRoom);
        jdbcTemplateObject.update(queryCreateServices);
        jdbcTemplateObject.update(queryCreateBooking);
        jdbcTemplateObject.update(queryCreateLogin);
    }
    
    public List<Customer> listCustomer() throws SQLException {
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

    public List<Room> listRoom() throws SQLException{
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

    public List<Employee> listEmployee() throws SQLException{
        String query = "SELECT * FROM employee";
        SqlQuery<Employee> sqlQuery = new SqlQuery<Employee>() {
            @Override
            protected RowMapper<Employee> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new EmployeeMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(query);
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

    public void addServices(Services services){
        String query = "INSERT INTO services VALUES(?,?,?)";
        jdbcTemplateObject.update(query, services.getId(),
                services.getName(), services.getPrice());
    }
    
    public void deleteServices(String id) throws SQLException{
        String query = "DELETE FROM services WHERE ser_id = ?";
        jdbcTemplateObject.update(query,id);
    }
    
    public List<Services> listServices(){
        String query = "SELECT * FROM services";
        SqlQuery<Services> sqlQuery = new SqlQuery<Services>() {
            @Override
            protected RowMapper<Services> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new ServicesMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(query);
        List<Services> lists = sqlQuery.execute();
        return lists;
    }

    public Services getServices(String id) throws SQLException{
        String query = "SELECT * FROM services WHERE ser_id = ?";
        Services services = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new ServicesMapper());
        return services;
    }

    public void addRoom(Room room) throws SQLException{
        String query = "INSERT INTO room VALUES(?,?,?)";
        jdbcTemplateObject.update(query, room.getId_Room(),
                room.getPrice_Room(), "not");
    }
    
    public void print() throws SQLException{
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
        for (Services services : this.listServices()){
            System.out.printf("%-20s%-20s%-20d\n",services.getId(),
                    services.getName(), services.getPrice());
        }
    }
    
    public void deleteRoom(String id) throws SQLException{
        String query = "DELETE FROM room WHERE r_id = ?";
        jdbcTemplateObject.update(query,id);
    }

    public Room getRoom(String id) throws SQLException{
        String query = "SELECT * FROM room WHERE r_id = ?";
        Room room = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new RoomMapper());
        return room;
    }
    
    public void deleteEmployee(String id) throws SQLException{
        String query = "DELETE FROM employee WHERE e_id = ?";
        jdbcTemplateObject.update(query,id);
    }
    
    public void addEmployee(Employee employee) throws SQLException{
        String query = "INSERT INTO employee VALUES(?,?,?)";
        jdbcTemplateObject.update(query, employee.getId(), employee.getName(),
                employee.getAge(), employee.getSalary(), employee.getPhoneNumber());
    }

    public void insertBooking (Customer customer, Bill bill, String id_Employee,
                               ArrayList<String> id_Room, String date){
        String query1 = "INSERT IGNORE INTO customer VALUES(?,?,?,?,?)";
        String query2 = "INSERT INTO bill VALUES(?,?,?,?)";
        String query4 = "UPDATE room SET r_type = 'yes' WHERE (r_id = ?)";
        String query5 = "INSERT INTO booking VALUES(?,?,?,?,?,?)";


        jdbcTemplateObject.update(query1, customer.getId(), customer.getName(),
                customer.getAddress(), customer.getAge(), customer.getPhoneNumber());
        jdbcTemplateObject.update(query2, bill.getId_Bill(),
                bill.getMoney_Bill(), bill.getDate_Bill(), "N10");
        
        for(String id : id_Room){
            jdbcTemplateObject.update(query4, id);
            jdbcTemplateObject.update(query5, customer.getId(),
                "N10", bill.getId_Bill(), id, date, id_Room.size());
        }
  
    }

    public void deleteBooking (String id,String id_Room) {
        String query3 = "DELETE FROM booking WHERE c_id = ?";
        String query2 = "DELETE FROM bill WHERE b_id = ?";
        String query4 = "UPDATE room SET r_type = 'not' WHERE r_id = ?";

        jdbcTemplateObject.update(query3, id);
        jdbcTemplateObject.update(query4, id_Room);
        jdbcTemplateObject.update(query2, id);
    }

    public Customer getCustomer(String id) {
        String query = "SELECT * FROM customer " +
                "WHERE c_id = ?";
        Customer customer = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new CustomerMapper());
        return customer;
    }

    public Bill getBill (String id) {
        String query = "SELECT * FROM bill " +
                "WHERE b_id = ?";
        Bill bill = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, new BillMapper());
        return bill;
    }
    
    public List<Bill> listBills() throws SQLException {
        String query = "SELECT * FROM bill";
        SqlQuery<Bill> sqlQuery = new SqlQuery<Bill>() {
            @Override
            protected RowMapper<Bill> newRowMapper(Object[] objects, Map<?, ?> map) {
                return new BillMapper();
            }
        };
        sqlQuery.setDataSource(dataSource);
        sqlQuery.setSql(query);
        List<Bill> lists = sqlQuery.execute();
        return lists;
    }

    public List<Room> searchByPrice(long start, long end){
        return jdbcTemplateObject.query("SELECT * FROM room " +
                "WHERE (r_price >= ? AND r_price <= ? AND r_type = 'not')",
                new RoomMapper(),
                start, end);
    }

    public String findRoom (String id) {
        String query = "SELECT r_id FROM booking WHERE c_id = ?";
        String roomID = jdbcTemplateObject.queryForObject(query,
                new Object[] {id}, String.class);
        return roomID;
    }
    
    public String findPassword (String username) {
        String query = "SELECT password FROM login WHERE username = ?";
        String password = jdbcTemplateObject.queryForObject(query,
                new Object[] {username}, String.class);
        return password;
    }
}
