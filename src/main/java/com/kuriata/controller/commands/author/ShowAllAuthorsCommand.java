package com.kuriata.controller.commands.author;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Author;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.AuthorService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowAllAuthorsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req) {

//        Author a1 = new Author(1, "Author1", "Ukraine");
//        Author a2 = new Author(2, "Author2", "USA");
//        Author a3 = new Author(3, "Author3", "Canada");
//        Author a4 = new Author(5, "Author4", "France");
        List<Author> authorList = new ArrayList<>();
//        authorList.add(a1);
//        authorList.add(a2);
//        authorList.add(a3);
//        authorList.add(a4);
        try{
            AuthorService authorService = new AuthorService(
                    AbstractDAOFactory.getDAOFactory().getAuthorsDAO(),
                    AbstractDAOFactory.getDAOFactory().getBookAuthorsDAO()
            );
            authorList = authorService.findAllAuthors();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("authorsList", authorList);
        return "/jsp/authors.jsp";
    }
}
