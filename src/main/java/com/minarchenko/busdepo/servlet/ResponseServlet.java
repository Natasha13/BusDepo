package com.minarchenko.busdepo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "ResponseServlet", urlPatterns = {"/hello"})
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        resp.setContentType("text/plain");
        String paramName = "index";

        String paramValue = req.getParameter(paramName);


        if (paramValue != null) {
            out.write(paramName);
            out.write(" = ");
            out.write(paramValue + " ");
        }

        out.close();
    }
}

