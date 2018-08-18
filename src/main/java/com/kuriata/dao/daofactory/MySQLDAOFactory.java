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
    public IBookDAO getBooksDAO() throws DAOException {
        return new BookDAO();
    }

    @Override
    public IShelfDAO getShelfsDAO(WrappedConnection wrappedConnection) throws DAOException {
        return new ShelfDAO();
    }

    @Override
    public IUserDAO getUsersDAO(WrappedConnection wrappedConnection) {
        return new UserDAO();
    }

    @Override
    public IShelfBookDAO getShelfBookDAO() throws DAOException {
        return new ShelfBookDAO();
    }

    @Override
    public IUserBookDAO getUserBookDao() throws DAOException {
        return new UserBookDAO();
    }
}