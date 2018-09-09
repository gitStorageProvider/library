package com.kuriata.controller.commands.shelf;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.ServletException;
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
            System.out.println("IS SHELF WITH ID=" + shelfId + " USED IN DB: " + shelfService.isShelfUsed(shelfId));
            if(shelfService.isShelfUsed(shelfId)){
                req.setAttribute("shelvesErrorMessage", "Shelf is used in DB.");
            } else {
                shelfService.deleteShelfById(shelfId);
                System.out.println("helfService.deleteShelfById(shelfId) called!!!!");
                req.setAttribute("shelvesOperationMessage", "Shelf deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/shelves.jsp";
    }
}
