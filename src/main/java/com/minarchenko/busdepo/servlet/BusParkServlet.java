package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.model.BusPark;
import com.minarchenko.busdepo.model.Route;
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

@WebServlet(name = "BusParkServlet", urlPatterns = {"/busPark"})
public class BusParkServlet extends HttpServlet {

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<BusPark> busParks = new ArrayList<>();

        String sql = "SELECT bp.id as id ,bus_id,b.bus_number bus_number," +
                    " user_id, user_name, login, password, user_role," +
                    " route_id,route_name, accepted " +
                    "FROM bus_park bp " +
                    "LEFT JOIN buses b ON bp.bus_id = b.id " +
                    "LEFT JOIN users ON bp.user_id = users.Id " +
                    "LEFT JOIN routes ON bp.route_id = routes.Id ";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bus bus=new Bus(
                                resultSet.getInt("bus_id"),
                                resultSet.getString("bus_number")
                        );
                        User user=new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("user_role")
                        );
                        Route route=new Route(
                                resultSet.getInt("route_id"),
                                resultSet.getString("route_name")
                        );
                        BusPark busPark = new BusPark(
                                resultSet.getInt("id"),
                                bus,
                                route,
                                user,
                                resultSet.getBoolean("accepted")
                        );
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sql = "INSERT INTO bus_park (bus_id,user_id,route_id, accepted) values(?,?,?,false)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, req.getParameter("bus_id"));
                statement.setString(2, req.getParameter("user_id"));
                statement.setString(3, req.getParameter("route_id"));
                statement.execute();
            }
        } catch (SQLException e) {
            log("SQL Exception: ", e);
        }

        resp.sendRedirect("/busPark");
    }
}



