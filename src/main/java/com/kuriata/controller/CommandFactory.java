package com.kuriata.controller;

import com.kuriata.controller.commands.DefaultCommand;
import com.kuriata.controller.commands.ICommand;
import com.kuriata.controller.commands.SupportedCommands;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CommandFactory {


    public ICommand defineCommand(HttpServletRequest req) {
        ICommand resultCommand = new DefaultCommand();
        String commandString = req.getParameter("command");

        if (commandString == null || commandString.isEmpty()) {
            return resultCommand;
        }

        try {
            for (SupportedCommands temp : SupportedCommands.values()) {
                if (temp.getCommandName().toUpperCase().equals(commandString.toUpperCase())) {
                    resultCommand = temp.getCommand();
                    break;
                }
            }
        } catch (IllegalArgumentException e) {

        }
        return resultCommand;
    }
}
