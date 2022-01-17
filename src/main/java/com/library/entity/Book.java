package com.library.entity;

import java.util.Comparator;
import java.util.Date;

public class Book {
    private Integer id;
    private String titleRu;
    private String titleEn;
    private String authorRu;
    private String authorEn;
    private String publicationRu;
    private String publicationEn;
    private Date datePublication;
    private Integer amount;

    public Book() {
    }

    public Book(String titleRu, String titleEn, String authorRu, String authorEn, String publicationRu, String publicationEn, Date datePublication, Integer amount) {
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.authorRu = authorRu;
        this.authorEn = authorEn;
        this.publicationRu = publicationRu;
        this.publicationEn = publicationEn;
        this.datePublication = datePublication;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getAuthorRu() {
        return authorRu;
    }

    public void setAuthorRu(String authorRu) {
        this.authorRu = authorRu;
    }

    public String getAuthorEn() {
        return authorEn;
    }

    public void setAuthorEn(String authorEn) {
        this.authorEn = authorEn;
    }

    public String getPublicationRu() {
        return publicationRu;
    }

    public void setPublicationRu(String publicationRu) {
        this.publicationRu = publicationRu;
    }

    public String getPublicationEn() {
        return publicationEn;
    }

    public void setPublicationEn(String publicationEn) {
        this.publicationEn = publicationEn;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public static class SortTitleRu implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getTitleRu().compareTo(b2.getTitleRu());
        }
    }

    public static class SortTitleEn implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getTitleEn().compareTo(b2.getTitleEn());
        }
    }

    public static class SortAuthorRu implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getAuthorRu().compareTo(b2.getAuthorRu());
        }
    }

    public static class SortAuthorEn implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getAuthorEn().compareTo(b2.getAuthorEn());
        }
    }

    public static class SortPublicationRu implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getPublicationRu().compareTo(b2.getPublicationRu());
        }
    }

    public static class SortPublicationEn implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getPublicationEn().compareTo(b2.getPublicationEn());
        }
    }

    public static class SortDatePublication implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            return b1.getDatePublication().compareTo(b2.getDatePublication());
        }
    }
}