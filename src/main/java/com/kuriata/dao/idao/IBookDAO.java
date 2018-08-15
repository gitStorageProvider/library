package com.kuriata.dao.idao;

import com.kuriata.dao.connection.AbstractConnectionFactory;
import com.kuriata.dao.connection.WrappedConnection;
import com.kuriata.entities.Book;
import com.kuriata.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IBookDAO extends IDAO<Book>{
}
