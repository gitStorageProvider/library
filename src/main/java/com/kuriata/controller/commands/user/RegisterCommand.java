package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;

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
            if(true){
                extractRequestParameters(req);
                setRequestAttributes(req);

                req.setAttribute("loginErrorMessage", "TEST PASSED OK.");
                return "/jsp/registerUser.jsp";
            }

            //ToDo: check entered data and if invalid redirect to same page

            return "/jsp/login.jsp";
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
}
