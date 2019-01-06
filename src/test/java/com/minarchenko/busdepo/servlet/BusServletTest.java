package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.service.BusService;
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

public class BusServletTest {
    private BusServlet busServlet=new BusServlet();

    private BusService busService = mock(BusService.class);
    private DataSource dataSource = mock(DataSource.class);

    @Before
    public void before() throws Exception {
        busServlet.setBusService(busService);
        busServlet.setDataSource(dataSource);
    }

    @Test
    public void doGet() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd=mock(RequestDispatcher.class);

        Bus bus1 = new Bus(1,"1");
        Bus bus2 = new Bus(2,"2");

        List<Bus> buses = Arrays.asList(bus1, bus2);

        when(req.getParameter("page")).thenReturn("2");
        when(busService.getBuses(dataSource, 2)).thenReturn(buses);
        when(busService.countBusesPages(dataSource)).thenReturn(3);
        when(req.getRequestDispatcher("bus.jsp")).thenReturn(rd);

        busServlet.doGet(req,resp);

        verify(req).setAttribute("page",2);
        verify(req).setAttribute("pagesCount", 3);
        verify(req).setAttribute("buses", buses);
        verify(rd).forward(req, resp);
    }

    @Test
    public void doPost() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("bus_number")).thenReturn("1");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        busServlet.doPost(req, resp);

        verify(busService).addBus("1", dataSource);
        verify(resp).sendRedirect("/buses");
    }
}