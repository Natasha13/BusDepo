package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.BusParkService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusParkAcceptServletTest {

    private BusParkAcceptServlet busParkAcceptServlet=new BusParkAcceptServlet();

    @Test
    public void doPost() throws Exception {

        BusParkService busParkService = mock(BusParkService.class);
        DataSource dataSource = mock(DataSource.class);
        busParkAcceptServlet.setBusParkService(busParkService);
        busParkAcceptServlet.setDataSource(dataSource);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("busPark_id")).thenReturn("1");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        busParkAcceptServlet.doPost(req, resp);

        verify(busParkService).busParkAccept("1", dataSource);
        verify(resp).sendRedirect("/busPark");
    }

}