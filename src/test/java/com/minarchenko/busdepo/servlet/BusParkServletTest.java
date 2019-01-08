package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.model.BusPark;
import com.minarchenko.busdepo.model.Route;
import com.minarchenko.busdepo.model.User;
import com.minarchenko.busdepo.service.BusParkService;
import org.apache.catalina.realm.GenericPrincipal;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BusParkServletTest {

    private BusParkServlet busParkServlet=new BusParkServlet();
    private BusParkService busParkService = mock(BusParkService.class);
    private DataSource dataSource = mock(DataSource.class);

    @Before
    public void before() throws Exception {
        busParkServlet.setBusParkService(busParkService);
        busParkServlet.setDataSource(dataSource);
    }

    @Test
    public void doGetForAdmin() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd=mock(RequestDispatcher.class);

        Bus bus1 = new Bus(1,"1");
        Bus bus2 = new Bus(2,"2");
        User user1 = new User(1,"name1","login1","password1","driver");
        User user2 = new User(2,"name2","login2","password2","driver");
        Route route1 = new Route(1,"123");
        Route route2 = new Route(2,"21");

        BusPark busPark1 = new BusPark(1,bus1,route1,user1,true);
        BusPark busPark2 = new BusPark(2,bus2,route2,user2,true);

        List<BusPark> busParks = Arrays.asList(busPark1, busPark2);

        when(req.isUserInRole("driver")).thenReturn(false);
        when(req.getParameter("page")).thenReturn("2");
        when(busParkService.getBusParks(dataSource, 2)).thenReturn(busParks);
        when(busParkService.countBusParkPages(dataSource)).thenReturn(3);
        when(req.getRequestDispatcher("busPark.jsp")).thenReturn(rd);

        busParkServlet.doGet(req,resp);

        verify(req).setAttribute("page",2);
        verify(req).setAttribute("pagesCount", 3);
        verify(req).setAttribute("busParks", busParks);
        verify(rd).forward(req, resp);
    }

    @Test
    public void doGetForDriver() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd=mock(RequestDispatcher.class);

        Bus bus1 = new Bus(1,"1");
        Route route1 = new Route(1,"123");
        User user1 = new User(1,"name1","login1","password1","driver");

        BusPark busPark1 = new BusPark(1,bus1,route1,user1,true);

        List<BusPark> busParks = Arrays.asList(busPark1);

        Principal userPrincipal = new GenericPrincipal("name1","password1",
                Collections.singletonList("driver"));

        when(req.isUserInRole("driver")).thenReturn(true);
        when(req.getParameter("page")).thenReturn("1");
        when(req.getUserPrincipal()).thenReturn(userPrincipal);
        when(busParkService.getBusParksForUser(dataSource, "name1")).thenReturn(busParks);
        when(req.getRequestDispatcher("busPark.jsp")).thenReturn(rd);

        busParkServlet.doGet(req,resp);

        verify(req).setAttribute("page",1);
        verify(req).setAttribute("pagesCount", 1);
        verify(req).setAttribute("busParks", busParks);
        verify(rd).forward(req, resp);
    }

    @Test
    public void doPost() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("bus_id")).thenReturn("1");
        when(req.getParameter("user_id")).thenReturn("2");
        when(req.getParameter("route_id")).thenReturn("3");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        busParkServlet.doPost(req, resp);

        verify(busParkService).addBusPark("1","2","3", dataSource);
        verify(resp).sendRedirect("/busPark");
    }

}