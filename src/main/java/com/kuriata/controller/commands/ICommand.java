package com.kuriata.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    String execute (HttpServletRequest req) throws ServletException;
}
