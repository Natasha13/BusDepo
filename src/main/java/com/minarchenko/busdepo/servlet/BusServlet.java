package com.minarchenko.busdepo.servlet;

import com.minarchenko.busdepo.model.Bus;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BusServlet", urlPatterns = {"/buses"})
public class BusServlet extends HttpServlet {

    @Resource(name = "BusDepo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String paramName = "id";
        String paramValue = req.getParameter(paramName); //

        List<Bus> buses = new ArrayList<>();
        String sql = "";
        if (paramValue != null) {
            sql = "SELECT id,bus_number FROM buses WHERE id=?";
        } else {
            sql = "SELECT id,bus_number FROM buses";
        }
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                if (paramValue != null) {
                    statement.setString(1, paramValue);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Bus bus = new Bus(
                                resultSet.getString("bus_number"),
                                resultSet.getInt("id"));
                        buses.add(bus);
                    }
                }
            }
        } catch (SQLException e) {
            log("SQL Exception: ", e);
        }

        req.setAttribute("buses", buses);
        RequestDispatcher rd = req.getRequestDispatcher("bus.jsp");
        rd.forward(req, resp);
    }
}
