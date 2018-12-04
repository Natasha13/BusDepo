package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.User;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String paramName = "id";
        String paramValue = req.getParameter(paramName); //

        List<User> users = new ArrayList<>();

        String sql = "";
        if (paramValue != null) {
            sql = "SELECT id,user_name, login, password, user_spesiality FROM users WHERE id=?";
        } else {
            sql = "SELECT id,user_name, login, password, user_spesiality FROM users";
        }
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if (paramValue != null) {
                    statement.setString(1, paramValue);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("user_spesiality")
                                );
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            log("SQL Exception: ", e);
        }

        req.setAttribute("users1", users);
        RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
        rd.forward(req, resp);
    }
}


