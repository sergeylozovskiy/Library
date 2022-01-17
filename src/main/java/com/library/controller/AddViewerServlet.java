package com.library.controller;

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

@WebServlet(name = "addViewer", urlPatterns = "/addViewer")
public class AddViewerServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddViewerServlet.class);
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

            if (login != "" && password != "") {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.VIEWER);

                if(surnameEn==""){
                    user.setSurnameEn(surnameRu);
                }else {
                    user.setSurnameEn(surnameEn);
                }
                if (surnameRu==""){
                    user.setSurnameRu(surnameEn);
                } else{
                    user.setSurnameRu(surnameRu);
                }
               // user.setSurnameEn(surnameEn);
                //user.setSurnameRu(surnameRu);
                if (nameEn==""){
                    user.setNameEn(nameRu);
                }else{
                    user.setNameEn(nameEn);
                }
                if(nameRu==""){
                    user.setNameRu(nameEn);
                }else{
                    user.setNameRu(nameRu);
                }
                //user.setNameEn(nameEn);
                //user.setNameRu(nameRu);
                if(patronymicEn==""){
                    user.setPatronymicEn(patronymicRu);
                }else{
                    user.setPatronymicEn(patronymicEn);
                }
                if (patronymicRu==""){
                    user.setPatronymicRu(patronymicEn);
                }else{
                    user.setPatronymicRu(patronymicRu);
                }
               // user.setPatronymicEn(patronymicEn);
               // user.setPatronymicRu(patronymicRu);

                user.setActive(true);
                String result = userService.addUser(user);

                if (result.equals("Success")) {
                    PrintWriter printWriter = resp.getWriter();
                    resp.sendRedirect("login.jsp");
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
