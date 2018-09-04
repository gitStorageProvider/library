package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IUserAuthorityDAO;
import com.kuriata.entities.Authority;
import com.kuriata.entities.UserAuthority;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAuthorityDAO implements IUserAuthorityDAO {
    public static final String USER_AUTHORITY_TABLE_NAME = "user_authority";
    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM " + USER_AUTHORITY_TABLE_NAME;
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + USER_AUTHORITY_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + USER_AUTHORITY_TABLE_NAME + "(user_id, authority_id) VALUES (?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + USER_AUTHORITY_TABLE_NAME + " SET user_id = ?, authority_id = ? WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + USER_AUTHORITY_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_USER_ID = "DELETE FROM " + USER_AUTHORITY_TABLE_NAME + " WHERE user_id = ?";
    public static final String SQL_SELECT_ALL_RECORDS_BY_USER_ID = "SELECT * FROM " + USER_AUTHORITY_TABLE_NAME + " WHERE user_id = ?";

    @Override
    public List<UserAuthority> findAll() throws DAOException {
        List<UserAuthority> result = new ArrayList<>();

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new UserAuthority(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("authority_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UserAuthority findById(int id) throws DAOException {
        UserAuthority result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new UserAuthority(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("authority_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(UserAuthority entity) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2,entity.getAuthorityId());
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
    public boolean update(UserAuthority entity) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getAuthorityId());
            preparedStatement.setInt(3, entity.getId());
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
    public List<UserAuthority> findAllByUserId(int userId) throws DAOException{
        List<UserAuthority> result = new ArrayList<>();

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_ALL_RECORDS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new UserAuthority(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("authority_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteByUserId(int userId) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_RECORD_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
