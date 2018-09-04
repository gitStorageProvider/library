package com.kuriata.services.impl;

import com.kuriata.dao.idao.IAuthorityDAO;
import com.kuriata.dao.idao.IUserAuthorityDAO;
import com.kuriata.dao.idao.IUserBookDAO;
import com.kuriata.dao.idao.IUserDAO;
import com.kuriata.entities.User;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    IUserDAO userDAO;
    @Mock
    IUserAuthorityDAO userAuthorityDAO;
    @Mock
    IAuthorityDAO authorityDAO;
    @Mock
    IUserBookDAO userBookDAO;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login_whenDaoReturnsNull_returnNull() throws Exception {
        String userLogin = null;
        String userPassword = "fooBar";
        when(userDAO.findByLogin(userLogin)).thenReturn(null);
        UserService userService = new UserService(userDAO, userAuthorityDAO, userBookDAO, authorityDAO);
        User loginResult = userService.login(userLogin, userPassword);
        assertNull(loginResult);
    }

    @Test(expected = ServiceException.class)
    public void login_whenDaoThrowsException_throwException() throws Exception {
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        when(userDAO.findByLogin(userLogin)).thenThrow(new DAOException("something went wrong.", new Exception("cause")));
        UserService userService = new UserService(userDAO, userAuthorityDAO, userBookDAO, authorityDAO);
        User loginResult = userService.login(userLogin, userPassword);
        assertNull(loginResult);
    }

    @Test
    public void login_returnNewUser() throws Exception {
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        User user = new User(1, userLogin, userPassword, "email@host.com", "Foo", "Bar", "+380971234567");
        when(userDAO.findByLogin(userLogin)).thenReturn(user);
        UserService userService = new UserService(userDAO, userAuthorityDAO, userBookDAO, authorityDAO);
        User loginResult = userService.login(userLogin, userPassword);
        assertEquals(loginResult, user);
    }


    @Test
    public void getUserAuthorities() throws Exception {

    }

    @Test
    public void deleteByID() throws Exception {

    }

    @Test
    public void isLoginAlreadyExist() throws Exception {
    }

    @Test
    public void registerNewUser() throws Exception {
    }

    @Test
    public void findAll_returnUserList() throws Exception {
        User user = new User(1, "userLogin", "userPassword", "email@host.com", "Foo", "Bar", "+380971234567");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userDAO.findAll()).thenReturn(userList);
        UserService userService = new UserService(userDAO, userAuthorityDAO, userBookDAO, authorityDAO);
        List<User> result = userService.findAll();
        assertEquals(result, userList);
    }

    @Test(expected = ServiceException.class)
    public void findAll_whenDaoThrowsException_throwException() throws Exception {
        when(userDAO.findAll()).thenThrow(new DAOException("something went wrong.", new Exception("cause")));
        UserService userService = new UserService(userDAO, userAuthorityDAO, userBookDAO, authorityDAO);
        List<User> result = userService.findAll();
    }

    @Test
    public void findByID() throws Exception {

    }

}