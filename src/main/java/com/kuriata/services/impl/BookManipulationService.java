package com.kuriata.services.impl;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.dao.idao.IBookAuthorDAO;
import com.kuriata.dao.idao.IBookDAO;
import com.kuriata.dao.idao.IShelfBookDAO;
import com.kuriata.dao.idao.IUserBookDAO;
import com.kuriata.entities.Book;
import com.kuriata.entities.BookAuthor;
import com.kuriata.entities.ShelfBook;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IBookManipulationService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class BookManipulationService implements IBookManipulationService {

    IBookDAO bookDAO;
    IUserBookDAO userBookDAO;
    IShelfBookDAO shelfBookDAO;
    IBookAuthorDAO bookAuthorDAO;

    public BookManipulationService(IBookDAO bookDAO, IUserBookDAO userBookDAO, IShelfBookDAO shelfBookDAO, IBookAuthorDAO bookAuthorDAO) {
        this.bookDAO = bookDAO;
        this.userBookDAO = userBookDAO;
        this.shelfBookDAO = shelfBookDAO;
        this.bookAuthorDAO = bookAuthorDAO;
    }

    @Override
    public boolean addBook(Book book, List<Integer> authorsIdList, int quantity, int shelfId) throws ServiceException {
        WrappedConnection connection = null;
        try {
            connection = AbstractConnectionFactory.getConnectionFactory().getConnection();
            connection.setAutoCommit(false);
            int bookId = bookDAO.insert(connection, book);
            for (int authorId : authorsIdList) {
                bookAuthorDAO.insert(connection, new BookAuthor(0, bookId, authorId));
            }
            shelfBookDAO.insert(connection, new ShelfBook(0, shelfId, bookId, quantity));
            connection.commit();
            return true;
        } catch (SQLException | DAOException e) {
            throw new ServiceException("Can't add book to the library.", e);
        } finally {
            if (connection != null) ;
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ServiceException("Can't close connection.", e);
            }
        }
    }

    @Override
    public boolean deleteBookFromLibrary(int bookId) throws ServiceException {
        WrappedConnection connection = null;
        try {
            if (isBookTaken(bookId)) {
                throw new ServiceException("Can't delete book taken by one or more users", new IllegalArgumentException());
            } else {
                connection = AbstractConnectionFactory.getConnectionFactory().getConnection();
                connection.setAutoCommit(false);
                bookAuthorDAO.deleteByBookId(connection, bookId);
                shelfBookDAO.deleteByBookId(connection, bookId);
                bookDAO.deleteById(connection, bookId);
                connection.commit();
                return true;
            }
        } catch (SQLException | DAOException e) {
            throw new ServiceException("Can't delete book from library.", e);
        } finally {
            if (connection != null) ;
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ServiceException("Can't close connection.", e);
            }
        }
    }

    public boolean isBookTaken(int bookId) throws ServiceException {
        List<UserBook> allUsersWhoTookTheBook = null;
        try {
            allUsersWhoTookTheBook = userBookDAO.findAllByBookId(bookId);
        } catch (DAOException e) {
            throw new ServiceException("Can't check is book taken or not.", e);
        }
        return allUsersWhoTookTheBook.size() > 0;
    }

    @Override
    public boolean setBookTakenByUser(int bookId, int userId) throws ServiceException {
        WrappedConnection connection = null;
        try {
            int bookQuantity = getBookQuantity(bookId);
            if (isBookAllreadyTakenByUser(bookId, userId) || bookQuantity <=0) {
                throw new ServiceException("Book can't be taken by user.", new IllegalArgumentException());
            } else {
                connection = AbstractConnectionFactory.getConnectionFactory().getConnection();
                connection.setAutoCommit(false);
                userBookDAO.insert(connection, new UserBook(0, userId, bookId, LocalDateTime.now()));
                shelfBookDAO.updateQuantityByBookId(connection, bookQuantity-1, bookId);
                connection.commit();
                return true;
            }
        } catch (SQLException | DAOException e) {
            throw new ServiceException("Can't delete book from library.", e);
        } finally {
            if (connection != null) ;
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ServiceException("Can't close connection.", e);
            }
        }
    }

    @Override
    public boolean returnBook(int recordId) throws ServiceException {
        WrappedConnection connection = null;
        try{
            UserBook record = userBookDAO.findById(recordId);
            int bookQuantity = getBookQuantity(record.getBookId());

            connection = AbstractConnectionFactory.getConnectionFactory().getConnection();
            connection.setAutoCommit(false);
            userBookDAO.deleteByUserIdAndBookId(connection, record.getUserId(), record.getBookId());
            shelfBookDAO.updateQuantityByBookId(connection, bookQuantity+1, record.getBookId());
            connection.commit();
            return true;
        } catch (SQLException | DAOException e) {
            throw new ServiceException("Can't return book from from user to library.", e);
        } finally {
            if (connection != null) ;
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ServiceException("Can't close connection.", e);
            }
        }

    }

    public boolean isBookAllreadyTakenByUser(int bookId, int userId) throws ServiceException {
        try {
            List<UserBook> takenBooks = userBookDAO.findAllByUserId(userId);
            for (UserBook takenBook : takenBooks) {
                if (takenBook.getBookId() == bookId) {
                    return true;
                }
            }
            return false;
        } catch (DAOException e) {
            throw new ServiceException("Can't check is book already taken by user or not.", e);
        }
    }

    public int getBookQuantity(int bookId) throws ServiceException {
        try {
            return shelfBookDAO.booksQuantityByBookId(bookId);
        } catch (DAOException e) {
            throw new ServiceException("Can't extract book quantity.", e);
        }
    }


}
