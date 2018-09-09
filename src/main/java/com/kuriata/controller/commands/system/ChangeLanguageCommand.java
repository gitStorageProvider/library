package com.kuriata.controller.commands.system;

import com.kuriata.controller.commands.ICommand;
import com.kuriata.exceptions.ServletException;
import com.kuriata.helpers.MessagesProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ChangeLanguageCommand implements ICommand {
    private String selectedLocale;

    @Override
    public String execute(HttpServletRequest req) throws ServletException {
        selectedLocale = req.getParameter("locale");
        req.setAttribute("newLocale", selectedLocale);
        String [] localeArray = selectedLocale.split("_");
        Locale.setDefault(new Locale(localeArray[0], localeArray[1]));
        MessagesProvider.updateLocale();
        return "/controller?command=showAllBooks";
    }
}
