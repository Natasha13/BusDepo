package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.service.BusService;
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
 * A Servlet, which is used for showing Bus entities and adding new entities
 */
@WebServlet(name = "BusServlet", urlPatterns = {"/buses"})
public class BusServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(BusServlet.class);

    private BusService busService = new BusService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    public void setBusService(BusService busService) {
        this.busService = busService;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns a page of Bus entities from dataSource for admin or driver
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageString = req.getParameter("page");

        logger.debug("BusServlet doGet. Page : {}", pageString);

        if (pageString == null) {
            pageString = "1";
        }

        Integer page = Integer.valueOf(pageString);
        int pagesCount = 0;

        List<Bus> buses = busService.getBuses(dataSource, page);

        pagesCount = busService.countBusesPages(dataSource);

        req.setAttribute("page", page);
        req.setAttribute("pagesCount", pagesCount);
        req.setAttribute("buses", buses);
        RequestDispatcher rd = req.getRequestDispatcher("bus.jsp");
        rd.forward(req, resp);
    }

    /**
     * Processes http requests by admin for adding new Bus entity
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bus_number = req.getParameter("bus_number");

        logger.info("BusServlet doPost. Bus number : {}", bus_number);

        busService.addBus(bus_number, dataSource);

        resp.sendRedirect("/buses");
    }
}
