package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.exceptions.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        HttpSession session = req.getSession();
        Enumeration<String> sessionAttributes = session.getAttributeNames();
        while (sessionAttributes.hasMoreElements()){
            session.removeAttribute(sessionAttributes.nextElement());
        }
        session.invalidate();

        return "/jsp/login.jsp";
    }
}
