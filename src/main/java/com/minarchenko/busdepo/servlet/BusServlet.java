package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Bus;
import com.minarchenko.busdepo.service.BusService;

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

@WebServlet(name = "BusServlet", urlPatterns = {"/buses"})
public class BusServlet extends HttpServlet {

    private final BusService busService = new BusService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        Integer page = Integer.valueOf(pageString);
        int pagesCount=0;

        List<Bus> buses = busService.getBuses(dataSource,page);


        pagesCount=busService.countBusesPages(dataSource);

        req.setAttribute("page", page);
        req.setAttribute("pagesCount",pagesCount);
        req.setAttribute("buses", buses);
        RequestDispatcher rd = req.getRequestDispatcher("bus.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bus_number = req.getParameter("bus_number");

        busService.addBus(bus_number, dataSource);

        resp.sendRedirect("/buses");
    }
}
