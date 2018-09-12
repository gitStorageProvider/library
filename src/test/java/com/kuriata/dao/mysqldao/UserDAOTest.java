package com.kuriata.dao.mysqldao;

import com.kuriata.dao.idao.IUserDAO;
import com.kuriata.entities.User;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class UserDAOTest {
    @Mock
    IUserDAO userDAO;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void testFindByIdUserMethod() throws Exception {
        User user = new User();
        when(userDAO.findById(1)).thenReturn(user);
        User result = userDAO.findById(1);
        User user2 = new User();
        assertEquals(result, user2);
    }

    @org.junit.Test
    public void insert() throws Exception {
        assertEquals("Dummy test", 1, 1);
    }




}