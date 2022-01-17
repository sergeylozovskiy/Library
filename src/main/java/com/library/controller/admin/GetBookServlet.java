package com.library.controller.admin;

import com.library.entity.Book;
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


@WebServlet(name = "getBook", urlPatterns = "/admin/getBook")
public class GetBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Connection connection = DatabaseFunctions.getConnection();
        BookService bookService = new BookService(connection);
        Book book = bookService.getBookById(Integer.parseInt(id));
        req.getSession().setAttribute("editedBook", book);
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("edit_book.jsp");
        printWriter.close();
    }

}
