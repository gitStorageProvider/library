package com.kuriata.entities;

public class BookAuthor extends BaseEntity {
    private int bookId;
    private int authorId;

    public BookAuthor() {
    }

    public BookAuthor(int id, int bookId, int authorId) {
        super(id);
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BookAuthor that = (BookAuthor) o;

        if (bookId != that.bookId) return false;
        return authorId == that.authorId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + bookId;
        result = 31 * result + authorId;
        return result;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + getId() +
                ", bookId=" + bookId +
                ", authorId=" + authorId +
                "}";
    }
}
