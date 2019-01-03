package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.BusPark;
import com.minarchenko.busdepo.service.BusParkService;
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
import java.security.Principal;
import java.util.List;

@WebServlet(name = "BusParkServlet", urlPatterns = {"/busPark"})
public class BusParkServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(BusParkServlet.class);

    private BusParkService busParkService = new BusParkService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    /**
     *
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Principal userPrincipal = req.getUserPrincipal();
        String pageString = req.getParameter("page");

        logger.debug("BusParkServlet doGet. Page : {}",pageString);

        if (pageString == null) {
            pageString = "1";
        }
        Integer page = Integer.valueOf(pageString);
        int pagesCount=0;

        List<BusPark> busParks;

        if (req.isUserInRole("driver")) {
            busParks = busParkService.getBusParksForUser(dataSource, userPrincipal.getName());
            pagesCount=1;
        } else {
            busParks = busParkService.getBusParks(dataSource, page);
            pagesCount= busParkService.countBusParkPages(dataSource);
        }

        req.setAttribute("page", page);
        req.setAttribute("pagesCount",pagesCount);
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

        logger.info("BusParkServlet doPost. Bus_ID : {}, User_ID : {}, Route_ID : {}",bus_id,user_id,route_id);

        busParkService.addBusPark(bus_id, user_id, route_id, dataSource);

        resp.sendRedirect("/busPark");
    }

}



