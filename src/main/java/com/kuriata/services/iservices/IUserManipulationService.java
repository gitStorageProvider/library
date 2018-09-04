package com.kuriata.services.iservices;

import com.kuriata.entities.User;

public interface IUserManipulationService {
    boolean setUserAsAdmin(int userId);
}
