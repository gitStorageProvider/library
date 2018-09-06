<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="myT" uri="/WEB-INF/tags/implicit.tld" %>
<html>
<body>
<h1>${authorsOperationMessage}</h1>
<h1>${authorsErrorMessage}</h1>
<c:choose>
    <c:when test="${empty authorsList}">
    </c:when>
    <c:otherwise>
        <table id="outTable">
            <thead>
            <th>Author full name</th>
            <th>Details</th>
                <c:if test="${isAdmin}">
                    <th>Option</th>
                    <th>Option</th>
                </c:if>
            </thead>
            <tbody>
                <myT:authors authorsList="${authorsList}" isAdmin="${isAdmin}">
                    <tr>
                        <c:forEach items="${colList}" var="column">
                            <td>${column}</td>
                        </c:forEach>
                    </tr>
                </myT:authors>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<c:if test="${isAdmin}">
    <form action="/controller" method="get">
        <input type="hidden" name="command" value="addAuthor">
        <input type="submit" value="Add new author">
    </form>
</c:if>
</body>
</html>
