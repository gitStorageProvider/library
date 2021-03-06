package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IUserBookDAO;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBookDAO implements IUserBookDAO {
    public static final String USER_BOOK_TABLE_NAME = "user_book";
    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM " + USER_BOOK_TABLE_NAME;
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + USER_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + USER_BOOK_TABLE_NAME + "(user_id, book_id, date) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + USER_BOOK_TABLE_NAME + " SET user_id = ?, book_id = ?, date = ? WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + USER_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_USER_ID_AND_BOOK_ID = "DELETE FROM " + USER_BOOK_TABLE_NAME + " WHERE user_id = ? AND book_id = ?";
    public static final String SQL_SELECT_RECORDS_BY_USER_ID = "SELECT * FROM " + USER_BOOK_TABLE_NAME + " WHERE user_id = ?";
    public static final String SQL_SELECT_RECORDS_BY_BOOK_ID = "SELECT * FROM " + USER_BOOK_TABLE_NAME + " WHERE book_id = ?";
    public static final String SQL_COUNT_BOOKS_TAKEN_BY_USER_WITH_ID = "SELECT COUNT(*) FROM " + USER_BOOK_TABLE_NAME + " WHERE user_id = ?";


    @Override
    public List<UserBook> findAll() throws DAOException {
        List<UserBook> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             Statement statement = wrappedConnection.createStatement()) {
            resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getTimestamp("date").toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract all entities from DB.", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
        return result;
    }

    @Override
    public UserBook findById(int id) throws DAOException {
        UserBook result = null;
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getTimestamp("date").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new DAOException("Can't find entity by id.", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
        return result;
    }

    @Override
    public int insert(UserBook entity) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDate()));
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't insert entity.", e);
        }
        return result;
    }

    @Override
    public boolean update(UserBook entity) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDate()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Can't update entity.", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_RECORD_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Can't delete entity.", e);
        }
    }


    @Override
    public List<UserBook> findAllByUserId(int userId) throws DAOException {
        List<UserBook> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORDS_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add( new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getTimestamp("date").toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract entities).", e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
        return result;
    }

    @Override
    public List<UserBook> findAllByBookId(int bookId) throws DAOException {
        List<UserBook> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORDS_BY_BOOK_ID)) {
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add( new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getTimestamp("date").toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract entities.", e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
        return result;
    }

    @Override
    public int countBooksTakenByUser(int userId) throws DAOException {
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_COUNT_BOOKS_TAKEN_BY_USER_WITH_ID)) {
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int result = resultSet.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new DAOException("Can't count books taken by user.", e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
    }

    @Override
    public int insert(WrappedConnection wrappedConnection, UserBook entity) throws DAOException {
        int result = 0;

        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getDate()));
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    result = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't insert entity.", e);
        }
        return result;
    }

    @Override
    public boolean deleteByUserIdAndBookId(WrappedConnection wrappedConnection, int userId, int bookId) throws DAOException {
        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_RECORD_BY_USER_ID_AND_BOOK_ID)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't delete entities.", e);
        }
    }
}
