package com.library.entity;

import com.library.enums.OrderStatus;

import java.util.Date;

public class Orders {
    private Integer id;
    private Book book;
    private Integer amount;
    private User librarian;
    private User viewer;
    private OrderStatus status;
    private Date dateCreated;
    private Date dateDecision;
    private Date dateReturned;
    private Date dateFrom;
    private Date dateTo;
    private Double penalty;

    public int getCountOrds() {
        return countOrds;
    }

    public void setCountOrds(int countOrds) {
        this.countOrds = countOrds;
    }

    private int countOrds;


    public Orders() {
    }

    public Orders(Book book, User viewer, OrderStatus status, Date dateCreated, Date dateFrom, Date dateTo) {
        this.book = book;
        this.viewer = viewer;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Orders(Book book, User librarian, User viewer, OrderStatus status, Date dateCreated, Date dateDecision, Date dateReturned, Date dateFrom, Date dateTo, Double penalty) {
        this.book = book;
        this.librarian = librarian;
        this.viewer = viewer;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateDecision = dateDecision;
        this.dateReturned = dateReturned;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.penalty = penalty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateDecision() {
        return dateDecision;
    }

    public void setDateDecision(Date dateDecision) {
        this.dateDecision = dateDecision;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatusString() {
     //   status.toString();
        switch (status) {
            case CREATED:
                return "CREATED";
            case ISSUED:
                return "ISSUED";
            case REJECTED:
                return "REJECTED";
            default:
                return "RETURNED";
        }
    }

    public String count(){

        return null;
    }

    public String getPenaltyString() {
        return penalty + "";
    }
}
