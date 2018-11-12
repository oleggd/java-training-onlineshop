package com.study.onlineshop.dao.jdbc.mapper;

import com.study.onlineshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper {
    // id, name, role, creationDate
    public User mapRow(ResultSet resultSet) throws SQLException {
        User User = new User();

        User.setId(resultSet.getInt("id"));
        User.setName(resultSet.getString("name"));
        Timestamp creationDate = resultSet.getTimestamp("creation_date");
        User.setCreationDate(creationDate.toLocalDateTime());
        User.setRole(resultSet.getString("role"));

        return User;
    }
}
