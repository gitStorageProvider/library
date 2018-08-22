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
    public IShelfDAO getShelfsDAO() throws DAOException {
        return new ShelfDAO();
    }

    @Override
    public IUserDAO getUsersDAO() {
        return new UserDAO();
    }

    @Override
    public IShelfBookDAO getShelfBookDAO() throws DAOException {
        return new ShelfBookDAO();
    }

    @Override
    public IUserBookDAO getUserBookDAO() throws DAOException {
        return new UserBookDAO();
    }

    @Override
    public IUserAuthorityDAO getUserAuthorityDAO() throws DAOException {
        return new UserAuthorityDAO();
    }

    @Override
    public IAuthorityDAO getAuthorityDAO() throws DAOException {
        return new AuthorityDAO();
    }
}