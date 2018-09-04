package com.kuriata.services.iservices;

import com.kuriata.entities.Book;
import com.kuriata.entities.TakenBook;
import com.kuriata.entities.User;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface IBookService {
    List<Book> getAllBooks() throws ServiceException;

    Map<Book, Integer> getAllAvailableBooks() throws ServiceException;

    List<TakenBook> getAllTakenBooks() throws ServiceException;

    List<TakenBook> getAllBooksTakenByUser (int userId) throws ServiceException;


}
