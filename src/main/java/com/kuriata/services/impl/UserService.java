package com.kuriata.services.impl;

import com.kuriata.dao.idao.IUserDAO;
import com.kuriata.entities.User;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IUserService;

import java.util.List;

//ToDo: class must implement IUserService
public class UserService {
    //ToDo: make userDAO initialization in constructor
    private IUserDAO userDAO;

    //ToDo: uncomment annotation in next row
//    @Override
    public User login(String userLogin, String userPassword) throws ServiceException {
        try {
            User user = userDAO.findByLogin(userLogin);
            if (user != null && user.getPassword().equals(userPassword)) {
                return user;
            }
            return null;
        } catch (DAOException e) {
            throw new ServiceException("User not retrieved successfully", e);
        }
    }

    //ToDo: implement method properly
//    @Override
//    public boolean registerUser(User user) {
//        return false;
//    }

    //ToDo: implement method properly
//    @Override
//    public boolean enableAdminPrivilegies() {
//        return false;
//    }

    //ToDo: implement method properly
//    @Override
//    public List<User> findAll() {
//        return null;
//    }

    //ToDo: implement method properly
//    @Override
//    public User findByID(int id) {
//        return null;
//    }

    //ToDo: implement method properly
//    @Override
//    public boolean update(User user) {
//        return false;
//    }

    //ToDo: implement method properly
//    @Override
//    public boolean deleteByID(int id) {
//        return false;
//    }

    //ToDo: implement method properly
//    @Override
//    public boolean isLoginAlreadyExist(String login) {
//        return false;
//    }
}
