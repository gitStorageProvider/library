package com.kuriata.controller.commands;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    String execute (HttpServletRequest req);
}
