package com.kuriata.entities;

import java.util.Date;

public class TakenBook extends UserBook {
    private Book book;
    private User user;

    public TakenBook() {
    }

    public TakenBook(UserBook userBook, Book book, User user) {
        super(userBook.getId(), userBook.getUserId(), userBook.getBookId(), userBook.getDate());
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
