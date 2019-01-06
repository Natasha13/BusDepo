package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.RouteService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RouteDeleteServletTest {

public RouteDeleteServlet routeDeleteServlet=new RouteDeleteServlet();

    @Test
    public void doPost() throws Exception {

        RouteService routeService = mock(RouteService.class);
        DataSource dataSource = mock(DataSource.class);
        routeDeleteServlet.setRouteService(routeService);
        routeDeleteServlet.setDataSource(dataSource);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("route_id")).thenReturn("1");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        routeDeleteServlet.doPost(req, resp);

        verify(routeService).routeDelete("1", dataSource);
        verify(resp).sendRedirect("/routes");
    }

}