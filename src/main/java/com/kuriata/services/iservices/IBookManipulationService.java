package com.kuriata.services.iservices;

import com.kuriata.entities.Book;
import com.kuriata.entities.Shelf;
import com.kuriata.entities.User;

import java.util.List;

public interface IBookManipulationService {
    boolean addBookInLibrary (Book book, int Quantity, Shelf shelf);

    boolean changeBookInLibrary (Book book);

    boolean deleteBookFromLibrary (int bookId);

    boolean setBookTakenByUser (int bookId, User user);

    boolean setBookAuthor (int bookId, int authorId);

    boolean deleteAuthorFromBook (int authorId, int bookId);

    boolean returnBook(int userId, int bookId);

    boolean changeBookQuantity (int bookId, int quantity);

    //ToDo: add method to interface and implement it
//    List<Book> findBook (String criteria)


}
