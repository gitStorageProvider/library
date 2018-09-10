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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TakenBook takenBook = (TakenBook) o;

        if (book != null ? !book.equals(takenBook.book) : takenBook.book != null) return false;
        return user != null ? user.equals(takenBook.user) : takenBook.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TakenBook{" +
                "id=" + super.getId() +
                ", bookId="+super.getBookId() +
                ", userId"+super.getUserId() +
                ", LocalDateTime" + super.getDate() +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
