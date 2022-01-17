package com.library.controller.librarian;

import com.library.entity.Orders;
import com.library.enums.OrderStatus;
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
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "selectOrders", urlPatterns = "/librarian/selectOrders")
public class OrderListServletLibrarian extends HttpServlet {
    private static final Logger log = Logger.getLogger(OrderListByViewerServletLibrarian.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Connection connection = DatabaseFunctions.getConnection();
            OrdersService ordersService = new OrdersService(connection);
            List orders = ordersService.searchOrders(null, "date_created");
            checkPenalty(orders, ordersService);
            req.getSession().setAttribute("orders", ordersService.searchOrders(null, "date_created"));
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("orders.jsp");
            printWriter.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Connection connection = DatabaseFunctions.getConnection();
            OrdersService ordersService = new OrdersService(connection);
            List orders = ordersService.searchOrders(null, "date_created");
            checkPenalty(orders, ordersService);
            req.getSession().setAttribute("orders", ordersService.searchOrders(null, "date_created"));
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("orders.jsp");
            printWriter.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }

    private void checkPenalty(List orders, OrdersService ordersService) {
        for (int i = 0; i < orders.size(); i++) {
            Orders order = (Orders) orders.get(i);
            if (order.getDateReturned() == null) {
                Date currentDate = new Date();
                if (order.getDateTo().before(currentDate) && order.getStatus().equals(OrderStatus.ISSUED)) {
                    long diff = Math.abs(currentDate.getTime() - order.getDateTo().getTime());
                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    double penalty = days * 7;
                    order.setPenalty(penalty);
                    String result = ordersService.updatePenalty(order);
                    if (result.equals("Success")) {
                    } else {
                        log.error(order.getId() + " couldn't update");
                    }
                }
            }
        }
    }
}
