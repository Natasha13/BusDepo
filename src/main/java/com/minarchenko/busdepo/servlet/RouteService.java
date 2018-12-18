package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Route;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteService implements Serializable {
    public RouteService() {
    }

    List<Route> getRoutes(DataSource dataSource) {
        List<Route> routes = new ArrayList<Route>();

        String sql = "SELECT id,route_name FROM routes";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
//                log("SQL Exception: ", e);
        }
        return routes;
    }

    void addRoute(String route_name, DataSource dataSource) {
        String sql = "INSERT INTO routes (route_name) VALUES(?)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, route_name);
                statement.execute();
            }
        } catch (SQLException e) {
//                e.printStackTrace();
        }
    }

}