package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.User;
import com.minarchenko.busdepo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * A Servlet, which is used for showing User entities and adding new entities
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private final UserService userService=new UserService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    /**
     * Returns a page of User entities from dataSource for admin or driver
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {

        String pageString = req.getParameter("page");

        logger.debug("Users doGet. Page : {}",pageString);

        if (pageString == null) {
            pageString = "1";
        }
        Integer page = Integer.valueOf(pageString);
        int pagesCount=0;

        List<User> users = userService.getUsers(dataSource,page);
        pagesCount=userService.countUsersPages(dataSource);

        req.setAttribute("page", page);
        req.setAttribute("pagesCount",pagesCount);
        req.setAttribute("users", users);
        RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
        rd.forward(req, resp);
    }

    /**
     * Processes http requests by admin for adding new User entity
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String user_name = req.getParameter("user_name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String user_role = req.getParameter("user_role");

        userService.addUser(user_name, login, password, user_role,dataSource);

        resp.sendRedirect("/users");
    }


}


