package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Route;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RouteDeleteServlet", urlPatterns = {"/routeDelete"})
public class RouteDeleteServlet {
    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            String p0 = req.getParameter("p0");

            List<Route> route= new ArrayList<>();
            String sql = "DELETE FROM routes WHERE id=?";
    }

}
