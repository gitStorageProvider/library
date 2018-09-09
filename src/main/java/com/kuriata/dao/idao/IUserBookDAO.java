package com.kuriata.dao.idao;

import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IUserBookDAO extends IDAO<UserBook> {
    List<UserBook> findAllByUserId(int userId) throws DAOException;
    List<UserBook> findAllByBookId(int bookId) throws DAOException;
    int countBooksTakenByUser(int userId) throws DAOException;
    //method used in transactions (while User takes Book)
    int insert(WrappedConnection wrappedConnection, UserBook entity) throws DAOException;
    //method used in transactions (while User returns Book)
    boolean deleteByUserIdAndBookId(WrappedConnection wrappedConnection, int userId, int bookId) throws DAOException;
}
