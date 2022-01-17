package com.library.controller;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "error", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ErrorServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("../error.jsp?message=" + resp.getStatus());
            printWriter.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }
}
