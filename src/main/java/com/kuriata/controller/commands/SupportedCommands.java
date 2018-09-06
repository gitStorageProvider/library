package com.kuriata.controller.commands;

import com.kuriata.controller.commands.author.AddAuthorCommand;
import com.kuriata.controller.commands.author.DeleteAuthorCommand;
import com.kuriata.controller.commands.author.ShowAllAuthorsCommand;
import com.kuriata.controller.commands.book.*;
import com.kuriata.controller.commands.shelf.AddShelfCommand;
import com.kuriata.controller.commands.shelf.DeleteShelfCommand;
import com.kuriata.controller.commands.shelf.EditShelfCommand;
import com.kuriata.controller.commands.shelf.ShowAllShelvesCommand;
import com.kuriata.controller.commands.user.*;

public enum SupportedCommands {
    SHOW_ALL_BOOKS("showAllBooks") {{
        this.command = new ShowAllBooksCommand();
    }},
    SHOW_AVAILABLE_BOOKS("showAvailableBooks") {{
        this.command = new ShowAvailableBooksCommand();
    }},
    SHOW_ALL_TAKEN_BOOKS("showAllTakenBooks") {{
        this.command = new ShowAllTakenBooks();
    }},
    SHOW_TAKEN_BOOKS("showTakenBooks") {{
        this.command = new ShowTakenBooks();
    }},
    LOGIN("login") {{
        this.command = new LoginCommand();
    }},
    LOGOUT("logout") {{
        this.command = new LogoutCommand();
    }},
    REGISTER("register") {{
        this.command = new RegisterCommand();
    }},
    DELETE_USER("deleteUser") {{
        this.command = new DeleteUserCommand();
    }},
    SHOW_ALL_AUTHORS("showAllAuthors") {{
        this.command = new ShowAllAuthorsCommand();
    }},
    ADD_AUTHOR("addAuthor") {{
        this.command = new AddAuthorCommand();
    }},
    DELETE_AUTHOR("deleteAuthor") {{
        this.command = new DeleteAuthorCommand();
    }},
    SHOW_ALL_SHELVES("showAllShelves") {{
        this.command = new ShowAllShelvesCommand();
    }},
    ADD_SHELF("addShelf") {{
        this.command = new AddShelfCommand();
    }},
    EDIT_SHELF("editShelf") {{
        this.command = new EditShelfCommand();
    }},
    DELETE_SHELF("deleteShelf") {{
        this.command = new DeleteShelfCommand();
    }},
    SHOW_ALL_USERS("showAllUsers") {{
        this.command = new ShowAllUsersCommand();
    }},
    ADD_BOOK("addBook") {{
        this.command = new AddBookCommand();
    }};

    private SupportedCommands(String commandName) {
        this.commandName = commandName;
    }

    private String commandName;
    ICommand command;

    public String getCommandName() {
        return commandName;
    }

    public ICommand getCommand() {
        return command;
    }
}
