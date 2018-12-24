package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Bus;
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

public class BusService implements Serializable {

    private static final int PAGE_SIZE = 2;

   public List<Bus> getBuses(DataSource dataSource, Integer page) {
        List<Bus> buses = new ArrayList<>();
        Integer offset=(page-1)*PAGE_SIZE;

        String sql = "SELECT id,bus_number FROM buses LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1,PAGE_SIZE);
                statement.setInt(2,offset);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bus bus = new Bus(
                                resultSet.getInt("id"),
                                resultSet.getString("bus_number"));
                        buses.add(bus);
                    }
                }
            }
        } catch (SQLException e) {
            //log("SQL Exception: ", e);
        }
        return buses;
    }

    public void addBus(String bus_number, DataSource dataSource) {
        String sql = "INSERT INTO buses (bus_number) VALUES(?)";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bus_number);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void busDelete(String bus_id, DataSource dataSource) {
        String sql = "DELETE FROM buses WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bus_id);
                statement.execute();
            }
        } catch (SQLException e) {
//            log("SQL Exception: ", e);
        }
    }

    public int countBusesPages( DataSource dataSource) {
        String sql = "SELECT count(*) from buses ";
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