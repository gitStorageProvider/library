package com.kuriata.controller.commands.shelf;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Shelf;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.ShelfService;
import com.kuriata.services.iservices.IShelfService;
import com.kuriata.validators.IValidator;
import com.kuriata.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
            extractRequestParameters(req);
            setRequestAttributes(req);
            if (checkFields(req)) {
                //if all entered fields values valid - add new shelf
                Shelf shelfToAdd = new Shelf(0, name, address, description);
                try {
                    IShelfService shelfService = new ShelfService(
                            AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                            AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
                    );
                    shelfService.addShelf(shelfToAdd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute("operationMessage", MessagesProvider.getMessage("message.shelfAdded"));
                return "/jsp/message.jsp";
            }
            //else set (in checkFields() method) error messages an return the same page
            return "/jsp/addShelf.jsp";
        }
    }

    private void extractRequestParameters(HttpServletRequest req) {
        name = req.getParameter("shelfName");
        address = req.getParameter("shelfAddress");
        description = req.getParameter("shelfDescription");
    }

    private void setRequestAttributes(HttpServletRequest req) {
        req.setAttribute("shelfName", name);
        req.setAttribute("shelfAddress", address);
        req.setAttribute("shelfDescription", description);
    }

    private boolean checkFields(HttpServletRequest req) {
        IValidator validator = Validator.getInstance();
        boolean isNameValid = true;
        boolean isAddressValid = true;
        boolean isDescriptionValid = true;

        if (!validator.isShelfeNameValid(name)) {
            req.setAttribute("shelfNameErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isNameValid = false;
        }
        if (!validator.isShelfAddressValid(address)) {
            req.setAttribute("shelfAddressErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isAddressValid = false;
        }
        if (!validator.isAuthorDetailsValid(description)) {
            req.setAttribute("shelfDescriptionErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isDescriptionValid = false;
        }
        return isNameValid && isAddressValid && isDescriptionValid;
    }
}
