package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.BusPark;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BusParkDeleteServlet", urlPatterns = {"/busParkDelete"})
public class BusParkDeleteServlet extends HttpServlet {

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String p0 = req.getParameter("p0");

        List<BusPark> busesPark = new ArrayList<>();
        String sql = "DELETE FROM bus_park WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, req.getParameter("busPark_id"));
                statement.execute();
            }
        } catch (SQLException e) {
            log("SQL Exception: ", e);
        }

        resp.sendRedirect("/busPark");
    }
}


