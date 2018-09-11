package com.kuriata.controller.commands.book;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.exceptions.ServletException;
import com.kuriata.services.impl.AuthorService;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.iservices.IAuthorService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindBooksCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String requestMethodName = req.getMethod();

        if (requestMethodName.equalsIgnoreCase("GET")) {
            return "/jsp/search.jsp";
        } else {
//        String searchCriteria = "authorName";
            String searchCriteria = req.getParameter("searchCriteria");
//        String[] searchKeyWords = {"Vance"};
            String[] searchKeyWords = req.getParameter("searchKeyWords").split(" ");

            List<Book> bookList = findBooks(searchCriteria, searchKeyWords);
            Map<Book, List<Author>> booksAuthorsMap = getBooksAndAuthors(bookList);

            req.setAttribute("booksAuthorsMap", booksAuthorsMap);

            return "/jsp/search.jsp";
        }
    }

    private List<Book> findBooks(String searchCriteria, String... searchKeyWords) throws ServletException {
        IBookService bookService = new BookService(
                AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                AbstractDAOFactory.getDAOFactory().getUsersDAO()
        );
        AuthorService authorService = new AuthorService(
                AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
        );
        List<Book> result = new ArrayList<>();
        try {
            switch (searchCriteria) {
                case "bookTitle":
                    result = bookService.findBooksByTitle(searchKeyWords);
                    break;
                case "bookKeyWords":
                    result = bookService.findBooksByKeywords(searchKeyWords);
                    break;
                case "authorName":
                    List<Author> authorList = authorService.findAuthorByName(searchKeyWords);
                    List<Integer> uniqueBookIdList = new ArrayList<>();
                    for (Author author : authorList) {
                        List<Integer> bookIdList = authorService.findBooksIdByAuthorId(author.getId());
                        for (int oneBookId : bookIdList) {
                            if (!uniqueBookIdList.contains(oneBookId)) {
                                uniqueBookIdList.add(oneBookId);
                            }
                        }
                    }
                    for (int bookId : uniqueBookIdList) {
                        result.add(bookService.getBookById(bookId));
                    }
                    break;
                default:
                    throw new ServletException("undefined search criteria", new IllegalArgumentException());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    Map<Book, List<Author>> getBooksAndAuthors(List<Book> bookList) throws ServletException {
        Map<Book, List<Author>> result = new HashMap<>();
        try {
            IAuthorService authorService = new AuthorService(
                    AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                    AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
            );
            for (Book oneBook : bookList) {
                List<Author> authorsList = authorService.findAllAuthorsByBookId(oneBook.getId());
                result.put(oneBook, authorsList);
            }
        } catch (ServiceException e) {
            throw new ServletException("Can't extract all authors of one book.", new IllegalArgumentException());
        }
        return result;
    }
}
