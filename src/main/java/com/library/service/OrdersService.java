package com.library.service;

import com.library.entity.Orders;
import com.library.enums.OrderStatus;
import com.library.util.DatabaseFunctions;
import com.library.util.Utils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersService {
    private Connection connection;
    private BookService bookService;
    private UserService userService;
    private static final Logger log = Logger.getLogger(OrdersService.class);

    public OrdersService(Connection connection) {
        this.connection = connection;
        this.bookService = new BookService(connection);
        this.userService = new UserService(connection);
    }

    public List<Orders> findAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("orders", " * ", null, null, statement);
            if (rs != null) {
                ordersList = getOrdersFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return ordersList;
    }

    public List<Orders> searchOrders(String condition, String orderBy) {
        List<Orders> ordersList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("orders", " * ", condition, orderBy, statement);
            if (rs != null) {
                ordersList = getOrdersFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return ordersList;
    }

    public Orders getOrdersById(int id) {
        Orders orders = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("orders", " * ", "id = " + id, null, statement);
            if (rs != null) {
                List<Orders> ordersList = getOrdersFromResultSet(rs);
                if (ordersList != null && ordersList.size() > 0) {
                    orders = ordersList.get(0);
                }
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return orders;
    }

    public String addOrders(Orders orders) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.insert("orders",
                    "book_id,amount,viewer_id,status,date_created,date_from,date_to",
                    "" + orders.getBook().getId() + "," +
                            "'" + orders.getAmount() + "'," +
                            "" + orders.getViewer().getId() + "," +
                            "'" + orders.getStatusString() + "'," +
                            "'" + Utils.getStringFromDate("yyyy-MM-dd", orders.getDateCreated()) + "'," +
                            "'" + Utils.getStringFromDate("yyyy-MM-dd", orders.getDateFrom()) + "'," +
                            "'" + Utils.getStringFromDate("yyyy-MM-dd", orders.getDateTo()) + "'",
                    statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String updateStatus(Orders orders) {
        String result;
        try {
            Statement statement = connection.createStatement();
            String update = "status='" + orders.getStatusString() + "'," +
                    "date_decision='" + Utils.getStringFromDate("yyyy-MM-dd", orders.getDateDecision()) + "'," +
                    "librarian_id = " + orders.getLibrarian().getId();
            if (orders.getStatus().equals(OrderStatus.RETURNED)) {
                update += ", date_returned='" + Utils.getStringFromDate("yyyy-MM-dd", orders.getDateReturned()) + "'";
            }
            result = DatabaseFunctions.update("orders", update, "id = " + orders.getId(), statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String updatePenalty(Orders orders) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.update("orders",
                    "penalty=" + orders.getPenalty(),
                    "id = " + orders.getId(), statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    private List<Orders> getOrdersFromResultSet(ResultSet rs) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        while (rs.next()) {
            Orders o = new Orders();
            o.setId(rs.getInt("id"));
            o.setBook(bookService.getBookById(rs.getInt("book_id")));
            o.setAmount(rs.getInt("amount"));
            o.setLibrarian(userService.getUserById(rs.getInt("librarian_id")));
            o.setViewer(userService.getUserById(rs.getInt("viewer_id")));
            o.setStatus(getStatus(rs.getString("status")));
            o.setDateCreated(rs.getDate("date_created"));
            o.setDateDecision(rs.getDate("date_decision"));
            o.setDateFrom(rs.getDate("date_from"));
            o.setDateTo(rs.getDate("date_to"));
            o.setDateReturned(rs.getDate("date_returned"));
            o.setPenalty(rs.getDouble("penalty"));
            ordersList.add(o);
        }
        return ordersList;
    }

    private OrderStatus getStatus(String statusString) {
        switch (statusString) {
            case "CREATED":
                return OrderStatus.CREATED;
            case "ISSUED":
                return OrderStatus.ISSUED;
            case "REJECTED":
                return OrderStatus.REJECTED;
            default:
                return OrderStatus.RETURNED;
        }
    }


//    public int getCountOrdersById(int id) {
//        Orders orders = null;
//        ResultSet count = null;
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet rs = DatabaseFunctions.selectCountOrders("orders", "count(*)", "id = " + id, statement);
//            orders.getCountOrds() = rs.;
//            statement.close();
//            rs.close();
//        } catch (Exception e) {
//            log.error(e.getLocalizedMessage());
//        }
//        return orders.getCountOrds();
//    }



}
