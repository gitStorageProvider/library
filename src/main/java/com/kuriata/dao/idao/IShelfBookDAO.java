package com.kuriata.dao.idao;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.entities.ShelfBook;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IShelfBookDAO extends IDAO<ShelfBook> {
    List<Integer> findAvailableBooksId() throws DAOException;

    int booksCountOnShelfWithId(int shelfId) throws DAOException;

    int booksQuantityByBookId(int bookId) throws DAOException;

    //method used in transactions (while add new Book to the system)
    int insert(WrappedConnection wrappedConnection, ShelfBook entity) throws DAOException;

    //method used in transactions (while delete Book from the system)
    boolean deleteByBookId(WrappedConnection wrappedConnection, int bookId) throws DAOException;

    //method used in transactions (while User takes/returns Book)
    boolean updateQuantityByBookId(WrappedConnection wrappedConnection, int quantity, int bookId);

}
