<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@page import="org.apache.commons.httpclient.*" %>

<html>

<c:set var="title" value="Error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>

            <h3 class="error">
                The following error occurred<br>
            </h3>

            <c:if test="${empty errorMessage}">
                <h3><%=HttpStatus.getStatusText(response.getStatus())%> <%=response.getStatus()%>
                </h3>
            </c:if>

            <h3><c:out value="${errorMessage}"/></h3>

            <% request.getSession().removeAttribute("errorMessage"); %>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>