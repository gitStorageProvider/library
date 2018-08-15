package com.kuriata.services.iservices;

import com.kuriata.entities.Author;

public interface IAuthorManipulationService {
    boolean addAuthor(Author author);
    boolean deleteAuthorById(int authorId);


}
