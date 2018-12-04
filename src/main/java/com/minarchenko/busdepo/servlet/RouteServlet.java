package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Route;

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

    @WebServlet(name = "RouteServlet", urlPatterns = {"/routes"})
    public class RouteServlet extends HttpServlet {

        @Resource(name = "BusDepo")
        private DataSource dataSource;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String paramName = "id";
            String paramValue = req.getParameter(paramName); //

            List<Route> routes = new ArrayList<>();

            String sql = "";
            if (paramValue != null) {
                sql = "SELECT id,route_name FROM routes WHERE id=?";
            } else {
                sql = "SELECT id,route_name FROM routes";
            }
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    if (paramValue != null) {
                        statement.setString(1, paramValue);
                    }
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            Route route = new Route(
                                    resultSet.getInt("id"),
                                    resultSet.getString("route_name"));
                            routes.add(route);
                        }
                    }
                }
            } catch (SQLException e) {
                log("SQL Exception: ", e);
            }

            req.setAttribute("route1", routes);
            RequestDispatcher rd = req.getRequestDispatcher("route.jsp");
            rd.forward(req, resp);
        }
    }

