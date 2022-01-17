package com.library.controller.admin;

import com.library.entity.Book;
import com.library.service.BookService;
import com.library.util.DatabaseFunctions;
import com.library.util.Utils;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "addBook", urlPatterns = "/admin/addBook")
public class AddBookServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddBookServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String authorEn = req.getParameter("authorEn");
            String authorRu = req.getParameter("authorRu");
            String publicationEn = req.getParameter("publicationEn");
            String publicationRu = req.getParameter("publicationRu");
            String datePublication = req.getParameter("datePublication");
            String titleEn = req.getParameter("titleEn");
            String titleRu = req.getParameter("titleRu");
            String amount = req.getParameter("amount");

            Connection connection = DatabaseFunctions.getConnection();
            BookService bookService = new BookService(connection);
            Book book = new Book();


            if(titleEn==""){
                book.setTitleEn(titleRu);
            }else {
                book.setTitleEn(titleEn);
            }
            if(titleRu==""){
                book.setTitleRu(titleEn);
            }else{
                book.setTitleRu(titleRu);
            }


           if(authorEn==""){
               book.setAuthorEn(authorRu);
           }else{
               book.setAuthorEn(authorEn);
           }
            if(authorRu==""){
                book.setAuthorRu(authorEn);
            }else{
                book.setAuthorRu(authorRu);
            }


            if(publicationEn==""){
                book.setPublicationEn(publicationRu);
            }else{
                book.setPublicationEn(publicationEn);
            }
            if(publicationRu==""){
                book.setPublicationRu(publicationEn);
            }else{
                book.setPublicationRu(publicationRu);
            }

            book.setAmount(Integer.parseInt(amount));
            book.setDatePublication(Utils.getDateFromString("yyyy-MM-dd", datePublication));

            String result = bookService.addBook(book);
            if (result.equals("Success")) {
                req.getSession().setAttribute("books", bookService.findAllBooks());
                //PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("books.jsp");
                //printWriter.close();
            } else {
                PrintWriter printWriter = resp.getWriter();
                resp.sendRedirect("error.jsp?message=" + result);
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
