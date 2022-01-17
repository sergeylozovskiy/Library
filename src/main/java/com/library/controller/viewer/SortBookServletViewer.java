package com.library.controller.viewer;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@WebServlet(name = "sortBook", urlPatterns = "/viewer/sortBook")
public class SortBookServletViewer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String field = req.getParameter("field");
        String type = req.getParameter("type");
        String lang = req.getParameter("lang");
        List books = (List) req.getSession().getAttribute("books");
        switch (field) {
            case "title":
                if (lang.toLowerCase().equals("ru")) {
                    Collections.sort(books, new Book.SortTitleRu());
                } else {
                    Collections.sort(books, new Book.SortTitleEn());
                }
                break;

            case "author":
                if (lang.toLowerCase().equals("ru")) {
                    Collections.sort(books, new Book.SortAuthorRu());
                } else {
                    Collections.sort(books, new Book.SortAuthorEn());
                }
                break;

            case "publication":
                if (lang.toLowerCase().equals("ru")) {
                    Collections.sort(books, new Book.SortPublicationRu());
                } else {
                    Collections.sort(books, new Book.SortPublicationEn());
                }
                break;

            case "datePublication":
                Collections.sort(books, new Book.SortDatePublication());
                break;
            default:
                break;
        }
        if (type.equals("desc")) {
            Collections.reverse(books);
        }
        req.getSession().setAttribute("books", books);
        PrintWriter printWriter = resp.getWriter();
        resp.sendRedirect("books.jsp");
        printWriter.close();
    }

}
