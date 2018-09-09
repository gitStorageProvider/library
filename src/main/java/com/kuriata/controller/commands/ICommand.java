package com.kuriata.controller.commands;

import com.kuriata.exceptions.ServletException;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    String execute (HttpServletRequest req) throws ServletException;
}
