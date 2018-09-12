package com.kuriata.services.impl;

import com.kuriata.dao.idao.IAuthorityDAO;
import com.kuriata.dao.idao.IUserAuthorityDAO;
import com.kuriata.dao.idao.IUserBookDAO;
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
    private IUserDAO userDAO;
    private IUserAuthorityDAO userAuthorityDAO;
    private IUserBookDAO userBookDAO;
    private IAuthorityDAO authorityDAO;

    public UserService(IUserDAO userDAO, IUserAuthorityDAO userAuthorityDAO, IUserBookDAO userBookDAO, IAuthorityDAO authorityDAO) {
        this.userDAO = userDAO;
        this.userAuthorityDAO = userAuthorityDAO;
        this.userBookDAO = userBookDAO;
        this.authorityDAO = authorityDAO;
    }

    @Override
    public User login(String userLogin, String userPassword) throws ServiceException {
        try {
            User user = null;
            if (userLogin != null) {
                user = userDAO.findByLogin(userLogin);
            }
            if (user != null && user.getPassword().equals(userPassword)) {
                return user;
            }
            return null;
        } catch (DAOException e) {
            throw new ServiceException("User not retrieved successfully", e);
        }
    }

    public Authority getUserAuthorities(User user) throws ServiceException {
        Authority combinedAuthority = new Authority();
        combinedAuthority.setAuthorityName("combinedAuthority");
        List<UserAuthority> userAuthorities;
        try {
            userAuthorities = userAuthorityDAO.findAllByUserId(user.getId());
            if (!userAuthorities.isEmpty()) {
                for (UserAuthority one : userAuthorities) {
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
    public boolean deleteByID(int userId) throws ServiceException {
        try {
            if(isUserUsed(userId)){
                throw new ServiceException("User can't be deleted. Used in DB.", new IllegalArgumentException());
            } else {
                return userAuthorityDAO.deleteByUserId(userId)&&userDAO.deleteById(userId);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //ToDo: realize method below
    @Override
    public boolean enableAdminPrivilegies() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    //ToDo: realize method below
    @Override
    public boolean update(User user) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isUserUsed(int userId) throws ServiceException {
        try {
            return userBookDAO.countBooksTakenByUser(userId) > 0;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }

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
        int generatedUserId;
        int generatedUserAuthorityId;
        try {
            generatedUserId = userDAO.insert(user);
            generatedUserAuthorityId = userAuthorityDAO.insert(new UserAuthority(0, generatedUserId, 1));
            if (generatedUserId != 0 && generatedUserAuthorityId != 0) {
                return true;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> result = new ArrayList<>();
        try {
            result = userDAO.findAll();
        } catch (DAOException e) {
//            e.printStackTrace();
            throw new ServiceException("Can't retrieve all users.", e);
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
}
