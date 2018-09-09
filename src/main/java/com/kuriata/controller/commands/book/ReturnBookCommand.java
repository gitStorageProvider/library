package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.services.impl.BookManipulationService;

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
                req.setAttribute("operationMessage", "Book returned.");
                return "/jsp/message.jsp";
            }else {
                req.setAttribute("errorMessage", "Book can't be returned.");
                return "/jsp/message.jsp";
            }
        } catch (ServiceException e) {
            throw new ServletException("Can't return book from user to library.", e);
        }

    }
}
