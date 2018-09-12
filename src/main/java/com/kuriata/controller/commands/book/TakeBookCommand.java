package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Book;
import com.kuriata.entities.TakenBook;
import com.kuriata.entities.User;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.BookManipulationService;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class TakeBookCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String bookIdString = req.getParameter("bookId");
        int bookId = 0;
        if (bookIdString != null && !bookIdString.isEmpty()) {
            bookId = Integer.parseInt(bookIdString);
        }
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        BookManipulationService bookManipulationService = new BookManipulationService(
                AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
        );
        try {
            if(bookManipulationService.isBookAllreadyTakenByUser(bookId, currentUser.getId())
                    || bookManipulationService.getBookQuantity(bookId)<= 0){
                req.setAttribute("errorMessage", MessagesProvider.getMessage("error.bookCantBeTaken"));
                return "/jsp/message.jsp";
            }else {
                bookManipulationService.setBookTakenByUser(bookId, currentUser.getId());
                req.setAttribute("operationMessage", MessagesProvider.getMessage("message.bookTaken"));
                return "/jsp/message.jsp";
            }

        } catch (ServiceException e) {
            throw new ServletException("Can't set book taken by user.", e);
        }
    }
}
