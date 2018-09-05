package com.kuriata.dao.daofactory;

import com.kuriata.dao.idao.*;
import com.kuriata.exceptions.DAOException;

public interface IDAOFactory {
    IAuthorDAO getAuthorsDAO();

    IBookAuthorDAO getBookAuthorsDAO();

    IBookDAO getBooksDAO();

    IShelfDAO getShelfsDAO();

    IUserDAO getUsersDAO() ;

    IShelfBookDAO getShelfBookDAO() ;

    IUserBookDAO getUserBookDAO() ;

    IUserAuthorityDAO getUserAuthorityDAO() ;

    IAuthorityDAO getAuthorityDAO() ;
}
