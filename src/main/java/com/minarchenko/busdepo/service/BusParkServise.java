package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.model.BusPark;
import com.minarchenko.busdepo.model.Route;
import com.minarchenko.busdepo.model.User;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusParkServise implements Serializable {

    private static final int PAGE_SIZE = 2;

    public List<BusPark> getBusParks(DataSource dataSource, Integer page) {
        List<BusPark> busParks = new ArrayList<>();
        Integer offset=(page-1)*PAGE_SIZE;

        String sql = "SELECT bp.id AS id ,bus_id,b.bus_number bus_number," +
                " user_id, user_name, login, password, user_role," +
                " route_id,route_name, accepted " +
                "FROM bus_park bp " +
                "LEFT JOIN buses b ON bp.bus_id = b.id " +
                "LEFT JOIN users ON bp.user_id = users.Id " +
                "LEFT JOIN routes ON bp.route_id = routes.Id " +
                "LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,PAGE_SIZE);
                statement.setInt(2,offset);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bus bus = new Bus(
                                resultSet.getInt("bus_id"),
                                resultSet.getString("bus_number")
                        );
                        User user = new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("user_role")
                        );
                        Route route = new Route(
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
//            log("SQL Exception: ", e);
        }

        return busParks;
    }

    public List<BusPark> getBusParksForUser(DataSource dataSource,String login) {
        List<BusPark> busParks = new ArrayList<>();

        String sql = "SELECT bp.id AS id ,bus_id,b.bus_number bus_number," +
                " user_id, user_name, login, password, user_role," +
                " route_id,route_name, accepted " +
                "FROM bus_park bp " +
                "LEFT JOIN buses b ON bp.bus_id = b.id " +
                "LEFT JOIN users ON bp.user_id = users.Id " +
                "LEFT JOIN routes ON bp.route_id = routes.Id " +
                "WHERE users.login=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, login);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bus bus = new Bus(
                                resultSet.getInt("bus_id"),
                                resultSet.getString("bus_number")
                        );
                        User user = new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("user_role")
                        );
                        Route route = new Route(
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
//            log("SQL Exception: ", e);
        }
        return busParks;
    }

    public void addBusPark(String bus_id, String user_id, String route_id, DataSource dataSource) {
        String sql = "INSERT INTO bus_park (bus_id,user_id,route_id, accepted) values(?,?,?,false)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bus_id);
                statement.setString(2, user_id);
                statement.setString(3, route_id);
                statement.execute();
            }
        } catch (SQLException e) {
       //     log("SQL Exception: ", e);
        }
    }

    public void busParkDelete(String busPark_id, DataSource dataSource) {
        String sql = "DELETE FROM bus_park WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, busPark_id);
                statement.execute();
            }
        } catch (SQLException e) {
            //  log("SQL Exception: ", e);
        }
    }


    public void busParkAccept(String busPark_id, DataSource dataSource) {
        String sql = "UPDATE bus_park SET accepted=NOT accepted WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, busPark_id);
                statement.execute();
            }
        } catch (SQLException e) {
            //  log("SQL Exception: ", e);
        }
    }

    public int countBusParkPages( DataSource dataSource) {
        String sql = "SELECT count(*) from bus_park ";
        int pagesCount=0;

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    pagesCount=(int)Math.ceil(resultSet.getDouble(1) /PAGE_SIZE);
                }
            }
        } catch (SQLException e) {
            //     log("SQL Exception: ", e);
        }
        return pagesCount;
    }
}
