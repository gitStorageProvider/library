package com.kuriata.dao.idao;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IBookDAO extends IDAO<Book> {
    //method used in transactions (while add new Book to the system)
    int insert(WrappedConnection wrappedConnection, Book book) throws DAOException;
    //method used in transactions (while delete Book from the system)
    boolean deleteById(WrappedConnection wrappedConnection, int id) throws DAOException;

    List<Book> findByFullTitle(String ... keyWords) throws DAOException;
    List<Book> findByKeyWords(String ... keyWords) throws DAOException;
}
