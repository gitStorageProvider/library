package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.ServletException;
import com.kuriata.services.impl.UserService;
import com.kuriata.services.iservices.IUserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        try {
            IUserService userService = new UserService(
                    AbstractDAOFactory.getDAOFactory().getUsersDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserAuthorityDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getAuthorityDAO()
            );
            if (userService.isUserUsed(userId)) {
                req.setAttribute("usersErrorMessage", "Used is used in DB.");
            } else {
                userService.deleteByID(userId);
                req.setAttribute("usersOperationMessage", "User deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/users.jsp";
    }
}
