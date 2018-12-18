package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.BusPark;

import javax.annotation.Resource;
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
        String busPark_id = req.getParameter("busPark_id");

        busDelete(busPark_id,dataSource);

        resp.sendRedirect("/busPark");
    }

    private void busDelete(String busPark_id, DataSource dataSource) {
        String sql = "DELETE FROM bus_park WHERE id=?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, busPark_id);
                statement.execute();
            }
        } catch (SQLException e) {
            log("SQL Exception: ", e);
        }
    }
}


