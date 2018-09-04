package com.kuriata.dao.idao;

import com.kuriata.entities.BookAuthor;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IBookAuthorDAO extends IDAO<BookAuthor> {
    int getWrittenBooksCountByAuthor (int authorId) throws DAOException;
    List<Integer> getAuthorsIdByBookId(int bookId) throws DAOException;
}
