package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.User;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.UserService;
import com.kuriata.services.iservices.IUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowAllUsersCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        List<User> usersList = new ArrayList<>();
//        User u1 = new User(1, "UserLogin1", "UserPassword1", "UserEmail1", "UserFirstName", "UserLastName", "+380971234567");
//        User u2 = new User(2, "UserLogin2", "UserPassword2", "UserEmail2", "UserFirstName", "UserLastName", "+380971234567");
//        User u3 = new User(3, "UserLogin3", "UserPassword3", "UserEmail3", "UserFirstName", "UserLastName", "+380971234567");
//        User u4 = new User(5, "UserLogin4", "UserPassword4", "UserEmail4", "UserFirstName", "UserLastName", "+380971234567");
//
//        usersList.add(u1);
//        usersList.add(u2);
//        usersList.add(u3);
//        usersList.add(u4);

        try{
            IUserService userService = new UserService(
                    AbstractDAOFactory.getDAOFactory().getUsersDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserAuthorityDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getAuthorityDAO()
            );
            usersList = userService.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("usersList", usersList);

        return "/jsp/users.jsp";
    }
}
