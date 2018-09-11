package com.kuriata.dao.mysqldao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IBookDAO;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IBookDAO {
    public static final String BOOKS_TABLE_NAME = "books";
    public static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM " + BOOKS_TABLE_NAME;
    public static final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM " + BOOKS_TABLE_NAME + " WHERE id = ?";
    public static final String SQL_INSERT_BOOK = "INSERT INTO " + BOOKS_TABLE_NAME + "(short_title, full_title, description, keywords) VALUES (?, ?, ?, ?)";
    public static final String SQL_UPDATE_BOOK = "UPDATE " + BOOKS_TABLE_NAME + " SET short_title = ?, full_title = ?, description = ?, keywords = ? WHERE id = ?";
    public static final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM " + BOOKS_TABLE_NAME + " WHERE id = ?";
    public static final String BEGIN_SQL_FIND_BOOK_BY_TITLE_KEYWORDS = "SELECT * FROM books WHERE books.full_title LIKE '%?%'";
    public static final String ADDITION_SQL_BOOK_FIND_BY_TITLE_KEYWORDS = " AND books.full_title LIKE '%?%'";
    public static final String BEGIN_SQL_FIND_BOOK_BY_KEYWORDS = "SELECT * FROM books WHERE books.keywords LIKE '%?%'";
    public static final String ADDITION_SQL_BOOK_FIND_BY_KEYWORDS = " AND books.keywords LIKE '%?%'";

    @Override
    public List<Book> findAll() throws DAOException {
        List<Book> result = new ArrayList<>();
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            Statement statement = wrappedConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                result.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("short_title"),
                        resultSet.getString("full_title"),
                        resultSet.getString("description"),
                        resultSet.getString("keywords")));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract all books from DB.", e);
        }
        return result;
    }

    @Override
    public Book findById(int id) throws DAOException {
        Book result = null;
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_SELECT_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Book(resultSet.getInt("id"),
                        resultSet.getString("short_title"),
                        resultSet.getString("full_title"),
                        resultSet.getString("description"),
                        resultSet.getString("keywords"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(Book book) throws DAOException {
        int result = 0;

        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection()) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_BOOK);
            preparedStatement.setString(1, book.getShortTitle());
            preparedStatement.setString(2, book.getFullTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getKeyWords());
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

    public boolean update(Book book) throws DAOException {
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();) {
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_UPDATE_BOOK);
            preparedStatement.setString(1, book.getShortTitle());
            preparedStatement.setString(2, book.getFullTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getKeyWords());
            preparedStatement.setInt(5, book.getId());
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
            PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int insert(WrappedConnection wrappedConnection, Book book) throws DAOException {
        int result = 0;

        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_INSERT_BOOK)) {

            preparedStatement.setString(1, book.getShortTitle());
            preparedStatement.setString(2, book.getFullTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getKeyWords());
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
    public boolean deleteById(WrappedConnection wrappedConnection, int id) throws DAOException {
        try (PreparedStatement preparedStatement = wrappedConnection.prepareStatement(SQL_DELETE_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Book> findByFullTitle(String... keyWords) throws DAOException {
        List<Book> result = new ArrayList<>();
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
                Statement statement = wrappedConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(generateTitleSearchString(keyWords));
            while (resultSet.next()) {
                result.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("short_title"),
                        resultSet.getString("full_title"),
                        resultSet.getString("description"),
                        resultSet.getString("keywords")));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract books from DB.", e);
        }
        return result;

    }

    @Override
    public List<Book> findByKeyWords(String... keyWords) throws DAOException {
        List<Book> result = new ArrayList<>();
        try (final WrappedConnection wrappedConnection = AbstractConnectionFactory.getConnectionFactory().getConnection();
             Statement statement = wrappedConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(generateKeyworsSearchString(keyWords));
            while (resultSet.next()) {
                result.add(new Book(resultSet.getInt("id"),
                        resultSet.getString("short_title"),
                        resultSet.getString("full_title"),
                        resultSet.getString("description"),
                        resultSet.getString("keywords")));
            }
        } catch (SQLException e) {
            throw new DAOException("Can't extract books from DB.", e);
        }
        return result;
    }

    private String generateTitleSearchString(String ... keyWords) throws DAOException {
        if(keyWords.length < 1){
            throw new DAOException("Less then one key word!", new IllegalArgumentException());
        }
        StringBuilder result = new StringBuilder();
        for(int index = 0; index < keyWords.length; ++index){
            if(index == 0){
                result.append(BEGIN_SQL_FIND_BOOK_BY_TITLE_KEYWORDS.replace("?", keyWords[index]));
            }else {
                result.append(ADDITION_SQL_BOOK_FIND_BY_TITLE_KEYWORDS.replace("?", keyWords[index]));
            }
        }
        return result.toString();
    }

    private String generateKeyworsSearchString(String ... keyWords) throws DAOException {
        if(keyWords.length < 1){
            throw new DAOException("Less then one key word!", new IllegalArgumentException());
        }
        StringBuilder result = new StringBuilder();
        for(int index = 0; index < keyWords.length; ++index){
            if(index == 0){
                result.append(BEGIN_SQL_FIND_BOOK_BY_KEYWORDS.replace("?", keyWords[index]));
            }else {
                result.append(ADDITION_SQL_BOOK_FIND_BY_KEYWORDS.replace("?", keyWords[index]));
            }
        }
        return result.toString();
    }

}
