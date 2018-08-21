package com.kuriata.controller.commands;

import com.kuriata.controller.commands.book.ShowAllBooksCommand;
import com.kuriata.controller.commands.user.LoginCommand;

public enum SupportedCommands {
    ADD_ADMIN("showAllBooks"){{
        this.command = new ShowAllBooksCommand();
    }},
    ADD_QUESTION("login"){{
        this.command = new LoginCommand();
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
