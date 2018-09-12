package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.User;
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

        try{
            IUserService userService = new UserService(
                    AbstractDAOFactory.getDAOFactory().getUsersDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserAuthorityDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getAuthorityDAO()
            );
            usersList = userService.findAll();
        }  catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("usersList", usersList);

        return "/jsp/users.jsp";
    }
}
