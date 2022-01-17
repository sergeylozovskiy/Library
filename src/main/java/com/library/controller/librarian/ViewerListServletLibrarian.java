package com.library.controller.librarian;

import com.library.service.UserService;
import com.library.util.DatabaseFunctions;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "selectViewers", urlPatterns = "/librarian/selectViewers")
public class ViewerListServletLibrarian extends HttpServlet {
    private static final Logger log = Logger.getLogger(ViewerListServletLibrarian.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Connection connection = DatabaseFunctions.getConnection();
            UserService userService = new UserService(connection);
            List viewers = userService.findAllUsers("role like 'VIEWER'", "login");
            req.getSession().setAttribute("viewers", viewers);
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("viewers.jsp");
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
            UserService userService = new UserService(connection);
            req.getSession().setAttribute("viewers", userService.findAllUsers("role like 'VIEWER'", "login"));
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("viewers.jsp");
            printWriter.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }

}
