/*
package com.library.controller.admin;

import com.library.entity.User;
import com.library.enums.Role;
import com.library.service.UserService;
import com.library.util.DatabaseFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "addTestUser", urlPatterns = "/addTestUser")
public class AddTestUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = DatabaseFunctions.getConnection();
            UserService userService = new UserService(connection);
            User user = new User();
            user.setRole(Role.ADMINISTRATOR);
            user.setPatronymicEn("patronymic");
            user.setPatronymicRu("отчество");
            user.setNameEn("name");
            user.setNameRu("имя");
            user.setSurnameEn("surname");
            user.setSurnameRu("фамилия");
            user.setActive(true);
            user.setLogin("test");
            user.setPassword("test");
            String result = userService.addUser(user);
            if (result.equals("Success")) {
            } else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=" + result);
                printWriter.close();
            }
            connection.close();
        } catch (Exception e) {
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + e.getLocalizedMessage());
            printWriter.close();
        }
    }
}
*/
