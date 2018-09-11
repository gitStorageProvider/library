package com.kuriata.dao.idao;

import com.kuriata.entities.Author;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IAuthorDAO extends IDAO<Author>{
    List<Author> findByName (String ... keyWords) throws DAOException;
}
