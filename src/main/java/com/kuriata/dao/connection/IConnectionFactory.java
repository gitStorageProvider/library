package com.kuriata.dao.connection;

import com.kuriata.exceptions.DAOException;

public interface IConnectionFactory {
    WrappedConnection getConnection() throws DAOException;
    void closeConnection() throws DAOException;
}
