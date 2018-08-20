package com.kuriata.dao.daofactory;

import com.kuriata.dao.idao.*;
import com.kuriata.exceptions.DAOException;

public interface IDAOFactory {
    IAuthorDAO getAuthorsDAO() throws DAOException;

    IBookDAO getBooksDAO() throws DAOException;

    IShelfDAO getShelfsDAO() throws DAOException;

    IUserDAO getUsersDAO() throws DAOException;

    IShelfBookDAO getShelfBookDAO() throws DAOException;

    IUserBookDAO getUserBookDAO() throws DAOException;

    IUserAuthorityDAO getUserAuthorityDAO() throws DAOException;

}
