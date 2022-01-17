package com.library.service;

import com.library.entity.Book;
import com.library.util.DatabaseFunctions;
import com.library.util.Utils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private Connection connection;
    private static final Logger log = Logger.getLogger(BookService.class);

    public BookService(Connection connection) {
        this.connection = connection;
    }

    public List<Book> findAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("books", " * ", null, null, statement);
            if (rs != null) {
                bookList = getBooksFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return bookList;
    }

    public List<Book> searchBooks(String condition, String orderBy) {
        List<Book> bookList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("books", " * ", condition, orderBy, statement);
            if (rs != null) {
                bookList = getBooksFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return bookList;
    }

    public String addBook(Book book) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.insert("books",
                    "author_en,author_ru,publication_en,publication_ru,date_publication,title_en,title_ru,amount",
                    "'" + book.getAuthorEn() + "'," +
                            "'" + book.getAuthorRu() + "'," +
                            "'" + book.getPublicationEn() + "'," +
                            "'" + book.getPublicationRu() + "'," +
                            "'" + Utils.getStringFromDate("yyyy-MM-dd", book.getDatePublication()) + "'," +
                            "'" + book.getTitleEn() + "'," +
                            "'" + book.getTitleRu() + "'," +
                            "" + book.getAmount(),
                    statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String deleteBook(int id) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.delete("books", "id = " + id, statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String editBook(Book book) {
        String result;
        try {
            // author_en,author_ru,publication_en,publication_ru,date_publication_en,date_publication_ru,title_en,title_ru
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.update("books",
                    "author_en='" + book.getAuthorEn() + "'," +
                            "author_ru ='" + book.getAuthorRu() + "'," +
                            "publication_en='" + book.getPublicationEn() + "'," +
                            "publication_ru='" + book.getPublicationRu() + "'," +
                            "date_publication='" + Utils.getStringFromDate("yyyy-MM-dd", book.getDatePublication()) + "'," +
                            "amount=" + book.getAmount() + "," +
                            "title_en='" + book.getTitleEn() + "'," +
                            "title_ru='" + book.getTitleRu() + "'",
                    "id = " + book.getId(), statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public String updateBookCount(int id, int amount, String oper) {
        String result;
        try {
            Statement statement = connection.createStatement();
            result = DatabaseFunctions.update("books", "amount = amount" + oper + amount, "id = " + id, statement);
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            result = e.getLocalizedMessage();
        }
        return result;
    }

    public List<Book> getBooksByCondition(String condition, String orderBy) {
        List<Book> bookList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("books", " * ", condition, orderBy, statement);
            if (rs != null) {
                bookList = getBooksFromResultSet(rs);
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return bookList;
    }

    public Book getBookById(int id) {
        Book book = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = DatabaseFunctions.select("books", " * ", "id = " + id, null, statement);
            if (rs != null) {
                List<Book> bookList = getBooksFromResultSet(rs);
                if (bookList != null && bookList.size() > 0) {
                    book = bookList.get(0);
                }
                rs.close();
            }
            statement.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return book;
    }

    private List<Book> getBooksFromResultSet(ResultSet rs) throws SQLException {
        List<Book> bookList = new ArrayList<>();
        while (rs.next()) {
            Book b = new Book();
            b.setId(rs.getInt("id"));
            b.setAuthorEn(rs.getString("author_en"));
            b.setAuthorRu(rs.getString("author_ru"));
            b.setDatePublication(rs.getDate("date_publication"));
            b.setPublicationEn(rs.getString("publication_en"));
            b.setPublicationRu(rs.getString("publication_ru"));
            b.setTitleEn(rs.getString("title_en"));
            b.setTitleRu(rs.getString("title_ru"));
            b.setAmount(rs.getInt("amount"));
            bookList.add(b);
        }
        return bookList;
    }
}
