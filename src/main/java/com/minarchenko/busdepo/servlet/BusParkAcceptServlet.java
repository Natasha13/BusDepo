package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.BusParkServise;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "BusParkAcceptServlet", urlPatterns = {"/busParkAccept"})
public class BusParkAcceptServlet extends HttpServlet {

    private BusParkServise busParkServise=new BusParkServise();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String busPark_id = req.getParameter("busPark_id");

        busParkServise.busParkAccept(busPark_id, dataSource);

        resp.sendRedirect("/busPark");
    }

}

