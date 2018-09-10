package com.kuriata.controller.commands.shelf;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.ShelfService;
import com.kuriata.services.iservices.IShelfService;
import com.kuriata.validators.IValidator;
import com.kuriata.validators.Validator;

import javax.servlet.http.HttpServletRequest;

public class EditShelfCommand implements ICommand {
    int shelfId;
    private String name;
    private String address;
    private String description;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String requestMethodName = req.getMethod();
        Shelf shelf = null;
        if (requestMethodName.equalsIgnoreCase("GET")) {
            shelfId = Integer.parseInt(req.getParameter("shelfId"));

            try {
                IShelfService shelfService = new ShelfService(
                        AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                        AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
                );
                shelf = shelfService.getShelfById(shelfId);
                setInitialValues(req, shelf);
                setRequestAttributes(req);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            return "/jsp/editShelf.jsp";
        } else {
            extractRequestParameters(req);
            setRequestAttributes(req);
            if (checkFields(req)) {
                //if all entered fields values valid - edit shelf in DAO
                shelf = new Shelf(shelfId, name, address, description);
                try {
                    IShelfService shelfService = new ShelfService(
                            AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                            AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
                    );
                    shelfService.editShelf(shelf);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute("operationMessage", "Shelve changed.");
                return "/jsp/message.jsp";
            }
            //else set all entered data an return the same page
            setRequestAttributes(req);
            return "/jsp/editShelf.jsp";
        }
    }

    private void setInitialValues(HttpServletRequest req, Shelf shelf) {
        name = shelf.getName();
        address = shelf.getAddress();
        description = shelf.getDescription();
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
        IValidator validator = new Validator();
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
