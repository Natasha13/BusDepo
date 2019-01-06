package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Route;
import com.minarchenko.busdepo.service.RouteService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RouteServletTest {

    private RouteServlet routeServlet=new RouteServlet();

    private RouteService routeService = mock(RouteService.class);
    private DataSource dataSource = mock(DataSource.class);

    @Before
    public void before() throws Exception {
        routeServlet.setRouteService(routeService);
        routeServlet.setDataSource(dataSource);
    }

    @Test
    public void doGet() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd=mock(RequestDispatcher.class);

        Route route1 = new Route(1,"123");
        Route route2 = new Route(2,"12");

        List<Route> routes = Arrays.asList(route1, route2);

        when(req.getParameter("page")).thenReturn("2");
        when(routeService.getRoutes(dataSource, 2)).thenReturn(routes);
        when(routeService.countRoutesPages(dataSource)).thenReturn(3);
        when(req.getRequestDispatcher("route.jsp")).thenReturn(rd);

        routeServlet.doGet(req,resp);

        verify(req).setAttribute("page",2);
        verify(req).setAttribute("pagesCount", 3);
        verify(req).setAttribute("routes", routes);
        verify(rd).forward(req, resp);
    }

    @Test
    public void doPost() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("route_name")).thenReturn("12");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        routeServlet.doPost(req, resp);

        verify(routeService).addRoute("12", dataSource);
        verify(resp).sendRedirect("/routes");
    }

}