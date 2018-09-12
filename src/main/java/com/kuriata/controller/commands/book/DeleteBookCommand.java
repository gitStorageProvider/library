package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.BookManipulationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DeleteBookCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String bookIdString = req.getParameter("id");
        int bookId = 0;
        if (bookIdString != null && !bookIdString.isEmpty()) {
            bookId = Integer.parseInt(bookIdString);
        }

        BookManipulationService bookManipulationService = new BookManipulationService(
                AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
        );
        try {
            if (bookManipulationService.isBookTaken(bookId)) {
                req.setAttribute("errorMessage", MessagesProvider.getMessage("error.bookCantBeDeleted"));
                return "/jsp/message.jsp";
            }
            bookManipulationService.deleteBookFromLibrary(bookId);
            req.setAttribute("operationMessage", MessagesProvider.getMessage("message.bookDeleted"));
            return "/jsp/message.jsp";
        } catch (ServiceException e) {
            throw new ServletException("Can't delete book.", e);
        }
    }
}
