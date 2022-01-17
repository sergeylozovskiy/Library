package com.library.controller.viewer;

import com.library.entity.Orders;
import com.library.enums.OrderStatus;
import com.library.service.BookService;
import com.library.service.OrdersService;
import com.library.service.UserService;
import com.library.util.DatabaseFunctions;
import com.library.util.Utils;
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

@WebServlet(name = "createOrder", urlPatterns = "/viewer/createOrder")
public class CreateOrderServletViewer extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateOrderServletViewer.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String bookId = req.getParameter("bookId");
            String viewerId = req.getParameter("viewerId");
            String dateFrom = req.getParameter("dateFrom");
            String dateTo = req.getParameter("dateTo");

            Connection connection = DatabaseFunctions.getConnection();
            OrdersService ordersService = new OrdersService(connection);
            UserService userService = new UserService(connection);
            BookService bookService = new BookService(connection);
            Orders orders = new Orders();

            orders.setViewer(userService.getUserById(Integer.parseInt(viewerId)));
            orders.setBook(bookService.getBookById(Integer.parseInt(bookId)));
            orders.setAmount(1);
            orders.setDateFrom(Utils.getDateFromString("yyyy-MM-dd", dateFrom));
            orders.setDateTo(Utils.getDateFromString("yyyy-MM-dd", dateTo));
            orders.setDateCreated(new Date());
            orders.setStatus(OrderStatus.CREATED);

            String result = ordersService.addOrders(orders);
            if (result.equals("Success")) {
                List ordersList = ordersService.searchOrders("viewer_id = " + Integer.parseInt(viewerId), "date_created");
                checkPenalty(ordersList, ordersService);
                req.getSession().setAttribute("orders", ordersService.searchOrders("viewer_id = " + Integer.parseInt(viewerId), "date_created"));
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("orders.jsp");
                printWriter.close();
            } else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=" + result);
                printWriter.close();
            }
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
