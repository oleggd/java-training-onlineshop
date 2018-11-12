package com.study.onlineshop.service.impl;

import com.study.onlineshop.dao.UserDao;
import com.study.onlineshop.entity.User;
import com.study.onlineshop.service.UserService;

import java.sql.*;

public class DefaultUserService implements UserService {

    private User user;
    private UserDao userDao;

    private static final String GET_LOGIN_SQL     = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END is_authorized FROM users WHERE name = ? AND password = ?";
    private static final String GET_PERMISSION_SQL = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END is_allowed FROM permissions WHERE role = ? AND object = ?;";

    @Override //"SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END is_authorized FROM user WHERE user = ? AND password = ?";
    public boolean isAuthenticated(String login, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LOGIN_SQL);
        ) {
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("is_authorized").equals("Y")) {
                    user = userDao.getUser(login);
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override //"SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END IS_ALLOWED FROM PERMISSIONS WHERE (ROLE = ? OR ROLE = '%') AND OBJECT = ?;";
    public boolean isAuthorized(String role, String object) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PERMISSION_SQL);
        ) {
            statement.setString(1, role);
            statement.setString(2, object);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("is_allowed").equals("Y")) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/db2_onlineshop";
        String name = "postgres";
        String password = "1234";

        return DriverManager.getConnection(url, name, password);
    }
}
