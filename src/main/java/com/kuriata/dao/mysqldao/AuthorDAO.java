package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IAuthorDAO;
import com.kuriata.entities.Author;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements IAuthorDAO {
    public static final String AUTHORS_TABLE_NAME = "authors";
    public static final String SQL_SELECT_ALL_AUTHORS = "SELECT * FROM " + AUTHORS_TABLE_NAME;
    public static final String SQL_SELECT_AUTHOR_BY_ID = "SELECT * FROM " + AUTHORS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_AUTHOR = "INSERT INTO " + AUTHORS_TABLE_NAME + "(full_name, details) VALUES (?, ?)";
    public static final String SQL_UPDATE_AUTHOR = "UPDATE " + AUTHORS_TABLE_NAME + " SET full_name = ?, details = ? WHERE id = ?";
    public static final String SQL_DELETE_AUTHOR_BY_ID = "DELETE FROM " + AUTHORS_TABLE_NAME + " WHERE id = ?";

    @Override
    public List<Author> findAll() throws DAOException {
        List<Author> result = new ArrayList<>();

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_AUTHORS);
            while (resultSet.next()) {
                result.add(new Author(resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("details")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Author findById(int id) throws DAOException {
        Author result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_AUTHOR_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Author(resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("details"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Author author) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_AUTHOR);
            preparedStatement.setString(1, author.getFullName());
            preparedStatement.setString(2, author.getDetails());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Author author) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_AUTHOR);
            preparedStatement.setString(1, author.getFullName());
            preparedStatement.setString(2, author.getDetails());
            preparedStatement.setInt(3, author.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_AUTHOR_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
