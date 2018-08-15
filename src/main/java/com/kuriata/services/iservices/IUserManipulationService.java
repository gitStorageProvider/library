package com.kuriata.services.iservices;

import com.kuriata.entities.User;

public interface IUserManipulationService {
    boolean registerNewUser(User user);
    boolean deleteUserById(int userId);
    boolean setUserAsAdmin(int userId);
}
