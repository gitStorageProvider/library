package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.entities.Book;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;
import com.kuriata.services.impl.AuthorService;
import com.kuriata.services.impl.BookManipulationService;
import com.kuriata.services.impl.ShelfService;
import com.kuriata.services.iservices.IAuthorService;
import com.kuriata.services.iservices.IShelfService;
import com.kuriata.validators.IValidator;
import com.kuriata.validators.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddBookCommand implements ICommand {
    private String bookShortTitle;
    private String bookFullTitle;
    private String bookDescription;
    private String bookKeyWords;
    private List<Integer> uniqueAuthorIdList;
    private int shelfId;
    private int bookQuantity;

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
            //get all available authors and shelves (registered in system)
            authorList = authorService.getAllAuthors();
            shelvesList = shelfService.getAllShelves();
            if (requestMethodName.equalsIgnoreCase("GET")) {
                req.setAttribute("authorsList", authorList);
                req.setAttribute("shelvesList", shelvesList);
                return "/jsp/addBook.jsp";
            } else {
                extractRequestParameters(req);
                setRequestAttributes(req);
                if (checkFields(req)) {
                    BookManipulationService bookManipulationService = new BookManipulationService(
                            AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                            AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                            AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                            AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
                    );
                    bookManipulationService.addBook(
                            new Book(0, bookShortTitle, bookFullTitle, bookDescription, bookKeyWords),
                            uniqueAuthorIdList, bookQuantity, shelfId
                    );
                    req.setAttribute("operationMessage", MessagesProvider.getMessage("message.bookAdded"));
                    return "/jsp/message.jsp";
                } else {
                    //if entered data is invalid - get all available authors and shelves (registered in system)
                    //put them into request and show the same page
                    authorList = authorService.getAllAuthors();
                    shelvesList = shelfService.getAllShelves();
                    req.setAttribute("authorsList", authorList);
                    req.setAttribute("shelvesList", shelvesList);
                    return "/jsp/addBook.jsp";
                }
            }
        } catch (ServiceException e) {
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
        String author1IdString = req.getParameter("author1Id");
        String author2IdString = req.getParameter("author2Id");
        String author3IdString = req.getParameter("author3Id");
        String author4IdString = req.getParameter("author4Id");
        String shelfIdString = req.getParameter("shelfId");
        String bookQuantityString = req.getParameter("bookQuantity");

        uniqueAuthorIdList = new ArrayList();
        if (author1IdString != null && !author1IdString.isEmpty()) {
            int tempValue = Integer.parseInt(author1IdString);
            //add first author id without check contains the collection (uniqueAuthorIdList)
            //it or not
            uniqueAuthorIdList.add(tempValue);
        }
        if (author2IdString != null && !author2IdString.isEmpty()) {
            int tempValue = Integer.parseInt(author2IdString);
            //if collection (uniqueAuthorIdList) not contains second author id - add it
            if (!uniqueAuthorIdList.contains(tempValue)) {
                uniqueAuthorIdList.add(tempValue);
            }
        }
        if (author3IdString != null && !author3IdString.isEmpty()) {
            int tempValue = Integer.parseInt(author3IdString);
            if (!uniqueAuthorIdList.contains(tempValue)) {
                uniqueAuthorIdList.add(tempValue);
            }
        }
        if (author4IdString != null && !author4IdString.isEmpty()) {
            int tempValue = Integer.parseInt(author4IdString);
            //if collection (uniqueAuthorIdList) not contains second author id - add it
            if (!uniqueAuthorIdList.contains(tempValue)) {
                uniqueAuthorIdList.add(tempValue);
            }
        }
        if (shelfIdString != null && !shelfIdString.isEmpty()) {
            shelfId = Integer.parseInt(shelfIdString);
        }
        if (bookQuantityString != null && !bookQuantityString.isEmpty()) {
            try {
                bookQuantity = Integer.parseInt(bookQuantityString);
            } catch (NumberFormatException e) {
                bookQuantity = 0;
            }
        }
    }

    private void setRequestAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession();
        req.setAttribute("bookShortTitle", bookShortTitle);
        req.setAttribute("bookFullTitle", bookFullTitle);
        req.setAttribute("bookDescription", bookDescription);
        req.setAttribute("bookKeyWords", bookKeyWords);
        req.setAttribute("bookQuantity", bookQuantity);
    }

    private boolean checkFields(HttpServletRequest req) throws ServletException {
        IValidator validator = new Validator();
        boolean isBookShortTitleValid = true;
        boolean isBookFullTitleValid = true;
        boolean isBookDescriptionValid = true;
        boolean isBookKeyWordsValid = true;
        boolean isUniqueAuthorIdListValid = true;
        boolean isShelfIdValid = true;
        boolean isBookQuantityValid = true;

        if (!validator.isBookShortTitleValid(bookShortTitle)) {
            req.setAttribute("bookShortTitleErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isBookShortTitleValid = false;
        }
        if (!validator.isBookFullTitleValid(bookFullTitle)) {
            req.setAttribute("bookFullTitleErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isBookFullTitleValid = false;
        }
        if (!validator.isBookDescriptionValid(bookDescription)) {
            req.setAttribute("bookDescriptionErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isBookDescriptionValid = false;
        }
        if (!validator.isBookKeyworsValid(bookKeyWords)) {
            req.setAttribute("bookKeyWordsErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isBookKeyWordsValid = false;
        }
        if (!validator.isBookAuthorsValid(uniqueAuthorIdList)) {
            req.setAttribute("bookAuthorsErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isUniqueAuthorIdListValid = false;
        }
        if (!validator.isBookQuantityValid(bookQuantity)) {
            req.setAttribute("bookQuantityErrorMessage", MessagesProvider.getMessage("message.wrongInput"));
            isBookQuantityValid = false;
        }
        return isBookShortTitleValid && isBookFullTitleValid && isBookDescriptionValid
                && isBookKeyWordsValid && isUniqueAuthorIdListValid &&
                isShelfIdValid && isBookQuantityValid;
    }
}