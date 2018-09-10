package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.TakenBook;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class ShowAllTakenBooks implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        try {
            List<TakenBook> userBookList;
            IBookService bookService = new BookService(
                    AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUsersDAO()
            );
            userBookList = bookService.getAllTakenBooks();
            req.setAttribute("allTakenBooksList", userBookList);
            return "/jsp/booksTakenAll.jsp";
        } catch (ServiceException e) {
            throw new ServletException("Can't get allTakenBooksList from service layer.", e);
        }
    }
}
