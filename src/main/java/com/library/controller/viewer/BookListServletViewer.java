package com.library.controller.viewer;

import com.library.service.BookService;
import com.library.util.DatabaseFunctions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;


@WebServlet(name = "getBooks", urlPatterns = "/viewer/getBooks")
public class BookListServletViewer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = DatabaseFunctions.getConnection();
        BookService bookService = new BookService(connection);
        req.getSession().setAttribute("books", bookService.searchBooks("amount > 0", null));
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("books.jsp");
        printWriter.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = DatabaseFunctions.getConnection();
        BookService bookService = new BookService(connection);
        req.getSession().setAttribute("books", bookService.searchBooks("amount > 0", null));
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("books.jsp");
        printWriter.close();
    }

}
