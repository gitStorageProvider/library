package com.kuriata.entities;

public class Author extends BaseEntity {
    private String fullName;
    private String details;

    public Author(){}

    public Author(int id, String fullName, String details) {
        super(id);
        this.fullName = fullName;
        this.details = details;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (fullName != null ? !fullName.equals(author.fullName) : author.fullName != null) return false;
        return details != null ? details.equals(author.details) : author.details == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + getId() + '\'' +
                ", \tfullName='" + fullName + '\'' +
                ", \tdetails='" + details + '\'' +
                '}';
    }
}
