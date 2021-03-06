package com.kuriata.services.impl;

import com.kuriata.dao.idao.IAuthorDAO;
import com.kuriata.dao.idao.IBookAuthorDAO;
import com.kuriata.entities.Author;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.iservices.IAuthorService;

import java.util.ArrayList;
import java.util.List;

public class AuthorService implements IAuthorService {
    private IAuthorDAO authorDAO;
    private IBookAuthorDAO bookAuthorDAO;

    public AuthorService(IAuthorDAO authorDAO, IBookAuthorDAO bookAuthorDAO) {
        this.authorDAO = authorDAO;
        this.bookAuthorDAO = bookAuthorDAO;
    }

    @Override
    public List<Author> findAllAuthors() throws ServiceException {
        List<Author> result = new ArrayList<>();
        try {
            result = authorDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("All authors not retrieved successfully.", e);
        }
        return result;
    }

    @Override
    public List<Author> findAllAuthorsByBookId(int bookId) throws ServiceException {
        List<Integer> authorsIdList = new ArrayList<>();
        List<Author> result = new ArrayList<>();
        try {
            authorsIdList = bookAuthorDAO.getAuthorsIdByBookId(bookId);
            for(Integer oneId : authorsIdList){
                result.add(authorDAO.findById(oneId));
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("All authors not retrieved successfully.", e);
        }
        return result;
    }

    @Override
    public List<Author> findAuthorByName(String... keyWords) throws ServiceException {
        try{
            return authorDAO.findByName(keyWords);
        } catch (DAOException e) {
            throw new ServiceException("Can't find author(s) by name.", e);
        }
    }

    @Override
    public List<Integer> findBooksIdByAuthorId(int authorId) throws ServiceException {
        try{
            return bookAuthorDAO.getBooksIdByAuthorId(authorId);
        } catch (DAOException e) {
            throw new ServiceException("Can't extract books id by author id.", e);
        }
    }

    @Override
    public boolean editAuthor(Author author) throws ServiceException {
        try {
            authorDAO.update(author);
            return true;
        } catch (DAOException e) {
            throw new ServiceException("Can't update author.", e);
        }
    }

    @Override
    public boolean addAuthor(Author author) throws ServiceException {
        try {
            authorDAO.insert(author);
            return true;
        } catch (DAOException e) {
            throw new ServiceException("Can't add author.", e);
        }
    }

    @Override
    public boolean deleteAuthorById(int authorId) throws ServiceException {
        try {
            //ToDo: use next method to check
            if (bookAuthorDAO.getWrittenBooksCountByAuthor(authorId) > 0) {
                throw new ServiceException("Can't delete author bounded to book(s)", new IllegalArgumentException());
            } else {
                authorDAO.deleteById(authorId);
                return true;
            }
        } catch (DAOException e) {
            throw new ServiceException("Can't delete author", e);
        }
    }

    @Override
    public boolean isAuthorUsed(int authorId) throws ServiceException {
        try {
            return bookAuthorDAO.getWrittenBooksCountByAuthor(authorId) > 0;
        } catch (DAOException e) {
            throw new ServiceException("Can't check is author used or not", e);
        }
    }
}
