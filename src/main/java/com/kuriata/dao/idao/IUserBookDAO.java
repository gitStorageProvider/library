package com.kuriata.dao.idao;

import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IUserBookDAO extends IDAO<UserBook> {
    List<UserBook> findAllByUserId(int userId) throws DAOException;
}
