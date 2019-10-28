package com.example.test.demoapp.core;

import com.example.test.demoapp.object.Bill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillMapper implements RowMapper<Bill> {
    public Bill mapRow(ResultSet resultSet, int rowNumber ) throws SQLException {
        Bill bill = new Bill();
        bill.setId_Bill(resultSet.getString("b_id"));
        bill.setMoney_Bill(resultSet.getLong("b_money"));
        bill.setDate_Bill(resultSet.getString("b_date"));
        return bill;
    }
}

