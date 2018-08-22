package com.kuriata.dao.idao;

import com.kuriata.entities.Authority;
import com.kuriata.entities.UserAuthority;
import com.kuriata.exceptions.DAOException;

import java.util.List;

public interface IUserAuthorityDAO extends IDAO<UserAuthority>{
    List<UserAuthority> findAllByUserId (int userId) throws DAOException;
}
