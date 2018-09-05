package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ShowAvailableBooksCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        Map<Book, Integer> allAvailableBooksMap = new HashMap<>();
        try {
            IBookService bookService = new BookService(
                    AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUsersDAO()
            );
            allAvailableBooksMap = bookService.getAllAvailableBooks();
        }  catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("allAvailableBooksMap", allAvailableBooksMap);

        return "/jsp/booksAvailable.jsp";
    }
}