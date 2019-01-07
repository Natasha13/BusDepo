package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Route;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteService {

    private static final int PAGE_SIZE = 2;
    private static Logger logger = LoggerFactory.getLogger(RouteService.class);

    public List<Route> getRoutes(DataSource dataSource, Integer page) {

        logger.debug("get all Routes for page {}", page);

        List<Route> routes = new ArrayList<Route>();
        Integer offset = (page - 1) * PAGE_SIZE;

        String sql = "SELECT id,route_name FROM routes users LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, PAGE_SIZE);
                statement.setInt(2, offset);
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
            logger.error("SQL error in getRoutes", e);
        }
        return routes;
    }

    public void addRoute(String route_name, DataSource dataSource) {

        logger.debug("Created new route. Route_name : {}", route_name);

        String sql = "INSERT INTO routes (route_name) VALUES(?)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, route_name);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error in addRoute", e);
        }
    }

    public void routeDelete(String route_id, DataSource dataSource) {
        logger.info("Delete Route record. Route_ID : {}", route_id);

        String sql = "DELETE FROM routes WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, route_id);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error in routeDelete", e);
        }
    }

    public int countRoutesPages(DataSource dataSource) {
        String sql = "SELECT count(*) from routes ";

        int pagesCount = 0;

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    pagesCount = (int) Math.ceil(resultSet.getDouble(1) / PAGE_SIZE);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error in countRoutesPages", e);
        }
        return pagesCount;
    }
}