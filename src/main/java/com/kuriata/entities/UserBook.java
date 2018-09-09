package com.kuriata.entities;

import java.time.LocalDateTime;

public class UserBook extends BaseEntity {
    private int userId;
    private int bookId;
    private LocalDateTime date;

    public UserBook() {
    }

    public UserBook(int id, int userId, int bookId, LocalDateTime date) {
        super(id);
        this.userId = userId;
        this.bookId = bookId;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserBook userBook = (UserBook) o;

        if (userId != userBook.userId) return false;
        if (bookId != userBook.bookId) return false;
        return date != null ? date.equals(userBook.date) : userBook.date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId;
        result = 31 * result + bookId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserBook{" +
                "id=" + userId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", date=" + date +
                "}";
    }
}
