package com.kuriata.dao.connection;

import com.kuriata.exceptions.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCConnectionFactory implements IConnectionFactory {
    protected DataSource dataSource;
    protected WrappedConnection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCConnectionFactory.class);


    private JDBCConnectionFactory() throws DAOException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            dataSource = (DataSource)
                    envCtx.lookup("jdbc/libraryDB");

            LOGGER.debug("DataSource initialized by context.xml");
        } catch (Exception e) {
            throw new DAOException("JDBCConnectionFactory exception while creating.", e);
        }
    }

    public static IConnectionFactory getInstance() {
        return ConnectionFactoryJDBCSingleton.INSTANCE.get();
    }


    @Override
    public WrappedConnection getConnection() throws DAOException {
        try {
            if (this.connection == null
                    || this.connection.isClosed()) {
                this.connection = new WrappedConnection(dataSource.getConnection());
            }
        } catch (SQLException e) {
            throw new DAOException("Abstract DAOManager exception. Error in connection opening", e);
        }
        return connection;
    }

    @Override
    public void closeConnection() throws DAOException {
        try {
            if (this.connection != null
                    && !this.connection.isClosed())
                this.connection.close();
        } catch (SQLException e) {
            throw new DAOException("Abstract DAOManager exception. Couldn't close connection", e);
        }
    }

    private static class ConnectionFactoryJDBCSingleton {

        public static final ThreadLocal<JDBCConnectionFactory> INSTANCE;

        //ToDo: розібратись, як працює наступний блок коду
        static {
            ThreadLocal<JDBCConnectionFactory> dm;
            try {
                dm = ThreadLocal.withInitial(() -> {
                    try {
                        return new JDBCConnectionFactory();
                    } catch (Exception e) {
                        LOGGER.error("Unexpected error", e);
                        return null;
                    }
                });
            } catch (NullPointerException e) {
                LOGGER.error("Unexpected error", e);
                dm = null;
            }
            INSTANCE = dm;
        }
    }
}
