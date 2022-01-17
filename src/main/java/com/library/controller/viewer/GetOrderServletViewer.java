package com.library.controller.viewer;

import com.library.service.OrdersService;
import com.library.util.DatabaseFunctions;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "getOrder", urlPatterns = "/viewer/getOrder")
public class GetOrderServletViewer extends HttpServlet {
    private static final Logger log = Logger.getLogger(GetOrderServletViewer.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            Connection connection= DatabaseFunctions.getConnection();
            OrdersService ordersService = new OrdersService(connection);
            req.getSession().setAttribute("order", ordersService.getOrdersById(Integer.parseInt(id)));
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("order.jsp");
            printWriter.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }
}
