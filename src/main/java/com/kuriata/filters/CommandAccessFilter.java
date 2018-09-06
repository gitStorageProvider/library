package com.kuriata.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String currentCommand;
        boolean isAdmin = false;
        boolean isReader = false;
        //making an anonymous inner class with an instance initializer (also known as an "double brace initialization")
        List<String> adminPermittedCommands = new ArrayList<String>() {{
            add("showAllBooks");
            add("showAllTakenBooks");
            add("deleteUser");
            add("addAuthor");
            add("deleteAuthor");
            add("addShelf");
            add("editShelf");
            add("deleteShelf");
            add("showAllUsers");
            add("addBook");
        }};
        List<String> readerPermittedCommands = new ArrayList<String>() {{
            add("showTakenBooks");
        }};
        List<String> guestPermittedCommands = new ArrayList<String>() {{
            add("login");
            add("logout");
            add("register");
            add("showAvailableBooks");
            add("showAllAuthors");
            add("showAllShelves");
        }};

        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("isAdmin") != null) {
            isAdmin = (Boolean) session.getAttribute("isAdmin");
        }
        if (session.getAttribute("isReader") != null) {
            isReader = (Boolean) session.getAttribute("isReader");
        }
        currentCommand = request.getParameter("command");

        if (isAdmin) {
            if (adminPermittedCommands.contains(currentCommand)) {
                chain.doFilter(request, response);
            }
        }
        if (isReader) {
            if (readerPermittedCommands.contains(currentCommand)) {
                chain.doFilter(request, response);
            }
        }
        if (currentCommand == null || guestPermittedCommands.contains(currentCommand)) {
            chain.doFilter(request, response);
        }
        response.getWriter().println("You haven't been authorities to execute command=" + currentCommand + "!");
//            ((HttpServletResponse)response).sendRedirect("/controller?command=login");
    }

    @Override
    public void destroy() {

    }
}
