package com.kuriata.controller.commands.shelf;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.ShelfService;
import com.kuriata.services.iservices.IShelfService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowAllShelvesCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        List<Shelf> shelvesList = new ArrayList<>();
//        Shelf s1 = new Shelf(1, "history", "a1", "all history items");
//        Shelf s2 = new Shelf(2, "technology", "b1", "all technology items");
//        Shelf s3 = new Shelf(3, "sociology", "c1", "all sociology items");
//        Shelf s4 = new Shelf(5, "art", "d1", "all art items");

//        shelvesList.add(s1);
//        shelvesList.add(s2);
//        shelvesList.add(s3);
//        shelvesList.add(s4);

        try{
            IShelfService shelfService = new ShelfService(
                    AbstractDAOFactory.getDAOFactory().getShelfsDAO(),
                    AbstractDAOFactory.getDAOFactory().getShelfBookDAO()
            );
            shelvesList = shelfService.getAllShelves();
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        req.setAttribute("shelvesList", shelvesList);

        return "/jsp/shelves.jsp";

    }
}
