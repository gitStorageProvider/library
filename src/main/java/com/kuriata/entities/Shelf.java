package com.kuriata.entities;

public class Shelf extends BaseEntity {
    private String name;
    private String adress;
    private String description;

    public Shelf(){}

    public Shelf(int id, String name, String adress, String description) {
        super(id);
        this.name = name;
        this.adress = adress;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Shelf shelf = (Shelf) o;

        if (name != null ? !name.equals(shelf.name) : shelf.name != null) return false;
        if (adress != null ? !adress.equals(shelf.adress) : shelf.adress != null) return false;
        return description != null ? description.equals(shelf.description) : shelf.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
