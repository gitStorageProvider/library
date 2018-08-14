package com.kuriata.dao.idao;

import com.kuriata.entities.Author;
import com.kuriata.entities.BaseEntity;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IDAO <T extends BaseEntity> {
    List<T> findAll() throws DAOException;

    T findById(int id) throws DAOException;

    //ToDo: uncomment methods
//    int insert(T entity) throws DAOException;
//
//    boolean update(T entity) throws DAOException;

    boolean deleteById(int id) throws DAOException;
}
