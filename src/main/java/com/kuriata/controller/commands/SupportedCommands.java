package com.kuriata.controller.commands;

import com.kuriata.controller.commands.book.ShowAllBooksCommand;
import com.kuriata.controller.commands.user.LoginCommand;
import com.kuriata.controller.commands.user.LogoutCommand;

public enum SupportedCommands {
    SHOW_ALL_BOOKS("showAllBooks"){{
        this.command = new ShowAllBooksCommand();
    }},
    LOGIN("login"){{
        this.command = new LoginCommand();
    }},
    LOGOUT("logout"){{
        this.command = new LogoutCommand();
    }};

    private SupportedCommands(String commandName){
        this.commandName = commandName;
    }

    private String commandName;
    ICommand command;

    public String getCommandName(){
        return commandName;
    }

    public ICommand getCommand() {
        return command;
    }
}
