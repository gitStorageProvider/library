package com.kuriata.controller;

import com.kuriata.controller.commands.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

//@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        CommandFactory commandFactory = new CommandFactory();
        ICommand command = commandFactory.defineCommand(req);
        String page = null;
        try {
            page = command.execute(req);
        } catch (com.kuriata.exceptions.ServletException e) {
            e.printStackTrace();
        }
        if (page != null) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = "/html/errorPage.html";
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
