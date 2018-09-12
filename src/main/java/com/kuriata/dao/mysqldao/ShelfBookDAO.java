package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IShelfBookDAO;
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
    public static final String SQL_SELECT_ALL_ID_OF_AVAILABLE_BOOKS = "SELECT book_id FROM " + SHELF_BOOK_TABLE_NAME + " WHERE quantity > 0";
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + SHELF_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + SHELF_BOOK_TABLE_NAME + "(shelf_id, book_id, quantity) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + SHELF_BOOK_TABLE_NAME + " SET shelf_id = ?, book_id = ?, quantity = ? WHERE id = ?";
    public static final String SQL_UPDATE_QUANTITY_BY_BOOK_ID = "UPDATE " + SHELF_BOOK_TABLE_NAME + " SET quantity = ? WHERE book_id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + SHELF_BOOK_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_BOOK_ID = "DELETE FROM " + SHELF_BOOK_TABLE_NAME + " WHERE book_id = ?";
    public static final String SQL_COUNT_BOOKS_ON_SHELF_WITH_ID = "SELECT Count(*) FROM " + SHELF_BOOK_TABLE_NAME + " WHERE shelf_id = ?";
    public static final String SQL_COUNT_QUANTITY_OF_BOOKS_WITH_ID = "SELECT quantity FROM " + SHELF_BOOK_TABLE_NAME + " WHERE book_id = ?";


    @Override
    public List<ShelfBook> findAll() throws DAOException {
        List<ShelfBook> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             Statement statement = wrappedConnection.createStatement()) {
            resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new ShelfBook(resultSet.getInt("id"),
                        resultSet.getInt("shelf_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("quantity")));
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
    public ShelfBook findById(int id) throws DAOException {
        ShelfBook result = null;
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new ShelfBook(resultSet.getInt("id"),
                        resultSet.getInt("shelf_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("quantity"));
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
    public int insert(ShelfBook entity) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD);) {
            preparedStatement.setInt(1, entity.getShelfId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setInt(3, entity.getQuantity());
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
    public boolean update(ShelfBook entity) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD);) {
            preparedStatement.setInt(1, entity.getShelfId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setInt(3, entity.getQuantity());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Can't update entity.", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_RECORD_BY_ID);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Can't delete entity.", e);
        }
    }

    @Override
    public List<Integer> findAvailableBooksId() throws DAOException {
        List<Integer> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             Statement statement = wrappedConnection.createStatement();) {
            resultSet = statement.executeQuery(SQL_SELECT_ALL_ID_OF_AVAILABLE_BOOKS);
            while (resultSet.next()) {
                result.add(new Integer(resultSet.getInt("book_id")));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't find all available books.", e);
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
    public int booksCountOnShelfWithId(int shelfId) throws DAOException {
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_COUNT_BOOKS_ON_SHELF_WITH_ID);) {
            preparedStatement.setInt(1, shelfId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Can't cont books on shelf.", e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
    }

    @Override
    public int booksQuantityByBookId(int bookId) throws DAOException {
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_COUNT_QUANTITY_OF_BOOKS_WITH_ID);) {
            preparedStatement.setInt(1, bookId);
             resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("quantity");
        } catch (SQLException e) {
            throw new DAOException("Can't get boos quantity.", e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }
    }

    @Override
    public int insert(WrappedConnection wrappedConnection, ShelfBook entity) throws DAOException {
        int result = 0;

        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD)) {
            preparedStatement.setInt(1, entity.getShelfId());
            preparedStatement.setInt(2, entity.getBookId());
            preparedStatement.setInt(3, entity.getQuantity());
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
    public boolean deleteByBookId(WrappedConnection wrappedConnection, int bookId) throws DAOException {
        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_RECORD_BY_BOOK_ID)) {
            preparedStatement.setInt(1, bookId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't delete entity.", e);
        }
    }

    @Override
    public boolean updateQuantityByBookId(WrappedConnection wrappedConnection, int quantity, int bookId) throws DAOException {
        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_QUANTITY_BY_BOOK_ID)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, bookId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't update entity.", e);
        }
    }
}
