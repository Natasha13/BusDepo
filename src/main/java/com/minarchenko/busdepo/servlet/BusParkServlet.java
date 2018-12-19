package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.BusPark;
import com.minarchenko.busdepo.service.BusParkServise;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@WebServlet(name = "BusParkServlet", urlPatterns = {"/busPark"})
public class BusParkServlet extends HttpServlet {

    private BusParkServise busParkServise = new BusParkServise();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Principal userPrincipal = req.getUserPrincipal();
        List<BusPark> busParks;

        if (req.isUserInRole("driver")) {
            busParks = busParkServise.getBusParksForUser(dataSource, userPrincipal.getName());
        } else {
            busParks = busParkServise.getBusParks(dataSource);
        }

        req.setAttribute("busParks", busParks);
        RequestDispatcher rd = req.getRequestDispatcher("busPark.jsp");
        rd.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bus_id = req.getParameter("bus_id");
        String user_id = req.getParameter("user_id");
        String route_id = req.getParameter("route_id");

        busParkServise.addBusPark(bus_id, user_id, route_id, dataSource);

        resp.sendRedirect("/busPark");
    }

}



