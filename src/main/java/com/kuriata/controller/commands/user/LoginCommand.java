package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Authority;
import com.kuriata.entities.User;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ICommand {
    //ToDo: realize method below
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = null;
        Authority authority = null;
        try {
            UserService userService = new UserService(
                    AbstractDAOFactory.getDAOFactory().getUsersDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserAuthorityDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getAuthorityDAO()
            );
            user = userService.login(login, password);
            if (user != null) {
                authority = userService.getUserAuthorities(user);

                session.setAttribute("isAdmin", authority.isAdmin());
                session.setAttribute("isReader", authority.isReader());

                session.setAttribute("user", user);
                session.removeAttribute("authorizationErrorMessage");
            }
            else {
                session.setAttribute("authorizationErrorMessage", "Wrong login/password");
            }
            //ToDo: services must throw only ServiceExceptions
        }  catch (ServiceException e) {
            e.printStackTrace();
        }
        return "/jsp/login.jsp";
    }
}
