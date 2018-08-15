package com.kuriata.entities;

public class ShelfBook extends BaseEntity {
    private int shelfId;
    private int bookId;
    private int quantity;

    public ShelfBook() {
    }

    public ShelfBook(int id, int shelfId, int bookId, int quantity) {
        super(id);
        this.shelfId = shelfId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ShelfBook shelfBook = (ShelfBook) o;

        if (shelfId != shelfBook.shelfId) return false;
        if (bookId != shelfBook.bookId) return false;
        return quantity == shelfBook.quantity;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + shelfId;
        result = 31 * result + bookId;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "ShelfBook{" +
                "id=" + getId() +
                ", shelfId=" + shelfId +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                "}";
    }
}
