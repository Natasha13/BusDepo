package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Bus;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusServiceTest {

    private BusService busService=new BusService();
    private DataSource dataSource = mock(DataSource.class);
    private Connection connection=mock(Connection.class);
    private PreparedStatement statement=mock(PreparedStatement.class);

    @Before
    public void setUp() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
    }

    @Test
    public void getBuses() throws Exception {

        ResultSet resultSet=mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true,false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("bus_number")).thenReturn("bus_number_1");

        List<Bus> buses = busService.getBuses(dataSource, 1);

        verify(statement).setInt(1, 2);
        verify(statement).setInt(2, 0);

        Bus bus = new Bus(1, "bus_number_1");
        List<Bus> expected = Collections.singletonList(bus);
        assertEquals(expected, buses);
    }

    @Test
    public void addBus() throws Exception {

        busService.addBus("1",dataSource);

        verify(statement).setString(1,"1");
        verify(statement).execute();
    }

    @Test
    public void busDelete() throws Exception {
    }

    @Test
    public void countBusesPages() throws Exception {
    }

}