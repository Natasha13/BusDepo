package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.User;
import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final int PAGE_SIZE = 2;
    private static Logger logger = LoggerFactory.getLogger(User.class);

    public List<User> getUsers(DataSource dataSource, Integer page) {
        logger.debug("get all Users for page {}", page);

        List<User> users = new ArrayList<>();
        Integer offset = (page - 1) * PAGE_SIZE;

        String sql = "SELECT id,user_name, login, password, user_role FROM users LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, PAGE_SIZE);
                statement.setInt(2, offset);
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
            logger.error("SQL error in getUsers", e);
        }
        return users;
    }


    public void addUser(String user_name, String login, String password, String user_role, DataSource dataSource) {
        logger.info("Created new User record. User_name : {}, Login : {}, " +
                        "Password : {}, User_role {}",
                user_name, login, password, user_role);

        MessageDigestCredentialHandler passwordHandler = new MessageDigestCredentialHandler();
        try {
            passwordHandler.setAlgorithm("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String passwordHash = passwordHandler.mutate(password);

        String sql = "INSERT INTO users (user_name, login, password, user_role ) values(?,?,?,?)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user_name);
                statement.setString(2, login);
                statement.setString(3, passwordHash);
                statement.setString(4,user_role);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error in addUser", e);
    }
    }

    public void userDelete(String user_id, DataSource dataSource) {
        logger.info("Delete User record. BusPark_ID : {}", user_id);

        String sql = "DELETE FROM users WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user_id);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error in userDelete", e);
        }
    }

    public int countUsersPages(DataSource dataSource) {

        String sql = "SELECT count(*) from users ";
        int pagesCount = 0;

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    pagesCount = (int) Math.ceil(resultSet.getDouble(1) / PAGE_SIZE);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error in countUsersPages", e);
        }
        return pagesCount;
    }
}
