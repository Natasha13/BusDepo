package com.minarchenko.busdepo.service;

import com.minarchenko.busdepo.model.Route;
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

public class RouteServiceTest {

    private RouteService routeService = new RouteService();
    private DataSource dataSource = mock(DataSource.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);

    @Before
    public void setUp() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
    }

    @Test
    public void getRoutes() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("route_name")).thenReturn("route_name_1");

        List<Route> routes = routeService.getRoutes(dataSource, 1);

        verify(statement).setInt(1, 2);
        verify(statement).setInt(2, 0);

        Route route = new Route(1, "route_name_1");
        List<Route> expected = Collections.singletonList(route);
        assertEquals(expected,routes);

    }

    @Test
    public void addRoute() throws Exception {
        routeService.addRoute("1",dataSource);

        verify(statement).setString(1, "1");
        verify(statement).execute();
    }

    @Test
    public void routeDelete() throws Exception {
        routeService.routeDelete("1",dataSource);

        verify(statement).setString(1, "1");
        verify(statement).execute();
    }

    @Test
    public void countRoutesPages() throws Exception {

        ResultSet resultSet = mock(ResultSet.class);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getDouble(1)).thenReturn(7.0);

        int countRoutesPages = routeService.countRoutesPages(dataSource);

        assertEquals(4, countRoutesPages);
    }

}