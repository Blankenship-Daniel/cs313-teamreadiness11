<%-- 
    Document   : personDetail
    Created on : Nov 21, 2016, 2:42:41 PM
    Author     : dblankenship
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Detail</title>
    </head>
    <body>
        <h1>Person Detail</h1>
        <p><c:out value="${personDetail}"/></p>
        <h3>Parent(s)</h3>
        <table>
            <c:forEach items="${parents}" var="parent">
                <tr>
                    <td>
                        <c:out value="${parent}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h3>Child(ren)</h3>
        <table>
            <c:forEach items="${children}" var="child">
                <tr>
                    <td>
                        <c:out value="${child}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
