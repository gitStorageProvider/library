package com.kuriata.controller.commands.shelf;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.DAOException;
import com.kuriata.services.impl.ShelfService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddShelfCommand implements ICommand {
    private String name;
    private String address;
    private String description;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String requestMethodName = req.getMethod();
        if (requestMethodName.equalsIgnoreCase("GET")) {
            return "/jsp/addShelf.jsp";
        } else {
            if (true) {
                extractRequestParameters(req);
                setRequestAttributes(req);

                Shelf shelfToAdd = new Shelf(0, name, address, description);
                try {
                    ShelfService shelfService = new ShelfService(
                            AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                            AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
                    );
                    shelfService.addShelf(shelfToAdd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return "/jsp/shelves.jsp";
            }

            //ToDo: check entered data and if invalid redirect to same page

            return "/jsp/addShelf.jsp";
        }
    }

    private void extractRequestParameters(HttpServletRequest req) {
        HttpSession session = req.getSession();
        name = req.getParameter("shelfName");
        address = req.getParameter("shelfAddress");
        description = req.getParameter("shelfDescription");
    }

    private void setRequestAttributes(HttpServletRequest req) {
        req.setAttribute("shelfName", name);
        req.setAttribute("shelfAddress", address);
        req.setAttribute("shelfDescription", description);

    }
}
