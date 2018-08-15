package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IDAO;
import com.kuriata.dao.idao.IShelfBookDAO;
import com.kuriata.entities.Author;
import com.kuriata.entities.ShelfBook;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShelfBookDAO implements IShelfBookDAO {
    public static final String SHELF_BOOK_TABLE_NAME = "shelf_book";
    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM " + SHELF_BOOK_TABLE_NAME;
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + SHELF_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + SHELF_BOOK_TABLE_NAME + "(shelf_id, book_id, quantity) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + SHELF_BOOK_TABLE_NAME + " SET shelf_id = ?, book_id = ?, quantity = ? WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + SHELF_BOOK_TABLE_NAME + " WHERE id = ?";


    @Override
    public List<ShelfBook> findAll() throws DAOException {
        List<ShelfBook> result = new ArrayList<>();

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new ShelfBook(resultSet.getInt("id"),
                        resultSet.getInt("shelf_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ShelfBook findById(int id) throws DAOException {
        ShelfBook result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new ShelfBook(resultSet.getInt("id"),
                        resultSet.getInt("shelf_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insert(ShelfBook entity) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setInt(1, entity.getShelfId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setInt(3, entity.getQuantity());
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
    public boolean update(ShelfBook entity) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setInt(1, entity.getShelfId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setInt(3, entity.getQuantity());
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
