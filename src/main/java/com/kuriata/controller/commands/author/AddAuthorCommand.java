package com.kuriata.controller.commands.author;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.services.impl.AuthorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddAuthorCommand implements ICommand {
    private String fullName;
    private String details;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {

        String requestMethodName = req.getMethod();

        if (requestMethodName.equalsIgnoreCase("GET")) {
            return "/jsp/addAuthor.jsp";
        } else {
            extractRequestParameters(req);
            setRequestAttributes(req);

            Author author = new Author(0, fullName, details);
            //ToDo: check entered data and if invalid redirect to same page
            try {
                AuthorService authorService = new AuthorService(
                        AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                        AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
                );
                authorService.addAuthor(author);
                req.setAttribute("authorsOperationMessage", "Author added.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/jsp/authors.jsp";
    }


    private void extractRequestParameters(HttpServletRequest req) {
        HttpSession session = req.getSession();
        fullName = req.getParameter("authorName");
        details = req.getParameter("authorDetails");
    }

    private void setRequestAttributes(HttpServletRequest req) {
        req.setAttribute("authorName", fullName);
        req.setAttribute("authorDetails", details);
    }
}
