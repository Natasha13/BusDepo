package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.BusService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusDeleteServletTest {
    private BusDeleteServlet busDeleteServlet = new BusDeleteServlet();

    @Test
    public void doPost() throws Exception {
        BusService busService = mock(BusService.class);
        DataSource dataSource = mock(DataSource.class);
        busDeleteServlet.setBusService(busService);
        busDeleteServlet.setDataSource(dataSource);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("bus_id")).thenReturn("1");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        busDeleteServlet.doPost(req, resp);

        verify(busService).busDelete("1", dataSource);
        verify(resp).sendRedirect("/buses");
    }

}