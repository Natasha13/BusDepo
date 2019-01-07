package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Bus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that contains business logic to work with Bus entities
 */
public class BusService {
    private static final int PAGE_SIZE = 2;
    private static Logger logger = LoggerFactory.getLogger(BusService.class);

    /**
     * Returns a page of Bus entities from dataSource for admin
     *
     * @param dataSource a dataSource to read from
     * @param page       a page to show
     */
    public List<Bus> getBuses(DataSource dataSource, Integer page) {
        logger.debug("getBuses for page {}", page);

        List<Bus> buses = new ArrayList<>();
        Integer offset = (page - 1) * PAGE_SIZE;

        String sql = "SELECT id,bus_number FROM buses LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, PAGE_SIZE);
                statement.setInt(2, offset);
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
            logger.error("SQL error in getBuses ", e);
        }
        return buses;
    }

    /**
     * Adds new Bus record to database
     */
    public void addBus(String bus_number, DataSource dataSource) {
        logger.info("created bus. Number : {}", bus_number);

        String sql = "INSERT INTO buses (bus_number) VALUES(?)";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bus_number);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error in getBuses ", e);
        }
    }

    /**
     * Delete Bus record from database
     */
    public void busDelete(String bus_id, DataSource dataSource) {
        logger.info("Deleted bus. Bus ID : {}", bus_id);

        String sql = "DELETE FROM buses WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, bus_id);
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("SQL error in getBuses ", e);
        }
    }

    /**
     * Return number of pages of Bus records
     */
    public int countBusesPages(DataSource dataSource) {

        String sql = "SELECT count(*) FROM buses ";

        logger.debug("Number of pages : {}", sql);

        int pageCount = 0;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    pageCount = (int) Math.ceil(resultSet.getDouble(1) / PAGE_SIZE);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL error in getBuses ", e);
        }
        return pageCount;
    }
}