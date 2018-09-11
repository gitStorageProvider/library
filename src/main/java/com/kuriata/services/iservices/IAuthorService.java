package com.kuriata.services.iservices;

import com.kuriata.entities.Author;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IAuthorService {
    List<Author> findAllAuthors() throws ServiceException;

    List<Author> findAllAuthorsByBookId(int bookId) throws ServiceException;

    List<Author> findAuthorByName(String ... keyWords) throws ServiceException;

    List<Integer> findBooksIdByAuthorId(int authorId) throws ServiceException;

    boolean editAuthor(Author author) throws ServiceException;

    boolean addAuthor(Author author) throws ServiceException;

    boolean deleteAuthorById(int authorId) throws ServiceException;

    boolean isAuthorUsed(int authorId) throws ServiceException;
}
