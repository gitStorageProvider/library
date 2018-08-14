<%--
  Created by IntelliJ IDEA.
  User: neo
  Date: 07.08.2018
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END2$

  <%@ page import="javax.naming.Context" %>
  <%@ page import="javax.naming.InitialContext" %>
  <%@ page import="javax.naming.NamingException" %>
  <%@ page import="javax.sql.DataSource" %>
  <%@ page import="com.kuriata.dao.idao.IAuthorDAO" %>
  <%@ page import="com.kuriata.dao.daofactory.AbstractDAOFactory" %>
  <%@ page import="com.kuriata.entities.Author" %>
  <%@ page import="java.util.List" %>
  <%
    try {
      Context initCtx = new InitialContext();
      DataSource ctx = (DataSource) initCtx.lookup("java:comp/env/jdbc/libraryDB");
      Object o = ctx.toString();
  %>
    <%=o%><br>
  <%
    }
    catch (NamingException ex) {
      System.out.println(ex);
    }
  %>

  <%
    IAuthorDAO authorDAO = AbstractDAOFactory.getDAOFactory().getAuthorsDAO();
    StringBuilder sb = new StringBuilder();
    List<Author> authorList = authorDAO.findAll();
    sb.append("List size of uthors is: "+authorList.size()+"\n");
    for(Author one: authorList){
      sb.append(one.getId()+ ", "+one.getCountry()+", "+one.getFullName()+";\n");
    }
  %>
  <%=sb%>


  </body>
</html>
