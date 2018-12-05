package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.BusPark;

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

@WebServlet(name = "BusParkServlet", urlPatterns = {"/busPark"})
public class BusParkServlet extends HttpServlet {

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String paramName = "id";
        String paramValue = req.getParameter(paramName); //

        List<BusPark> busParks = new ArrayList<>();

        String sql = "";
        if (paramValue != null) {
            sql = "SELECT id,bus_id, user_id, route_id, accepted FROM bus_park WHERE id=?";
        } else {
            sql = "SELECT id,bus_id, user_id, route_id, accepted FROM bus_park";
        }
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if (paramValue != null) {
                    statement.setString(1, paramValue);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        BusPark busPark = new BusPark(
                                resultSet.getInt("id"),
                                resultSet.getInt("bus_id"),
                                resultSet.getInt("user_id"),
                                resultSet.getInt("route_id"),
                                resultSet.getBoolean("accepted"));
                        busParks.add(busPark);
                    }
                }
            }
        } catch (SQLException e) {
            log("SQL Exception: ", e);
        }

        req.setAttribute("busParks1", busParks);
        RequestDispatcher rd = req.getRequestDispatcher("busPark.jsp");
        rd.forward(req, resp);
    }
}


