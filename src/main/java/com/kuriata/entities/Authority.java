package com.kuriata.entities;

public class Authority extends BaseEntity {
    private String authorityName;
    private boolean reader;
    private boolean admin;

    public Authority() {
    }

    public Authority(int id, String authorityName, boolean reader, boolean admin) {
        super(id);
        this.authorityName = authorityName;
        this.reader = reader;
        this.admin = admin;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public boolean isReader() {
        return reader;
    }

    public void setReader(boolean reader) {
        this.reader = reader;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Authority authority = (Authority) o;

        if (reader != authority.reader) return false;
        if (admin != authority.admin) return false;
        return authorityName != null ? authorityName.equals(authority.authorityName) : authority.authorityName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (authorityName != null ? authorityName.hashCode() : 0);
        result = 31 * result + (reader ? 1 : 0);
        result = 31 * result + (admin ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id='" + getId() + '\'' +
                ", authorityName='" + authorityName + '\'' +
                ", reader=" + reader +
                ", admin=" + admin +
                '}';
    }
}
