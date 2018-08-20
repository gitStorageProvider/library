package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IUserBookDAO;
import com.kuriata.entities.Author;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserBookDAO implements IUserBookDAO {
    public static final String USER_BOOK_TABLE_NAME = "user_book";
    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM " + USER_BOOK_TABLE_NAME;
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + USER_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + USER_BOOK_TABLE_NAME + "(user_id, book_id, date) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + USER_BOOK_TABLE_NAME + " SET user_id = ?, book_id = ?, date = ? WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + USER_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_SELECT_RECORDS_BY_USER_ID = "SELECT * FROM " + USER_BOOK_TABLE_NAME + " WHERE user_id = ?";

    @Override
    public List<UserBook> findAll() throws DAOException {
        List<UserBook> result = new ArrayList<>();

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        new java.util.Date(resultSet.getTimestamp("date").getTime())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UserBook findById(int id) throws DAOException {
        UserBook result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        new java.util.Date(resultSet.getTimestamp("date").getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            //ToDo: check is converting below correct, because stackOverflow recommends to use java.util.time
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(entity.getDate().getTime()));
            preparedStatement.setDate(3, (java.sql.Date) entity.getDate());
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
    public boolean update(UserBook entity) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getBookId());
            //ToDo: check is converting below correct, because stackOverflow recommends to use java.util.time
            preparedStatement.setDate(3, (java.sql.Date) entity.getDate());
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
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<UserBook> findAllByUserId(int userId) throws DAOException {
        List<UserBook> result = new ArrayList<>();
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORDS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add( new UserBook(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        new java.util.Date(resultSet.getTimestamp("date").getTime())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
