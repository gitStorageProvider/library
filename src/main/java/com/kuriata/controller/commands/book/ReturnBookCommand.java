package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.BookManipulationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ReturnBookCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String recordIdString = req.getParameter("recordId");
        int recordId = 0;
        if (recordIdString != null && !recordIdString.isEmpty()) {
            recordId = Integer.parseInt(recordIdString);
        }
        BookManipulationService bookManipulationService = new BookManipulationService(
                AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
        );
        try {
            if(bookManipulationService.returnBook(recordId)){
                req.setAttribute("operationMessage", MessagesProvider.getMessage("message.bookReturned"));
                return "/jsp/message.jsp";
            }else {
                req.setAttribute("errorMessage", MessagesProvider.getMessage("error.bookCantBeReturned"));
                return "/jsp/message.jsp";
            }
        } catch (ServiceException e) {
            throw new ServletException("Can't return book from user to library.", e);
        }

    }
}
