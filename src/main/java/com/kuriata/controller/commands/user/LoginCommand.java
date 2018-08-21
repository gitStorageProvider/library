package com.kuriata.controller.commands.user;

import com.kuriata.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ICommand {
    //ToDo: realize method below
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().setAttribute("attributeName1", "AtributeObject1");
        System.out.println("Attribute 1 set.");
        req.getSession().setAttribute("attributeName2", "AtributeObject2");
        System.out.println("Attribute 2 set.");
        req.getSession().setAttribute("attributeName3", "AtributeObject3");
        System.out.println("Attribute 3 set.");

        return "/jsp/login.jsp";
    }
}
