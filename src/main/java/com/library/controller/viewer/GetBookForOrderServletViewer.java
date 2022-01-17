package com.library.controller.viewer;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getBookForOrder", urlPatterns = "/viewer/getBookForOrder")
public class GetBookForOrderServletViewer extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetBookForOrderServletViewer.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String bookId = req.getParameter("bookId");
            String viewerId = req.getParameter("viewerId");

            req.getSession().setAttribute("bookId", bookId);
            req.getSession().setAttribute("viewerId", viewerId);
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("create_order.jsp");
            printWriter.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }
}
