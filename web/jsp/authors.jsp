<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="myT" uri="/WEB-INF/tags/implicit.tld" %>
<fmt:setBundle basename="text"/>
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
            <th><fmt:message key="label.tableHead.authorsfullname"/></th>
            <th><fmt:message key="label.tableHead.details"/></th>
                <c:if test="${isAdmin}">
                    <th><fmt:message key="label.tableHead.option"/></th>
                    <th><fmt:message key="label.tableHead.option"/></th>
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
        <input type="submit" value="<fmt:message key="button.addAuthor"/>">
    </form>
</c:if>
</body>
</html>
