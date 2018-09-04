package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IShelfDAO;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShelfDAO implements IShelfDAO {
    public static final String SHELVES_TABLE_NAME = "shelves";
    public static final String SQL_SELECT_ALL_SHELVES = "SELECT * FROM " + SHELVES_TABLE_NAME;
    public static final String SQL_SELECT_SHELF_BY_ID = "SELECT * FROM " + SHELVES_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_SHELF = "INSERT INTO " + SHELVES_TABLE_NAME + "(name, address, description) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_SHELF = "UPDATE " + SHELVES_TABLE_NAME + " SET name = ?, address = ?, description = ? WHERE id = ?";
    public static final String SQL_DELETE_SHELF_BY_ID = "DELETE FROM " + SHELVES_TABLE_NAME + " WHERE id = ?";


    @Override
    public List<Shelf> findAll() throws DAOException {
        List<Shelf> result = new ArrayList<>();
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SHELVES);
            while (resultSet.next()) {
                result.add(new Shelf(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Shelf findById(int id) throws DAOException {
        Shelf result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_SHELF_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Shelf(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(Shelf shelf) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_SHELF);
            preparedStatement.setString(1, shelf.getName());
            preparedStatement.setString(2, shelf.getAddress());
            preparedStatement.setString(3, shelf.getDescription());
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

    public boolean update(Shelf shelf) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_SHELF);
            preparedStatement.setString(1, shelf.getName());
            preparedStatement.setString(2, shelf.getAddress());
            preparedStatement.setString(3, shelf.getDescription());
            preparedStatement.setInt(4, shelf.getId());
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
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_SHELF_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
