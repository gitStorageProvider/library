package com.kuriata.services.iservices;

import com.kuriata.entities.User;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IUserService {

    User login(String userLogin, String userPassword) throws ServiceException;

    boolean isLoginAlreadyExist(String login) throws ServiceException;

    boolean registerNewUser(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    User findByID(int id) throws ServiceException;

    boolean deleteByID(int id) throws ServiceException;

//    boolean enableAdminPrivilegies()throws ServiceException;

//  ToDo: think are methods bellow needed or not
//    boolean update(User user)throws ServiceException;
}
