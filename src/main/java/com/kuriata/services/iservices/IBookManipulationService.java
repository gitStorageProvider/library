package com.kuriata.services.iservices;

import com.kuriata.entities.Book;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IBookManipulationService {
    boolean addBook(Book book, List<Integer> authorsIdList, int quantity, int shelfId) throws ServiceException;

//    boolean changeBookInLibrary (Book book);

    boolean deleteBookFromLibrary(int bookId) throws ServiceException;

    boolean setBookTakenByUser(int bookId, int userId) throws ServiceException;

    boolean returnBook(int recordId) throws ServiceException;

    //ToDo: add method to interface and implement it
//    List<Book> findBook (String criteria)
    public boolean isBookAllreadyTakenByUser(int bookId, int userId) throws ServiceException;

    public int getBookQuantity(int bookId) throws ServiceException;


}
