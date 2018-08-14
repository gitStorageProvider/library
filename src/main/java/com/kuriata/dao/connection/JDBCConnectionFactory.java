package com.kuriata.dao.connection;

import com.kuriata.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;

public class JDBCConnectionFactory implements IConnectionFactory {
    private DataSource dataSource;
    protected WrappedConnection connection;
//    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCConnectionFactory.class);
    private static final JDBCConnectionFactory instance = new JDBCConnectionFactory();


    private JDBCConnectionFactory(){
        try {
//            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/libraryDB") ;
            //ToDo: delete commented code bellow
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            dataSource = (DataSource)
                    envCtx.lookup("jdbc/libraryDB");

//            LOGGER.debug("DataSource initialized by context.xml");
        } catch (Exception e) {
//            LOGGER.error("JDBCConnectionFactory exception while creating.", e);
        }
    }

    public static IConnectionFactory getInstance() {
        return instance;
    }


    @Override
    public WrappedConnection getConnection() throws DAOException {
        try {
            if (this.connection == null || this.connection.isClosed()) {
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
}
