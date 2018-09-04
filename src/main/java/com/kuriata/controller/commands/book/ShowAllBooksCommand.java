package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.AuthorService;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.iservices.IAuthorService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowAllBooksCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {
        Map<Book, List<Author>> allBooksAuthorsMap = new HashMap<>();
        try {
            IBookService bookService = new BookService(
                    AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUsersDAO()
            );
            IAuthorService authorService = new AuthorService(
                    AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                    AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
            );
            List<Book> allBooksList = bookService.getAllBooks();
            for(Book oneBook : allBooksList){
                List<Author> authorsList = authorService.getAllAuthorsByBookId(oneBook.getId());
                allBooksAuthorsMap.put(oneBook, authorsList);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("allBooksAuthorsMap", allBooksAuthorsMap);

        return "/jsp/booksAll.jsp";
    }
}
