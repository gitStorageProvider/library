package com.kuriata.services.impl;

import com.kuriata.dao.idao.IShelfBookDAO;
import com.kuriata.dao.idao.IShelfDAO;
import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IShelfService;

import java.util.ArrayList;
import java.util.List;

public class ShelfService implements IShelfService {
    private IShelfDAO shelfDAO;
    private IShelfBookDAO shelfBookDAO;

    public ShelfService(IShelfDAO shelfDAO, IShelfBookDAO shelfBookDAO) {
        this.shelfDAO = shelfDAO;
        this.shelfBookDAO = shelfBookDAO;
    }

    @Override
    public List<Shelf> getAllShelves() throws ServiceException {
        List<Shelf> result = new ArrayList<>();
        try {
            result = shelfDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addShelf(Shelf shelf) throws ServiceException {
        try {
            shelfDAO.insert(shelf);
            return true;
        } catch (DAOException e) {
            throw new ServiceException("Can't add shelf.", e);
        }
    }

    @Override
    public boolean deleteShelfById(int shelfId) throws ServiceException {
        try {
            if (isShelfUsed(shelfId)) {
                throw new ServiceException("Can't delete shelf bounded to book(s)", new IllegalArgumentException());
            } else {
                shelfDAO.deleteById(shelfId);
                return true;
            }
        } catch (DAOException e) {
            throw new ServiceException("Can't delete shelf", e);
        }
    }

    //ToDo: realize method
    @Override
    public boolean editShelf(Shelf shelf) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShelfUsed(int shelfId) throws ServiceException {
        try {
            return shelfBookDAO.booksCountOnShelfWithId(shelfId) > 0;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
