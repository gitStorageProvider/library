package com.kuriata.services.iservices;

import com.kuriata.entities.Author;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IAuthorService {
    List<Author> getAllAuthors() throws ServiceException;

    List<Author> getAllAuthorsByBookId(int bookId) throws ServiceException;

    boolean editAuthor(Author author) throws ServiceException;

    boolean addAuthor(Author author) throws ServiceException;

    boolean deleteAuthorById(int authorId) throws ServiceException;

    boolean isAuthorUsed(int authorId) throws ServiceException;
}
