package com.kuriata.entities;

public class UserAuthority extends BaseEntity {
    private int userId;
    private int authorityId;

    public UserAuthority() {
    }

    public UserAuthority(int id, int userId, int authorityId) {
        super(id);
        this.userId = userId;
        this.authorityId = authorityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserAuthority that = (UserAuthority) o;

        if (userId != that.userId) return false;
        return authorityId == that.authorityId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId;
        result = 31 * result + authorityId;
        return result;
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "id=" + getId() +
                ", userId=" + userId +
                ", authorityId=" + authorityId +
                '}';
    }
}
