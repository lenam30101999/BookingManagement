package com.example.test.demoapp.core;

import com.example.test.demoapp.object.Services;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesMapper implements RowMapper<Services> {
    @Override
    public Services mapRow(ResultSet resultSet, int i) throws SQLException {
        Services services = new Services();
        services.setId(resultSet.getString("ser_id"));
        services.setName(resultSet.getString("ser_name"));
        services.setPrice(resultSet.getLong("ser_price"));
        return services;
    }
}
