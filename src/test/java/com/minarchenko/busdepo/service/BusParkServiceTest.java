package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.model.BusPark;
import com.minarchenko.busdepo.model.Route;
import com.minarchenko.busdepo.model.User;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusParkServiceTest {

    private BusParkService busParkService = new BusParkService();
    private DataSource dataSource = mock(DataSource.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);

    @Before
    public void setUp() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
    }

    @Test
    public void getBusParks() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("bus_id")).thenReturn(1);
        when(resultSet.getString("bus_number")).thenReturn("bus_number_1");
        when(resultSet.getInt("user_id")).thenReturn(2);
        when(resultSet.getString("user_name")).thenReturn("user_name_1");
        when(resultSet.getString("login")).thenReturn("login_1");
        when(resultSet.getString("password")).thenReturn("password_1");
        when(resultSet.getString("user_role")).thenReturn("user_role_1");
        when(resultSet.getString("bus_number")).thenReturn("bus_number_1");
        when(resultSet.getInt("route_id")).thenReturn(3);
        when(resultSet.getString("route_name")).thenReturn("route_name_1");
        when(resultSet.getBoolean("accepted")).thenReturn(false);

        List<BusPark> busParks = busParkService.getBusParks(dataSource, 1);

        verify(statement).setInt(1, 2);
        verify(statement).setInt(2, 0);

        Bus bus = new Bus(1, "bus_number_1");
        User user = new User(2, "user_name_1","login_1",
                "password_1","user_role_1");
        Route route = new Route(3, "route_name_1");
        BusPark busPark1 = new BusPark(1,bus,route,user,false);

        List<BusPark> expected = Collections.singletonList(busPark1);
        assertEquals(expected, busParks);
    }

    @Test
    public void getBusParksForUser() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("bus_id")).thenReturn(1);
        when(resultSet.getString("bus_number")).thenReturn("bus_number_1");
        when(resultSet.getInt("user_id")).thenReturn(2);
        when(resultSet.getString("user_name")).thenReturn("user_name_1");
        when(resultSet.getString("login")).thenReturn("login_1");
        when(resultSet.getString("password")).thenReturn("password_1");
        when(resultSet.getString("user_role")).thenReturn("user_role_1");
        when(resultSet.getString("bus_number")).thenReturn("bus_number_1");
        when(resultSet.getInt("route_id")).thenReturn(3);
        when(resultSet.getString("route_name")).thenReturn("route_name_1");
        when(resultSet.getBoolean("accepted")).thenReturn(false);

        List<BusPark> busParks = busParkService.getBusParksForUser(dataSource, "login1");

        verify(statement).setString(1, "login1");

        Bus bus = new Bus(1, "bus_number_1");
        User user = new User(2, "user_name_1","login_1",
                "password_1","user_role_1");
        Route route = new Route(3, "route_name_1");
        BusPark busPark1 = new BusPark(1,bus,route,user,false);

        List<BusPark> expected = Collections.singletonList(busPark1);
        assertEquals(expected, busParks);
    }

    @Test
    public void addBusPark() throws Exception {

        busParkService.addBusPark("bus_id","user_id","route_id",dataSource);

        verify(statement).setString(1, "bus_id");
        verify(statement).setString(2, "user_id");
        verify(statement).setString(3, "route_id");
        verify(statement).execute();
    }

    @Test
    public void busParkDelete() throws Exception {
        busParkService.busParkDelete("1", dataSource);

        verify(statement).setString(1, "1");
        verify(statement).execute();
    }

    @Test
    public void busParkAccept() throws Exception {
        busParkService.busParkAccept("1", dataSource);

        verify(statement).setString(1, "1");
        verify(statement).execute();
    }

    @Test
    public void countBusParkPages() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getDouble(1)).thenReturn(7.0);

        int countBusParkPages = busParkService.countBusParkPages(dataSource);

        assertEquals(4, countBusParkPages);
    }

}