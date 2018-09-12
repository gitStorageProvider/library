package com.kuriata.controller.commands.shelf;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.ShelfService;
import com.kuriata.services.iservices.IShelfService;

import javax.servlet.http.HttpServletRequest;

public class DeleteShelfCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        int shelfId = Integer.parseInt(req.getParameter("shelfId"));
        try {
            IShelfService shelfService = new ShelfService(
                    AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
            );
            if(shelfService.isShelfUsed(shelfId)){
                req.setAttribute("errorMessage", MessagesProvider.getMessage("error.shelfCantBeDeleted"));
            } else {
                shelfService.deleteShelfById(shelfId);
                req.setAttribute("operationMessage", MessagesProvider.getMessage("message.shelfDeleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/message.jsp";
    }
}
