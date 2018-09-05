package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.UserBook;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllTakenBooks implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        List<UserBook> userBookList;
        IBookService bookService = new BookService(
                AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                AbstractDAOFactory.getDAOFactory().getUsersDAO()
        );


        return null;
    }
}
