package com.kuriata.dao.daofactory;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IAuthorDAO;
import com.kuriata.dao.idao.IBookDAO;
import com.kuriata.dao.idao.IShelfDAO;
import com.kuriata.dao.idao.IUserDAO;
import com.kuriata.dao.mysqldao.AuthorDAO;
import com.kuriata.dao.mysqldao.BookDAO;
import com.kuriata.dao.mysqldao.ShelfDAO;
import com.kuriata.dao.mysqldao.UserDAO;
import com.kuriata.exceptions.DAOException;

public class MySQLDAOFactory implements IDAOFactory {
    @Override
    public IAuthorDAO getAuthorsDAO(WrappedConnection wrappedConnection) {
        return new AuthorDAO(wrappedConnection);
    }

    @Override
    public IBookDAO getBooksDAO(WrappedConnection wrappedConnection) throws DAOException {
        return null;
    }

    @Override
    public IShelfDAO getShelfsDAO(WrappedConnection wrappedConnection) throws DAOException {
        return null;
    }

    @Override
    public IUserDAO getUsersDAO(WrappedConnection wrappedConnection) {
        return null;
    }

}