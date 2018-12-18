package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@WebServlet(name = "UserDeleteServlet", urlPatterns = {"/usersDelete"})
public class UserDeleteServlet extends HttpServlet {
    private UserService userService=new UserService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user_id = req.getParameter("user_id");

        userService.userDelete(user_id,dataSource);

        resp.sendRedirect("/users");
    }


}