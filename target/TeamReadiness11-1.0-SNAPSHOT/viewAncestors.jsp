<%-- 
    Document   : viewAncestors
    Created on : Nov 21, 2016, 11:22:42 AM
    Author     : dblankenship
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ancestors</h1>
        <table>
            <c:forEach items="${ancestors}" var="ancestor">
                <tr>
                    <td>
                        <a href="PersonDetail?id=${ancestor.get(0)}">
                            <c:out value="${ancestor.get(1)} ${ancestor.get(2)}"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
