package com.library.controller.admin;

import com.library.service.UserService;
import com.library.util.DatabaseFunctions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;


@WebServlet(name = "showAllUsers", urlPatterns = "/admin/showAllUsers")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = DatabaseFunctions.getConnection();
        UserService userService = new UserService(connection);
        List users = userService.findAllUsers(null,null);
        req.getSession().setAttribute("users", users);
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("users.jsp");
        printWriter.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = DatabaseFunctions.getConnection();
        UserService userService = new UserService(connection);
        req.getSession().setAttribute("users", userService.findAllUsers(null,null));
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("users.jsp");
        printWriter.close();
    }
}
