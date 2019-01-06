package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.User;
import com.minarchenko.busdepo.service.UserService;
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

public class UserServletTest {


    private UserServlet userServlet=new UserServlet();

    private UserService userService = mock(UserService.class);
    private DataSource dataSource = mock(DataSource.class);

    @Before
    public void before() throws Exception {
        userServlet.setUserService(userService);
        userServlet.setDataSource(dataSource);
    }

    @Test
    public void doGet() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd=mock(RequestDispatcher.class);

        User user1 = new User(1,"name123","login1","password1","driver");
        User user2 = new User(2,"name2","login2","password2","driver");

        List<User> users = Arrays.asList(user1, user2);

        when(req.getParameter("page")).thenReturn("2");
        when(userService.getUsers(dataSource, 2)).thenReturn(users);
        when(userService.countUsersPages(dataSource)).thenReturn(3);
        when(req.getRequestDispatcher("user.jsp")).thenReturn(rd);

        userServlet.doGet(req,resp);

        verify(req).setAttribute("page",2);
        verify(req).setAttribute("pagesCount", 3);
        verify(req).setAttribute("users", users);
        verify(rd).forward(req, resp);
    }

    @Test
    public void doPost() throws Exception {

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("user_name")).thenReturn("12");
        when(req.getParameter("login")).thenReturn("ninavakulenko");
        when(req.getParameter("password")).thenReturn("Nina");
        when(req.getParameter("user_role")).thenReturn("driver");


        HttpServletResponse resp = mock(HttpServletResponse.class);

        userServlet.doPost(req, resp);

        verify(userService).addUser("12","ninavakulenko","Nina",
                "driver",dataSource);
        verify(resp).sendRedirect("/users");
    }

}