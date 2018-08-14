package com.kuriata.entities;

public class Book extends BaseEntity {
    private String shortTitle;
    private String fullTitle;
    private String description;
    private String keyWords;

    public Book (){}

    public Book(int id, String shortTitle, String fullTitle, String description, String keyWords) {
        super(id);
        this.shortTitle = shortTitle;
        this.fullTitle = fullTitle;
        this.description = description;
        this.keyWords = keyWords;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (shortTitle != null ? !shortTitle.equals(book.shortTitle) : book.shortTitle != null) return false;
        if (fullTitle != null ? !fullTitle.equals(book.fullTitle) : book.fullTitle != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        return keyWords != null ? keyWords.equals(book.keyWords) : book.keyWords == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (shortTitle != null ? shortTitle.hashCode() : 0);
        result = 31 * result + (fullTitle != null ? fullTitle.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (keyWords != null ? keyWords.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + getId() + '\'' +
                ", \tshortTitle='" + shortTitle + '\'' +
                ", \tfullTitle='" + fullTitle + '\'' +
                ", \tdescription='" + description + '\'' +
                ", \tkeyWords='" + keyWords + '\'' +
                "} ";
    }
}
