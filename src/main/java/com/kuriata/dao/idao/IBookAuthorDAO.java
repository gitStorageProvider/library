package com.kuriata.dao.idao;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.entities.BookAuthor;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IBookAuthorDAO extends IDAO<BookAuthor> {
    int getWrittenBooksCountByAuthor (int authorId) throws DAOException;
    List<Integer> getBooksIdByAuthorId(int authorId) throws DAOException;
    List<Integer> getAuthorsIdByBookId(int bookId) throws DAOException;
    //method used in transactions (while add new Book to the system)
    int insert(WrappedConnection wrappedConnection, BookAuthor entity) throws DAOException;
    //method used in transactions (while delete Book from the system)
    boolean deleteByBookId(WrappedConnection wrappedConnection, int bookId) throws DAOException;
}
