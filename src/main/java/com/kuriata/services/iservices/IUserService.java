package com.kuriata.services.iservices;

import com.kuriata.entities.User;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IUserService {
    User login(String userLogin, String userPassword) throws ServiceException;

    boolean registerUser(User user);

    boolean enableAdminPrivilegies();

    List<User> findAll();

    User findByID(int id);

    boolean update(User user);

    boolean deleteByID(int id);

    boolean isLoginAlreadyExist(String login);
}
