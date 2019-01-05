package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.service.BusParkService;
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
 * A Servlet , that processes http requests from delete BusPark record button
 */
@WebServlet(name = "BusParkDeleteServlet", urlPatterns = {"/busParkDelete"})
public class BusParkDeleteServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(BusParkDeleteServlet.class);

    private BusParkService busParkService =new BusParkService();

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String busPark_id = req.getParameter("busPark_id");

        logger.info("BusParkDeleteServlet doPost. BusPark_ID : {}",busPark_id);

        busParkService.busParkDelete(busPark_id,dataSource);

        resp.sendRedirect("/busPark");
    }

}


