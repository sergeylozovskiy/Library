package com.library.controller.admin;

import com.library.entity.User;
import com.library.enums.Role;
import com.library.service.UserService;
import com.library.util.DatabaseFunctions;
import com.library.util.PasswordEncryption;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Objects;

@WebServlet(name = "login", urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UserLoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Connection connection = DatabaseFunctions.getConnection();
            UserService userService = new UserService(connection);
            User user = userService.getUserByLogin(login);
            if (user != null) {
                if (Objects.equals(PasswordEncryption.hashPassword(password), user.getPassword())) {
                    if (user.isActive()) {
                        req.setAttribute("user", user);
                        if (user.getRole().equals(Role.ADMINISTRATOR)) {
                            req.getSession().setAttribute("user", user);
                            PrintWriter printWriter = resp.getWriter();
                            resp.sendRedirect("admin/main.jsp");
                            printWriter.close();
                        } else if (user.getRole().equals(Role.VIEWER)) {
                            req.getSession().setAttribute("user", user);
                            PrintWriter printWriter = resp.getWriter();
                            resp.sendRedirect("viewer/main.jsp");
                            printWriter.close();
                        } else if (user.getRole().equals(Role.LIBRARIAN)) {
                            req.getSession().setAttribute("user", user);
                            PrintWriter printWriter = resp.getWriter();
                            resp.sendRedirect("librarian/main.jsp");
                            printWriter.close();
                        } else {
                            PrintWriter printWriter = resp.getWriter();
                            resp.sendRedirect("error.jsp?message=Unknown user's role");
                            printWriter.close();
                        }
                    } else {
                        PrintWriter printWriter = resp.getWriter();
                        resp.sendRedirect("error.jsp?message=Blocked account");
                        printWriter.close();
                    }
                } else {
                    PrintWriter printWriter = resp.getWriter();
                    resp.sendRedirect("error.jsp?message=Incorrect password");
                    printWriter.close();
                }
            } else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=Incorrect login");
                printWriter.close();
            }
            connection.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }
}
