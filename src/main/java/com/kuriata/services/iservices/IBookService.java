package com.kuriata.services.iservices;

import com.kuriata.entities.Book;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface IBookService {
    List<Book> getAllBooks() throws ServiceException;

    List<Book> getAllAvailableBooks() throws ServiceException;

    List<UserBook> getAllTakenBooks() throws ServiceException;

    List<UserBook> getAllBooksTakenByUser (int userId) throws ServiceException;

}
