package com.kuriata.dao.daofactory;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.*;
import com.kuriata.exceptions.DAOException;

public interface IDAOFactory {
    IAuthorDAO getAuthorsDAO() throws DAOException;

    IBookDAO getBooksDAO() throws DAOException;

    IShelfDAO getShelfsDAO(WrappedConnection wrappedConnection) throws DAOException;

    IUserDAO getUsersDAO(WrappedConnection wrappedConnection) throws DAOException;

    IShelfBookDAO getShelfBookDAO() throws DAOException;

    IUserBookDAO getUserBookDao() throws DAOException;

}
