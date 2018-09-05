package com.kuriata.dao.daofactory;

import com.kuriata.exceptions.DAOException;

public class AbstractDAOFactory {
    private static SupportedDatabases currentDatabase = SupportedDatabases.MYSQL;

    public static IDAOFactory getDAOFactory()  {
        IDAOFactory factory = null;
             switch (currentDatabase) {
                case MYSQL:
                    factory = new MySQLDAOFactory();
                    break;
                case ORALCE:
                    throw new UnsupportedOperationException("Oracle DAOFactory not supported by AbstractDAOFactory.");
                default:
                    throw new UnsupportedOperationException("AbstractDAOFactory not initialized.");
            }
        return factory;
    }

    private static synchronized void setDatabase(SupportedDatabases currentDatabase) {
        AbstractDAOFactory.currentDatabase = currentDatabase;
    }
}
