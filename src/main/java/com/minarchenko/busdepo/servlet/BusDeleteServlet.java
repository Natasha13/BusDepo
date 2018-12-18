package com.minarchenko.busdepo.servlet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "BusDeleteServlet", urlPatterns = {"/busDelete"})
public class BusDeleteServlet extends HttpServlet {

    private BusService busService = new BusService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bus_id = req.getParameter("bus_id");

        busService.busDelete(bus_id,dataSource);

        resp.sendRedirect("/buses");
    }
}
