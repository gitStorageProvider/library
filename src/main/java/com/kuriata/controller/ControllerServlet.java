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

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        resp.getWriter().printf("Controller servlet process().");
//        Context ctx = null;
//        Connection connection = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/libraryDB");
//            connection = ds.getConnection();
//
//
//            System.out.println("connection created");
//            WrappedConnection wrappedConnection = new WrappedConnection(connection);
//            System.out.println("wrapped connection created");
//            stmt = wrappedConnection.createStatement();
//            System.out.println("statement created");
//            rs = stmt.executeQuery("SELECT * FROM authors");
//            System.out.println("querry executed");
//            StringBuilder sb = new StringBuilder();
//            while (rs.next()) {
//                //Retrieve by column name
//                String full_name = rs.getString("full_name");
//                String country = rs.getString("country");
//
//                //Display values
//
//                sb.append("full_name: " + full_name);
//                sb.append(", country: " + country);
//                System.out.println(sb);
//            }
//            resp.getWriter().println("SQL query worked properly. Time in milis is: " + System.currentTimeMillis());
//
////            IAuthorDAO authorDAO = AbstractDAOFactory.getDAOFactory().getAuthorsDAO(wrappedConnection);
////            List<Author> authorList = authorDAO.findAll();
////            resp.getWriter().printf("List size of uthors is: "+authorList.size());
////            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM books;");
////            resp.getWriter().printf("List size of books is: "+rs.getFetchSize());
//
//        } catch (NamingException e) {
//            // Handle error that it's not configured in JNDI.
//            throw new IllegalStateException("jdbc/db is missing in JNDI!", e);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            AuthorDAO authorDAO = (AuthorDAO) AbstractDAOFactory.getDAOFactory().getAuthorsDAO();
//            List<Author> authorList = authorDAO.findAll();
//            resp.getWriter().println("List size of uthors is: "+authorList.size());
//            StringBuilder sb = new StringBuilder();
//            for(Author one: authorList){
//                sb.append(one.getId()+ ", "+one.getDetails()+", "+one.getFullName()+";\n");
//            }
//            resp.getWriter().println(sb);
//            resp.getWriter().println("\n find author by id = 1: \n");
//            Author foundByIDAuthor = authorDAO.findById(1);
//            resp.getWriter().println(foundByIDAuthor.getId()+ ", "+foundByIDAuthor.getDetails()+", "+foundByIDAuthor.getFullName()+";\n");
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        CommandFactory commandFactory = new CommandFactory();
        ICommand command = commandFactory.defineCommand(req);
        String page = command.execute(req);

        Enumeration e = (Enumeration) (req.getSession().getAttributeNames());
        while (e.hasMoreElements()) {
            Object tring;
            if ((tring = e.nextElement()) != null) {
                System.out.println("-->" + req.getSession().getValue((String) tring));
                System.out.println("<br/>");
            }
        }

        if (page != null) {
            System.out.println("Computed page = " + page);
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = "/html/errorPage.html";
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
