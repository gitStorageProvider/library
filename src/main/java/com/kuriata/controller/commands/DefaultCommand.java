package com.kuriata.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        return "/jsp/login.jsp";
    }
}
