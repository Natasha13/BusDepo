package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.User;
import org.apache.catalina.realm.MessageDigestCredentialHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

    public List<User> getUsers(DataSource dataSource, Integer page) {
        List<User> users = new ArrayList<>();
        Integer offset=(page-1)*PAGE_SIZE;

        String sql = "SELECT id,user_name, login, password, user_role FROM users LIMIT ? OFFSET ?";

        try (Connection connection =dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,PAGE_SIZE);
                statement.setInt(2,offset);
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
        MessageDigestCredentialHandler passwordHandler = new MessageDigestCredentialHandler();
        try {
            passwordHandler.setAlgorithm("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String passwordHash = passwordHandler.mutate(password);

        String sql = "INSERT INTO users (user_name, login, password, user_role ) values(?,?,?,?)";

        QueryRunner runner = new QueryRunner(dataSource);
        try {
            runner.execute(sql, user_name,login,passwordHash,user_role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userDelete(String user_id, DataSource dataSource) {
        String sql = "DELETE FROM users WHERE id=?";

        QueryRunner runner = new QueryRunner(dataSource);
        try {
            runner.execute(sql, user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countUsersPages( DataSource dataSource) {
        String sql = "SELECT count(*) from users ";
        QueryRunner runner = new QueryRunner(dataSource);
        ResultSetHandler<Integer> handler = resultSet -> {
            resultSet.next();
            return (int)Math.ceil(resultSet.getDouble(1) /PAGE_SIZE);
        };
        try {
            return runner.execute(sql, handler).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
