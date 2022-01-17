package com.library.controller.admin;

import com.library.entity.User;
import com.library.enums.Role;
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

@WebServlet(name = "addLibrarian", urlPatterns = "/admin/addLibrarian")
public class AddLibrarianServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddLibrarianServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String surnameRu = req.getParameter("surnameRu");
            String surnameEn = req.getParameter("surnameEn");
            String nameRu = req.getParameter("nameRu");
            String nameEn = req.getParameter("nameEn");
            String patronymicRu = req.getParameter("patronymicRu");
            String patronymicEn = req.getParameter("patronymicEn");

            Connection connection = DatabaseFunctions.getConnection();
            UserService userService = new UserService(connection);
            if (login != "" && password != ""){
                User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(Role.LIBRARIAN);
            user.setSurnameEn(surnameEn);
            user.setSurnameRu(surnameRu);
            user.setNameEn(nameEn);
            user.setNameRu(nameRu);
            user.setPatronymicEn(patronymicEn);
            user.setPatronymicRu(patronymicRu);
            user.setActive(true);
            String result = userService.addUser(user);
            if (result.equals("Success")) {
                req.getSession().setAttribute("librarians", userService.findAllUsers("role = 'LIBRARIAN'", null));
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("librarians.jsp");
                printWriter.close();
            } else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=" + result);
                printWriter.close();
            }
            connection.close();
            }
            else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=" + "incorrect data");
                printWriter.close();
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }
}
