package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "RouteDeleteServlet", urlPatterns = {"/routeDelete"})
public class RouteDeleteServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(RouteDeleteServlet.class);

    private RouteService routeService = new RouteService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String route_id = req.getParameter("route_id");

        logger.info("RouteDeleteServlet doPost. Route_ID : {}",route_id);

        routeService.routeDelete(route_id, dataSource);

        resp.sendRedirect("/routes");
    }
}
