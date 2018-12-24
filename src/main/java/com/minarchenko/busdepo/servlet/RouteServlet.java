package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Route;
import com.minarchenko.busdepo.service.RouteService;

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

@WebServlet(name = "RouteServlet", urlPatterns = {"/routes"})
public class RouteServlet extends HttpServlet {

    private final RouteService routeService = new RouteService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pageString = req.getParameter("page");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {
        String route_name = request.getParameter("route_name");

        routeService.addRoute(route_name, dataSource);

        resp.sendRedirect("/routes");
    }

}

