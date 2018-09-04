package com.kuriata.services.iservices;

import com.kuriata.entities.Authority;
import com.kuriata.entities.User;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IUserService {

    User login(String userLogin, String userPassword) throws ServiceException;

    public Authority getUserAuthorities(User user) throws ServiceException;

    boolean isLoginAlreadyExist(String login) throws ServiceException;

    boolean registerNewUser(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User findByID(int id) throws ServiceException;

    boolean deleteByID(int id) throws ServiceException;

    boolean enableAdminPrivilegies() throws ServiceException;

    boolean update(User user) throws ServiceException;

    boolean isUserUsed(int userId) throws ServiceException;
}
