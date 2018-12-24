package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Route;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteService implements Serializable {

    private static final int PAGE_SIZE = 2;

    public List<Route> getRoutes(DataSource dataSource, Integer page) {
        List<Route> routes = new ArrayList<Route>();
        Integer offset=(page-1)*PAGE_SIZE;

        String sql = "SELECT id,route_name FROM routes users LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,PAGE_SIZE);
                statement.setInt(2,offset);
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

    public void addRoute(String route_name, DataSource dataSource) {
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

    public void routeDelete(String route_id, DataSource dataSource) {
        String sql = "DELETE FROM routes WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, route_id);
                statement.execute();
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    public int countRoutesPages( DataSource dataSource) {
        String sql = "SELECT count(*) from routes ";
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