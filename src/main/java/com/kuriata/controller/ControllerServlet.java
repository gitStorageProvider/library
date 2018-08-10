package com.kuriata.controller;

import com.kuriata.dao.connection.WrappedConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
//        resp.getWriter().printf("Controller servlet process().");
        Context ctx = null;
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/libraryDB");
            connection = ds.getConnection();


            System.out.println("connection created");
            WrappedConnection wrappedConnection = new WrappedConnection(connection);
            System.out.println("wrapped connection created");
            stmt = wrappedConnection.createStatement();
            System.out.println("statement created");
            rs = stmt.executeQuery("SELECT * FROM authors");
            System.out.println("querry executed");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                //Retrieve by column name
                String full_name = rs.getString("full_name");
                String country = rs.getString("country");

                //Display values

                sb.append("full_name: " + full_name);
                sb.append(", country: " + country);
                System.out.println(sb);
            }
            resp.getWriter().println("SQL query worked properly. Time in milis is: " + System.currentTimeMillis());

//            IAuthorDAO authorDAO = AbstractDAOFactory.getDAOFactory().getAuthorsDAO(wrappedConnection);
//            List<Author> authorList = authorDAO.findAll();
//            resp.getWriter().printf("List size of uthors is: "+authorList.size());
//            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM books;");
//            resp.getWriter().printf("List size of books is: "+rs.getFetchSize());

        } catch (NamingException e) {
            // Handle error that it's not configured in JNDI.
            throw new IllegalStateException("jdbc/db is missing in JNDI!", e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
