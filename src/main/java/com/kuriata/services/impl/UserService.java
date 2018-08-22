package com.kuriata.services.impl;

import com.kuriata.dao.idao.IAuthorityDAO;
import com.kuriata.dao.idao.IUserAuthorityDAO;
import com.kuriata.dao.idao.IUserDAO;
import com.kuriata.entities.Authority;
import com.kuriata.entities.User;
import com.kuriata.entities.UserAuthority;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IUserService;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    //ToDo: make userDAO initialization in constructor
    private IUserDAO userDAO;
    private IUserAuthorityDAO userAuthorityDAO;
    private IAuthorityDAO authorityDAO;

    public UserService(IUserDAO userDAO, IUserAuthorityDAO userAuthorityDAO, IAuthorityDAO authorityDAO) {
        this.userDAO = userDAO;
        this.userAuthorityDAO = userAuthorityDAO;
        this.authorityDAO = authorityDAO;
    }

    @Override
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

    public Authority getUserAuthorities(User user) throws ServiceException {
        Authority combinedAuthority = new Authority(); combinedAuthority.setAuthorityName("combinedAuthority");
        List<UserAuthority> userAuthorities;
        try {
            userAuthorities = userAuthorityDAO.findAllByUserId(user.getId());
            if (!userAuthorities.isEmpty()) {
                for (UserAuthority one : userAuthorities){
                    System.out.println(one);
                    //Combine all authorities from table user_authority into one authority
                    //that contain all admin and reader privileges
                    Authority temp = authorityDAO.findById(one.getAuthorityId());
                    combinedAuthority.setReader(combinedAuthority.isReader() || temp.isReader());
                    combinedAuthority.setAdmin(combinedAuthority.isAdmin() || temp.isAdmin());
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return combinedAuthority;
    }

    @Override
    public boolean deleteByID(int id) {
        try {
            return userDAO.deleteById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //ToDo: check is method body correct or not
    @Override
    public boolean isLoginAlreadyExist(String login) {
        try {
            return !userDAO.loginNotRegistered(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean registerNewUser(User user) {
        int generatedId;
        try {
            generatedId = userDAO.insert(user);
            if (generatedId != 0) {
                return true;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try {
            result = userDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByID(int id) {
        User result = null;
        try {
            result = userDAO.findById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }


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
