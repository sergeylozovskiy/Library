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


@WebServlet(name = "getLibrarianList", urlPatterns = "/admin/getLibrarianList")
public class LibrarianListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = DatabaseFunctions.getConnection();
        UserService userService = new UserService(connection);
        List librarians = userService.findAllUsers("role = 'LIBRARIAN'",null);
        req.getSession().setAttribute("librarians", librarians);
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("librarians.jsp");
        printWriter.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = DatabaseFunctions.getConnection();
        UserService userService = new UserService(connection);
        req.getSession().setAttribute("librarians", userService.findAllUsers("role = 'LIBRARIAN'",null));
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("librarians.jsp");
        printWriter.close();
    }
}
