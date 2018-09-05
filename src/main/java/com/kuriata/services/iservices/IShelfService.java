package com.kuriata.services.iservices;

import com.kuriata.entities.Shelf;
import com.kuriata.exceptions.ServiceException;

import java.util.List;

public interface IShelfService {
    List<Shelf> getAllShelves() throws ServiceException;

    Shelf getShelfById(int shelfId) throws ServiceException;

    boolean addShelf(Shelf shelf) throws ServiceException;

    boolean deleteShelfById(int shelfId) throws ServiceException;

    boolean editShelf( Shelf shelf) throws ServiceException;

    boolean isShelfUsed(int shelfId) throws ServiceException;
}
