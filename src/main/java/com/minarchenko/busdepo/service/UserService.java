package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> getUsers(DataSource dataSource) {
        List<User> users = new ArrayList<>();

        String sql = "SELECT id,user_name, login, password, user_role FROM users";

        try (Connection connection =dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("user_role")
                        );
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
//            log("SQL Exception: ", e);
        }
        return users;
    }


    public void addUser(String user_name, String login, String password, String user_role, DataSource dataSource) {
        String sql = "INSERT INTO users (user_name, login, password, user_role ) values(?,?,?,?)";

        try (Connection connection =dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user_name);
                statement.setString(2, login);
                statement.setString(3, password);
                statement.setString(4, user_role);
                statement.execute();
            }
        } catch (SQLException e) {
//            log("SQL Exception: ", e);
        }
    }
    public void userDelete(String user_id, DataSource dataSource) {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user_id);
                statement.execute();
            }
        } catch (SQLException e) {
//            log("SQL Exception: ", e);
        }
    }
}
