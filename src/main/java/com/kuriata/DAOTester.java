package com.kuriata;

import com.kuriata.exceptions.DAOException;

import java.sql.*;

public class DAOTester {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/library";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        try {
//            IConnectionFactory connectionFactory = AbstractConnectionFactory.getConnectionFactory();
//            System.out.println("connection factory: " + connectionFactory);
//            WrappedConnection wrappedConnection = connectionFactory.getConnection();
//            System.out.println("wrappedConnection: " + wrappedConnection);
//            IAuthorDAO authorDAO = AbstractDAOFactory.getDAOFactory().getAuthorsDAO(wrappedConnection);
//            System.out.println(authorDAO.findAll().toString());
            // JDBC driver name and database URL


            Connection conn = null;
            Statement stmt = null;
            try {
                //STEP 2: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 3: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM authors";
                ResultSet rs = stmt.executeQuery(sql);

                //STEP 5: Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    String full_name = rs.getString("full_name");
                    String country = rs.getString("country");

                    //Display values

                    System.out.print("full_name: " + full_name);
                    System.out.println(", country: " + country);
                }
                //STEP 6: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException se2) {
                }// nothing we can do
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }//end try
            System.out.println("Goodbye!");
        }
        finally {
            //end main
        }
//end FirstExample
    }   
}
