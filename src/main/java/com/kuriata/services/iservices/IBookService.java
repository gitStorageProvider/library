package com.kuriata.services.iservices;

import com.kuriata.entities.Book;
import com.kuriata.entities.TakenBook;
import com.kuriata.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface IBookService {
    List<Book> getAllBooks() throws ServiceException;

    Book getBookById(int bookId) throws ServiceException;

    List<Book> findBooksByTitle(String ... keyWords) throws ServiceException;

    List<Book> findBooksByKeywords(String ... keyWords) throws ServiceException;

    Map<Book, Integer> getAllAvailableBooks() throws ServiceException;

    List<TakenBook> getAllTakenBooks() throws ServiceException;

    List<TakenBook> getAllBooksTakenByUser(int userId) throws ServiceException;
}
