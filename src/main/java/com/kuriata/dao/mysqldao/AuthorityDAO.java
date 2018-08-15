package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IAuthorityDAO;
import com.kuriata.entities.Authority;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorityDAO implements IAuthorityDAO {
    public static final String AUTHORITY_TABLE_NAME = "authorities";
    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM " + AUTHORITY_TABLE_NAME;
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + AUTHORITY_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + AUTHORITY_TABLE_NAME + "(authority_name, reader_flag, admin_flag) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + AUTHORITY_TABLE_NAME + " SET authority_name = ?, reader_flag = ?, admin_flag = ? WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + AUTHORITY_TABLE_NAME + " WHERE id = ?";

    @Override
    public List<Authority> findAll() throws DAOException {
        List<Authority> result = new ArrayList<>();

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new Authority(resultSet.getInt("id"),
                        resultSet.getString("authority_name"),
                        resultSet.getBoolean("reader_flag"),
                        resultSet.getBoolean("admin_flag")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Authority findById(int id) throws DAOException {
        Authority result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Authority(resultSet.getInt("id"),
                        resultSet.getString("authority_name"),
                        resultSet.getBoolean("reader_flag"),
                        resultSet.getBoolean("admin_flag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(Authority entity) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setString(1, entity.getAuthorityName());
            preparedStatement.setBoolean(2, entity.isReader());
            preparedStatement.setBoolean(3, entity.isAdmin());
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
    public boolean update(Authority entity) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setString(1, entity.getAuthorityName());
            preparedStatement.setBoolean(2, entity.isReader());
            preparedStatement.setBoolean(3, entity.isAdmin());
            preparedStatement.setInt(4, entity.getId());
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
}
