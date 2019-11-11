package com.example.test.demoapp.core;

import com.example.test.demoapp.object.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LogInMapper implements RowMapper<Account>{

    @Override
    public Account mapRow(ResultSet rs, int i) throws SQLException {
        Account logIn = new Account();
        logIn.setUsername(rs.getString("username"));
        logIn.setPassword(rs.getString("password"));
        return logIn;
    }
}
