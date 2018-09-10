<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://library/functions" prefix="f" %>
<fmt:setBundle basename="text"/>
<html>
<%--<body>--%>
<c:choose>
    <c:when test="${empty takenBooksList}">
    </c:when>
    <c:otherwise>
        <table id="outTable">
            <thead>
            <th><fmt:message key="label.tableHead.book"/></th>
            <th><fmt:message key="label.tableHead.time"/></th>
            <th><fmt:message key="label.tableHead.option"/></th>
            </thead>
            <tbody>
            <c:forEach var="oneElement" items="${takenBooksList}">
                <c:url value="?command=returnBook" var="returnLink">
                    <c:param name="recordId" value="${oneElement.id}"/>
                </c:url>
                <tr>
                    <td>${oneElement.book.shortTitle}</td>
                    <td>${f:formatLocalDateTime(oneElement.date, 'dd.MM.yyyy HH:mm')}</td>
                    <td>
                        <a href="${returnLink}"><fmt:message key="option.returnBook"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
