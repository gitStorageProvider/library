package com.kuriata.services.impl;

import com.kuriata.dao.idao.IBookDAO;
import com.kuriata.dao.idao.IShelfBookDAO;
import com.kuriata.dao.idao.IUserBookDAO;
import com.kuriata.entities.Book;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IBookService;

import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {

    IBookDAO bookDAO;
    IShelfBookDAO shelfBookDAO;
    IUserBookDAO userBookDAO;

    public BookService(IBookDAO bookDAO, IShelfBookDAO shelfBookDAO, IUserBookDAO userBookDAO) {
        this.bookDAO = bookDAO;
        this.shelfBookDAO = shelfBookDAO;
        this.userBookDAO = userBookDAO;
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
    public List<Book> getAllAvailableBooks() throws ServiceException {
        List<Integer> availableBooksIdList;
        List<Book> availableBooksList= new ArrayList<>();;
        try {
            availableBooksIdList = shelfBookDAO.findAvailableBooksId();
            for(int one: availableBooksIdList){
                availableBooksList.add(bookDAO.findById(one));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return availableBooksList;
    }

    @Override
    public List<UserBook> getAllTakenBooks() {
        List<UserBook> result = new ArrayList<>();
        try{
            result = userBookDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<UserBook> getAllBooksTakenByUser(int userId) throws ServiceException {
        List<UserBook> result = new ArrayList<>();
        try{
            result = userBookDAO.findAllByUserId(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
