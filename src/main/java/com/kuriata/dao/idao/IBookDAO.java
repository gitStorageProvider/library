package com.kuriata.dao.idao;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.DAOException;

public interface IBookDAO extends IDAO<Book> {
    //method used in transactions (while add new Book to the system)
    int insert(WrappedConnection wrappedConnection, Book book) throws DAOException;
    //method used in transactions (while delete Book from the system)
    boolean deleteById(WrappedConnection wrappedConnection, int id) throws DAOException;

}
