package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.BusService;
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

/**
 * A Servlet , that processes http requests from delete Bus record button
 */
@WebServlet(name = "BusDeleteServlet", urlPatterns = {"/busDelete"})
public class BusDeleteServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(BusDeleteServlet.class);

    private BusService busService = new BusService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bus_id = req.getParameter("bus_id");
        logger.info("BusDeleteServlet doPost. BusPark_ID : {}",bus_id);

        busService.busDelete(bus_id,dataSource);

        resp.sendRedirect("/buses");
    }
}
