package com.kuriata.services.iservices;

import com.kuriata.entities.Book;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IBookManipulationService {
    boolean addBook(Book book, List<Integer> authorsIdList, int quantity, int shelfId) throws ServiceException;
    //ToDo: uncomment and realize method functionality in all layers
//    boolean changeBookInLibrary (Book book);

    boolean deleteBookFromLibrary(int bookId) throws ServiceException;

    boolean setBookTakenByUser(int bookId, int userId) throws ServiceException;

    boolean returnBook(int recordId) throws ServiceException;

    public boolean isBookAllreadyTakenByUser(int bookId, int userId) throws ServiceException;

    public int getBookQuantity(int bookId) throws ServiceException;


}
