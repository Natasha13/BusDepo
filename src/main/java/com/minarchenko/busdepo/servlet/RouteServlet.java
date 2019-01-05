package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Route;
import com.minarchenko.busdepo.service.RouteService;
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
 * A Servlet, which is used for showing Route entities and adding new entities
 */
@WebServlet(name = "RouteServlet", urlPatterns = {"/routes"})
public class RouteServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(RouteServlet.class);

    private final RouteService routeService = new RouteService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    /**
     * Returns a page of Route entities from dataSource for admin or driver
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pageString = req.getParameter("page");

        logger.debug("RouteServlet doGet. Page : {}",pageString);

        if (pageString == null) {
            pageString = "1";
        }
        Integer page = Integer.valueOf(pageString);
        int pagesCount=0;

        List<Route> routes = routeService.getRoutes(dataSource,page);
        pagesCount=routeService.countRoutesPages(dataSource);

        req.setAttribute("page", page);
        req.setAttribute("pagesCount",pagesCount);
        req.setAttribute("routes", routes);
        RequestDispatcher rd = req.getRequestDispatcher("route.jsp");
        rd.forward(req, resp);
    }

    /**
     * Processes http requests by admin for adding new Route entity
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {
        String route_name = request.getParameter("route_name");

        logger.info("RouteServlet doPost. Route name : {}",route_name);

        routeService.addRoute(route_name, dataSource);

        resp.sendRedirect("/routes");
    }

}

