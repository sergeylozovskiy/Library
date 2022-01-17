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


@WebServlet(name = "deleteUser", urlPatterns = "/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Connection connection = DatabaseFunctions.getConnection();
        UserService userService = new UserService(connection);
        String result = userService.deleteUser(Integer.parseInt(id));
            if (result.equals("Success")) {
                req.getSession().setAttribute("users", userService.findAllUsers(null, null));
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("users.jsp");
                printWriter.close();
            } else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=" + "You are admin, you can't leave");
                printWriter.close();
            }
        }
    }