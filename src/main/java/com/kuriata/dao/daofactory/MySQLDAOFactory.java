package com.kuriata.dao.daofactory;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.*;
import com.kuriata.dao.mysqldao.*;
import com.kuriata.exceptions.DAOException;

public class MySQLDAOFactory implements IDAOFactory {
    @Override
    public IAuthorDAO getAuthorsDAO() {
        return new AuthorDAO();
    }

    @Override
    public IBookAuthorDAO getBookAuthorsDAO()  {
        return new BookAuthorDAO();
    }

    @Override
    public IBookDAO getBooksDAO()  {
        return new BookDAO();
    }

    @Override
    public IShelfDAO getShelfsDAO()  {
        return new ShelfDAO();
    }

    @Override
    public IUserDAO getUsersDAO() {
        return new UserDAO();
    }

    @Override
    public IShelfBookDAO getShelfBookDAO()  {
        return new ShelfBookDAO();
    }

    @Override
    public IUserBookDAO getUserBookDAO()  {
        return new UserBookDAO();
    }

    @Override
    public IUserAuthorityDAO getUserAuthorityDAO()  {
        return new UserAuthorityDAO();
    }

    @Override
    public IAuthorityDAO getAuthorityDAO() {
        return new AuthorityDAO();
    }
}