package com.kuriata.controller.commands.author;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.AuthorService;
import com.kuriata.validators.IValidator;
import com.kuriata.validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddAuthorCommand implements ICommand {
    private String fullName;
    private String details;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {

        String requestMethodName = req.getMethod();

        if (requestMethodName.equalsIgnoreCase("GET")) {
            return "/jsp/addAuthor.jsp";
        } else {
            extractRequestParameters(req);
            setRequestAttributes(req);

            if(checkFields(req)){
            Author author = new Author(0, fullName, details);
            try {
                AuthorService authorService = new AuthorService(
                        AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                        AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
                );
                authorService.addAuthor(author);
                req.setAttribute("authorsOperationMessage", MessagesProvider.getMessage("message.authorAdded"));
            } catch (Exception e) {
                e.printStackTrace();
            }}else {
                return "/jsp/addAuthor.jsp";
            }
        }
        return "/jsp/authors.jsp";
    }

    private void extractRequestParameters(HttpServletRequest req) {
        HttpSession session = req.getSession();
        fullName = req.getParameter("authorName");
        details = req.getParameter("authorDetails");
    }

    private void setRequestAttributes(HttpServletRequest req) {
        req.setAttribute("authorName", fullName);
        req.setAttribute("authorDetails", details);
    }

    private boolean checkFields(HttpServletRequest req) throws ServletException {
        IValidator validator =  Validator.getInstance();
        boolean isAuthorFullNameValid = true;
        boolean isAuthorDetailsValid = true;

        if (!validator.isAuthorFullNameValid(fullName)) {
            req.setAttribute("authorNameErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isAuthorFullNameValid = false;
        }
        if (!validator.isAuthorDetailsValid(details)) {
            req.setAttribute("authorDetailsErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isAuthorDetailsValid = false;
        }
        return isAuthorFullNameValid && isAuthorDetailsValid;
    }
}
