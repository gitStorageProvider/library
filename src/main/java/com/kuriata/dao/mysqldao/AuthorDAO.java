package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.JDBCConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IAuthorDAO;
import com.kuriata.entities.Author;
import com.kuriata.entities.BaseEntity;
import com.kuriata.exceptions.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AuthorDAO implements IAuthorDAO {
    public static final String AUTHORS_TABLE_NAME = "authors";
    public static final String SQL_SELECT_ALL_AUTHORS = "SELECT * FROM " + AUTHORS_TABLE_NAME;
    public static final String SQL_SELECT_AUTHOR_BY_ID = "SELECT * FROM " + AUTHORS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_AUTHOR = "INSERT INTO " + AUTHORS_TABLE_NAME + "(full_name, country) VALUES (?, ?)";
    public static final String SQL_UPDATE_ADMIN = "UPDATE " + AUTHORS_TABLE_NAME + " SET full_name = ?, country = ? WHERE id = ?";
    public static final String SQL_DELETE_ADMIN_BY_ID = "DELETE FROM " + AUTHORS_TABLE_NAME + " WHERE id = ?";

    private WrappedConnection wrappedConnection;

    public AuthorDAO(WrappedConnection wrappedConnection) {
        this.wrappedConnection = wrappedConnection;
    }

    @Override
    public List<Author> findAll() throws DAOException {
        WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
        try {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_AUTHORS);
            while(resultSet.next()){
                System.out.println(resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                wrappedConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
