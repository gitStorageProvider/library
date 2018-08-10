package com.kuriata.dao.connection;

import com.kuriata.exceptions.DAOException;

public abstract class AbstractConnectionFactory {

    private static SupportedConnectionTypes currentType = SupportedConnectionTypes.JDBC;

    public static IConnectionFactory getConnectionFactory() throws DAOException {
        IConnectionFactory connectionFactory = null;
        try {
            switch (currentType) {
                case JDBC:
                    connectionFactory = JDBCConnectionFactory.getInstance();
                    break;
                default:
                    throw new RuntimeException("Connection type= " + currentType + " not supported");
            }
        }catch (Throwable t){
            throw new DAOException("Exception in class AbstractConnectionFactory.", t );
        }
        return connectionFactory;
    }
}
