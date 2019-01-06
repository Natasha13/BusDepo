package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.UserService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDeleteServletTest {
    private UserDeleteServlet userDeleteServlet = new UserDeleteServlet();

    @Test
    public void doPost() throws Exception {

        UserService userService = mock(UserService.class);
        DataSource dataSource = mock(DataSource.class);
        userDeleteServlet.setUserService(userService);
        userDeleteServlet.setDataSource(dataSource);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("user_id")).thenReturn("1");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        userDeleteServlet.doPost(req, resp);

        verify(userService).userDelete("1", dataSource);
        verify(resp).sendRedirect("/users");
    }
}