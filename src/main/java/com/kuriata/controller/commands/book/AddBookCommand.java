package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.AuthorService;
import com.kuriata.services.impl.ShelfService;
import com.kuriata.services.iservices.IAuthorService;
import com.kuriata.services.iservices.IShelfService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddBookCommand implements ICommand {
    private String bookShortTitle;
    private String bookFullTitle;
    private String bookDescription;
    private String bookKeyWords;
    private int author1Id;
    private int author2Id;
    private int author3Id;
    private int author4Id;
    private int shelfId;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String requestMethodName = req.getMethod();
        List<Author> authorList = new ArrayList<>();
        List<Shelf> shelvesList = new ArrayList<>();
        try {
            IShelfService shelfService = new ShelfService(
                    AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
            );
            IAuthorService authorService = new AuthorService(
                    AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                    AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
            );
            authorList = authorService.getAllAuthors();
            shelvesList = shelfService.getAllShelves();

            if (requestMethodName.equalsIgnoreCase("GET")) {
                req.setAttribute("authorsList", authorList);
                req.setAttribute("shelvesList", shelvesList);
                return "/jsp/addBook.jsp";
            } else {
                if (true) {
                    extractRequestParameters(req);
                    setRequestAttributes(req);

                    req.setAttribute("bookShortTitleErrorMessage", bookShortTitle);
                    req.setAttribute("bookFullTitleErrorMessage", bookFullTitle);
                    req.setAttribute("bookDescriptionErrorMessage", bookDescription);
                    req.setAttribute("bookKeyWordsMessage", bookKeyWords);

                    return "/jsp/addBook.jsp";
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

        private void extractRequestParameters(HttpServletRequest req) {
        HttpSession session = req.getSession();
        bookShortTitle = req.getParameter("bookShortTitle");
        bookFullTitle = req.getParameter("bookFullTitle");
        bookDescription = req.getParameter("bookDescription");
        bookKeyWords = req.getParameter("bookKeyWords");
        author1Id = Integer.parseInt(req.getParameter("author1Id"));
        author2Id = Integer.parseInt(req.getParameter("author2Id"));
        author4Id = Integer.parseInt(req.getParameter("author4Id"));
        author1Id = Integer.parseInt(req.getParameter("author1Id"));
        shelfId = Integer.parseInt(req.getParameter("shelfId"));
    }

    private void setRequestAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession();
        req.setAttribute("bookShortTitle", bookShortTitle);
        req.setAttribute("bookFullTitle", bookFullTitle);
        req.setAttribute("bookDescription", bookDescription);
        req.setAttribute("bookKeyWords", bookKeyWords);
    }
}