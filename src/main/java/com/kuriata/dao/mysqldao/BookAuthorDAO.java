package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IBookAuthorDAO;
import com.kuriata.entities.BookAuthor;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO implements IBookAuthorDAO {
    public static final String BOOK_AUTHOR_TABLE_NAME = "book_author";
    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM " + BOOK_AUTHOR_TABLE_NAME;
    public static final String SQL_SELECT_RECORD_BY_ID = "SELECT * FROM " + BOOK_AUTHOR_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_RECORD = "INSERT INTO " + BOOK_AUTHOR_TABLE_NAME + "(book_id, author_id) VALUES (?, ?)";
    public static final String SQL_UPDATE_RECORD = "UPDATE " + BOOK_AUTHOR_TABLE_NAME + " SET book_id = ?, author_id = ? WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_ID = "DELETE FROM " + BOOK_AUTHOR_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_DELETE_RECORD_BY_BOOK_ID = "DELETE FROM " + BOOK_AUTHOR_TABLE_NAME + " WHERE book_id = ?";
    public static final String SQL_SELECT_ALL_BOOKS_WRITTEN_BY_AUTHOR_WITH_ID = "SELECT * FROM " + BOOK_AUTHOR_TABLE_NAME + " WHERE author_id = ?";
    public static final String SQL_COUNT_BOOKS_WRITTEN_BY_AUTHOR_WITH_ID = "SELECT COUNT(*) FROM " + BOOK_AUTHOR_TABLE_NAME + " WHERE author_id = ?";
    public static final String SQL_SELECT_ALL_AUTHORS_ID_BY_BOOK_ID = "SELECT author_id FROM " + BOOK_AUTHOR_TABLE_NAME + " WHERE book_id = ?";

    @Override
    public List<BookAuthor> findAll() throws DAOException {
        List<BookAuthor> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             Statement statement = wrappedConnection.createStatement()) {
             resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                result.add(new BookAuthor(resultSet.getInt("id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("author_id")));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract all entities from DB.", e);
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
    public BookAuthor findById(int id) throws DAOException {
        BookAuthor result = null;
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_RECORD_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new BookAuthor(resultSet.getInt("id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("author_id"));
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


    public int insert(BookAuthor bookAuthor) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD)) {
            preparedStatement.setInt(1, bookAuthor.getBookId());
            preparedStatement.setInt(2, bookAuthor.getAuthorId());
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


    public boolean update(BookAuthor bookAuthor) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_RECORD);) {
            preparedStatement.setInt(1, bookAuthor.getBookId());
            preparedStatement.setInt(2, bookAuthor.getAuthorId());
            preparedStatement.setInt(3, bookAuthor.getId());
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
    public int getWrittenBooksCountByAuthor(int authorId) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_COUNT_BOOKS_WRITTEN_BY_AUTHOR_WITH_ID);) {
            preparedStatement.setInt(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Can't get written Books count .", e);
        }
    }

    @Override
    public List<Integer> getBooksIdByAuthorId(int authorId) throws DAOException {
        List<Integer> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_ALL_BOOKS_WRITTEN_BY_AUTHOR_WITH_ID)) {
            preparedStatement.setInt(1, authorId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new Integer(resultSet.getInt("book_id")));
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException("Can't get books id .", e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Can't close ResultSet.", e);
            }
        }

    }

    @Override
    public List<Integer> getAuthorsIdByBookId(int bookId) throws DAOException {
        List<Integer> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_ALL_AUTHORS_ID_BY_BOOK_ID);
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new Integer(resultSet.getInt("author_id")));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't get authors id.", e);
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
    public int insert(WrappedConnection wrappedConnection, BookAuthor entity) throws DAOException {
        int result = 0;

        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_RECORD)) {
            preparedStatement.setInt(1, entity.getBookId());
            preparedStatement.setInt(2, entity.getAuthorId());
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
            throw new DAOException("Can't delete entity by id.", e);
        }
    }
}
