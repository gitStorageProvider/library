package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.dao.daofactory.AbstractDAOFactory;
import com.kuriata.entities.User;
import com.kuriata.exceptions.DAOException;
import com.kuriata.exceptions.ServiceException;
import com.kuriata.services.impl.UserService;
import com.kuriata.services.iservices.IUserService;
import com.kuriata.validators.IValidator;
import com.kuriata.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ICommand {
    private String login;
    private String password;
    private String passwordConfirmation;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        String requestMethodName = req.getMethod();
        if (requestMethodName.equalsIgnoreCase("GET")) {
            return "/jsp/registerUser.jsp";

        } else {
            extractRequestParameters(req);
            setRequestAttributes(req);
            if (checkFields(req)) {
                try {
                    IUserService userService = new UserService(
                            AbstractDAOFactory.getDAOFactory().getUsersDAO(),
                            AbstractDAOFactory.getDAOFactory().getUserAuthorityDAO(),
                            AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                            AbstractDAOFactory.getDAOFactory().getAuthorityDAO()
                    );
                    userService.registerNewUser(
                            new User(0, login, password, email, firstName, lastName, phone));
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                return "/controller?command=showAllBooks";
            } else {
                return "/jsp/registerUser.jsp";
            }
        }
    }

    private void extractRequestParameters(HttpServletRequest req) {
        HttpSession session = req.getSession();
        login = req.getParameter("login");
        password = req.getParameter("password");
        passwordConfirmation = req.getParameter("passwordConfirmation");
        email = req.getParameter("email");
        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        phone = req.getParameter("phone");
    }

    private void setRequestAttributes(HttpServletRequest req) {
        req.setAttribute("login", login);
        req.setAttribute("password", password);
        req.setAttribute("passwordConfirmation", passwordConfirmation);
        req.setAttribute("email", email);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("phone", phone);
    }

    private boolean checkFields(HttpServletRequest req) throws ServletException {
        IValidator validator = new Validator();
        boolean isLoginValid = true;
        boolean isPasswordValid = true;
        boolean isPasswordConfirmationValid = true;
        boolean isEmailValid = true;
        boolean isFirstNameValid = true;
        boolean isLastNameValid = true;
        boolean isPhoneValid = true;

        if (!validator.isUserLoginValid(login) || loginAlreadyExist(req)) {
            req.setAttribute("loginErrorMessage", "invalid input");
            isLoginValid = false;
        }
        if (!validator.isUserPasswordValid(password)) {
            req.setAttribute("passwordErrorMessage", "invalid input");
            isPasswordValid = false;
        }
        if (!validator.isUserPasswordsMached(password, passwordConfirmation)) {
            req.setAttribute("passwordConfirmationErrorMessage", "invalid input");
            isPasswordConfirmationValid = false;
        }
        if (!validator.isUserEmailValid(email)) {
            req.setAttribute("emailErrorMessage", "invalid input");
            isEmailValid = false;
        }
        if (!validator.isUserFirstNameValid(firstName)) {
            req.setAttribute("firstNameErrorMessage", "invalid input");
            isFirstNameValid = false;
        }
        if (!validator.isUserLastNameValid(lastName)) {
            req.setAttribute("lastNameErrorMessage", "invalid input");
            isLastNameValid = false;
        }
        if (!validator.isUserPhoneValid(phone)) {
            req.setAttribute("phoneErrorMessage", "invalid input");
            isPhoneValid = false;
        }
        return isLoginValid && isPasswordValid && isPasswordConfirmationValid &&
                isEmailValid && isFirstNameValid && isLastNameValid && isPhoneValid;
    }

    private boolean loginAlreadyExist(HttpServletRequest req) throws ServletException {
        try{
            IUserService userService = new UserService(
                    AbstractDAOFactory.getDAOFactory().getUsersDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserAuthorityDAO(),
                    AbstractDAOFactory.getDAOFactory().getUserBookDAO(),
                    AbstractDAOFactory.getDAOFactory().getAuthorityDAO()
            );
            return userService.isLoginAlreadyExist(login);
        } catch (ServiceException e) {
            throw new ServletException("Can't check is login already exist or not.", e);
        }
    }
}
