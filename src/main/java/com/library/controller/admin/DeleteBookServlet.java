package com.library.controller.admin;

import com.library.service.BookService;
import com.library.util.DatabaseFunctions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


@WebServlet(name = "deleteBook", urlPatterns = "/admin/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Connection connection = DatabaseFunctions.getConnection();
        BookService bookService = new BookService(connection);
        String result = bookService.deleteBook(Integer.parseInt(id));
        if (result.equals("Success")) {
            req.getSession().setAttribute("books", bookService.findAllBooks());
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("books.jsp");
            printWriter.close();
        } else {
            PrintWriter printWriter = resp.getWriter();
            resp.sendRedirect("error.jsp?message=" + result);
            printWriter.close();
        }
    }
}