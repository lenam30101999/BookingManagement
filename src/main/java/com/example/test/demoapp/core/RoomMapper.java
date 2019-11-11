package com.example.test.demoapp.core;

import com.example.test.demoapp.object.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Room room = new Room();
        room.setId_Room(resultSet.getString("r_id"));
        room.setPrice_Room(resultSet.getLong("r_price"));
        room.setType_Room(resultSet.getString("r_type"));
        return room;
    }
}
