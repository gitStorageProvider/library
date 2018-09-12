package com.kuriata.controller.commands.author;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;
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
            if (authorService.isAuthorUsed(authorId)) {
                req.setAttribute("errorMessage", MessagesProvider.getMessage("error.authorCantBeDeleted"));
                return "/jsp/message.jsp";
            } else {
                authorService.deleteAuthorById(authorId);
                req.setAttribute("operationMessage",MessagesProvider.getMessage("message.authorDeleted"));
                return "/jsp/message.jsp";
            }
        } catch (ServiceException e) {
            throw new ServletException("Author not deleted.", e);
        }
    }
}
