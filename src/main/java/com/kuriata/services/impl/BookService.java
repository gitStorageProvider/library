package com.kuriata.services.impl;

import com.kuriata.dao.idao.IBookDAO;
import com.kuriata.dao.idao.IShelfBookDAO;
import com.kuriata.dao.idao.IUserBookDAO;
import com.kuriata.dao.idao.IUserDAO;
import com.kuriata.entities.Book;
import com.kuriata.entities.TakenBook;
import com.kuriata.entities.User;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IBookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService implements IBookService {

    IBookDAO bookDAO;
    IShelfBookDAO shelfBookDAO;
    IUserBookDAO userBookDAO;
    private IUserDAO userDAO;

    public BookService(IBookDAO bookDAO, IShelfBookDAO shelfBookDAO, IUserBookDAO userBookDAO, IUserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.shelfBookDAO = shelfBookDAO;
        this.userBookDAO = userBookDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        try {
            return bookDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Can't get all books in BookService", e);
        }
    }

    @Override
    public Map<Book, Integer> getAllAvailableBooks() throws ServiceException {
        List<Integer> availableBooksIdList;
        Map<Book, Integer> availableBooks = new HashMap<>();
        try {
            availableBooksIdList = shelfBookDAO.findAvailableBooksId();
            for (int one : availableBooksIdList) {
                Book book = bookDAO.findById(one);
                Integer quantity = shelfBookDAO.booksQuantityByBookId(one);
                availableBooks.put(book, quantity);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return availableBooks;
    }

    @Override
    public List<TakenBook> getAllTakenBooks() {
        List<TakenBook> result = new ArrayList<>();
        List<UserBook> userBookList;
        try {
            userBookList = userBookDAO.findAll();
            for (UserBook oneRecord : userBookList) {
                Book book = bookDAO.findById(oneRecord.getBookId());
                User user = userDAO.findById(oneRecord.getUserId());
                result.add(new TakenBook(oneRecord, book, user));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TakenBook> getAllBooksTakenByUser(int userId) throws ServiceException {
        List<TakenBook> result = new ArrayList<>();
        List<UserBook> userBookList;
        try {
            userBookList = userBookDAO.findAllByUserId(userId);
            for (UserBook oneRecord : userBookList) {
                Book book = bookDAO.findById(oneRecord.getBookId());
                User user = userDAO.findById(oneRecord.getUserId());
                result.add(new TakenBook(oneRecord, book, user));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
