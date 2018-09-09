package com.kuriata.controller.commands.author;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.services.impl.AuthorService;
import com.kuriata.services.iservices.IAuthorService;

import javax.servlet.http.HttpServletRequest;

public class DeleteAuthorCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        try {
            IAuthorService authorService = new AuthorService(
                    AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                    AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
            );
            //if author can`t be deleted - show error message and forward to same page
            //else - delete author
            System.out.println("IS AUTHOR WITH ID=" + authorId + " USED IN DB: " + authorService.isAuthorUsed(authorId));
            if (authorService.isAuthorUsed(authorId)) {
                req.setAttribute("authorsErrorMessage", "Author is used in DB.");
            } else {
                authorService.deleteAuthorById(authorId);
                req.setAttribute("authorsOperationMessage", "Author deleted.");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "/jsp/authors.jsp";
    }
}
