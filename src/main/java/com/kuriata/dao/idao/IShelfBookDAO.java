package com.kuriata.dao.idao;

import com.kuriata.entities.Book;
import com.kuriata.entities.ShelfBook;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IShelfBookDAO extends IDAO<ShelfBook> {
    List<Integer> findAvailableBooksId() throws DAOException;
    int booksCountOnShelfWithId(int shelfId) throws DAOException;
    int booksQuantityByBookId(int bookId) throws DAOException;

}
