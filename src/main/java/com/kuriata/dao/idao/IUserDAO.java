package com.kuriata.dao.idao;

import com.kuriata.entities.User;
import com.kuriata.exceptions.DAOException;

public interface IUserDAO extends IDAO{
    public User findByLogin(String userLogin) throws DAOException;
}
