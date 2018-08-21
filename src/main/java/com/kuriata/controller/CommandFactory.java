package com.kuriata.controller;

import com.kuriata.controller.commands.DefaultCommand;
import com.kuriata.controller.commands.ICommand;
import com.kuriata.controller.commands.SupportedCommands;


import javax.servlet.http.HttpServletRequest;

public class CommandFactory {


    public ICommand defineCommand(HttpServletRequest req) {
        ICommand resultCommand = new DefaultCommand();

        String commandString = req.getParameter("command");
        System.out.println("Command: " + commandString);

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
            System.out.println("CommandFactory result: " + resultCommand);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception while command definition." + e);
            req.setAttribute("wrongCommand", commandString);
        }
        return resultCommand;
    }
}
