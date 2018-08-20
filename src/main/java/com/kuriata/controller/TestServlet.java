package com.kuriata.controller;

import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.dao.mysqldao.AuthorDAO;
import com.kuriata.dao.mysqldao.BookDAO;
import com.kuriata.entities.Author;
import com.kuriata.entities.Book;
import com.kuriata.entities.User;
import com.kuriata.entities.UserBook;
import com.kuriata.exceptions.DAOException;
import com.kuriata.services.impl.BookService;
import com.kuriata.services.impl.UserService;
import com.kuriata.services.iservices.IBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            testAuthorsDAO(resp);
//            testBooksDAO(resp);
//            testUserServices(resp);
//            testBooksService(resp);
            testBookService_available_and_taken(resp);

//        } catch (DAOException | ServiceException e) {
//            e.printStackTrace();
//        }
    }

    private void testBookService_available_and_taken(HttpServletResponse resp) {
        try {
            IBookService bookService = new BookService(
                    AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO());
            List<Book> bookList = bookService.getAllAvailableBooks();
            resp.getWriter().println("\n\n\nAvailable books:\n");
            for (Book one : bookList) {
                resp.getWriter().println(one + ";\n");
            }

            List<UserBook> bookList2 = bookService.getAllTakenBooks();
            resp.getWriter().println("\n\n\nTaken books:\n");
            for (UserBook one : bookList2) {
                resp.getWriter().println(one + ";\n");
            }

            List<UserBook> bookList3 = bookService.getAllBooksTakenByUser(1);
            resp.getWriter().println("\n\n\nTaken books by user with id = 1:\n");
            for (UserBook one : bookList3) {
                resp.getWriter().println(one + ";\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testBooksService(HttpServletResponse resp) {
        try {
            IBookService bookService = new BookService(
                    AbstractDAOFactory.getDAOFactory().getBooksDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO());
            List<Book> bookList = bookService.getAllBooks();
            for (Book one : bookList) {
                resp.getWriter().println(one + ";\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testAuthorsDAO(HttpServletResponse resp) throws DAOException, IOException {

        //AUTHORS_DAO
        AuthorDAO authorDAO = (AuthorDAO) AbstractDAOFactory.getDAOFactory().getAuthorsDAO();

        List<Author> authorList = authorDAO.findAll();
        resp.getWriter().println("\tList size of authors is: " + authorList.size());
        resp.getWriter().println("Print all list: " + authorList.size());
        for (Author one : authorList) {
            resp.getWriter().println(one + ";\n");
        }

        resp.getWriter().println("\n\tfind author by id = 1: \n");
        Author foundByIDAuthor = authorDAO.findById(1);
        resp.getWriter().println(foundByIDAuthor + ";\n");

        resp.getWriter().println("\n\tinsert new author with name=Andrii, country=Ukraine: \n");
        authorDAO.insert(new Author(1, "Andrii", "Ukraine"));
        resp.getWriter().println("Print all list: " + authorList.size());
        for (Author one : authorList) {
            resp.getWriter().println(one + ";\n");
        }

//        resp.getWriter().println("\n\tchange author with id=2 to root,root: \n");
//        Author author2change = authorDAO.findById(2);
//        author2change.setFullName("root");
//        author2change.setCountry("root");
//        authorDAO.update(author2change);
//        resp.getWriter().println("Print all list: " + authorList.size());
//        for (Author one : authorList) {
//            resp.getWriter().println(one + ";\n");
//        }

        resp.getWriter().println("\n\tdelete author with id=2: \n");
        authorDAO.deleteById(2);
        resp.getWriter().println("Print all list: " + authorList.size());
        for (Author one : authorList) {
            resp.getWriter().println(one + ";\n");
        }
    }

    private void testBooksDAO(HttpServletResponse resp) throws DAOException, IOException {
        //BOOKS_DAO
        StringBuilder sb = new StringBuilder();
        BookDAO bookDAO = (BookDAO) AbstractDAOFactory.getDAOFactory().getBooksDAO();

        List<Book> bookList = bookDAO.findAll();
        resp.getWriter().println("\tList size of books is: " + bookList.size());
        resp.getWriter().println("\tPrint all list: " + bookList.size());
        for (Book one : bookList) {
            resp.getWriter().println(one + ";\n");
        }

        resp.getWriter().println("\tFind book by id = 2: ");
        Book foundByIDBook = bookDAO.findById(2);
        resp.getWriter().println(foundByIDBook + ";\n");

//        resp.getWriter().println("\tinsert new book with shortTitle=root, fullTitle=rootroot, description=rooDescr, keyWords=rootKeyWords:");
//        bookDAO.insert(new Book(1, "root", "rootroot", "rooDescr", "rootKeyWords"));
//        resp.getWriter().println("Print all list: " + bookList.size());
//        for (Book one : bookList) {
//            resp.getWriter().println(one + ";\n");
//        }

        resp.getWriter().println("\n\tchange book with id=2 to 1,1,1,1: \n");
        Book book2change = bookDAO.findById(2);
        book2change.setShortTitle("1");
        book2change.setFullTitle("1");
        book2change.setDescription("1");
        book2change.setKeyWords("1");
        bookDAO.update(book2change);
        resp.getWriter().println("Print all list: " + bookList.size());
        for (Book one : bookList) {
            resp.getWriter().println(one + ";\n");
        }

        resp.getWriter().println("\n\tdelete book with id=2: \n");
        bookDAO.deleteById(2);
        resp.getWriter().println("Print all list: " + bookList.size());
        for (Book one : bookList) {
            resp.getWriter().println(one + ";\n");
        }
    }

    private void testUserServices(HttpServletResponse resp) {
//        IUserService userService = new UserService();
        UserService userService = new UserService();
        User result = null;
        try {
            result = userService.login("root", "root");
            resp.getWriter().println("Result of login as root root : "+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
