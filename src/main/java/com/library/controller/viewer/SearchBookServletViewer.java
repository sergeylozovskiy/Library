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


@WebServlet(name = "searchBook", urlPatterns = "/viewer/searchBook")
public class SearchBookServletViewer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String conditionTotal;
        String conditionTitle = null;
        String conditionAuthor = null;
        if (title != null && title.length() > 0) {
            conditionTitle = "(title_en like '%" + title + "%' or title_ru like '%" + title + "%')";
        }
        if (author != null && author.length() > 0) {
            conditionAuthor = "(author_en like '%" + author + "%' or author_ru like '%" + author + "%')";
        }
        if (conditionTitle != null && conditionAuthor != null) {
            conditionTotal = conditionAuthor + " and " + conditionTitle;
        } else if (conditionTitle != null && conditionAuthor == null) {
            conditionTotal = conditionTitle;
        } else {
            conditionTotal = conditionAuthor;
        }

        Connection connection = DatabaseFunctions.getConnection();
        BookService bookService = new BookService(connection);
        req.getSession().setAttribute("books", bookService.searchBooks(conditionTotal, null));
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("books.jsp");
        printWriter.close();
    }

}
