<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Admin Panel" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content center">
            <h1><fmt:message key="admin_panel_jsp.text.admin_panel"/></h1>
            <a href="controller?command=listOfFaculties&sort=number"><strong><fmt:message key="admin_panel_jsp.button.faculties"/></strong></a>
            <a href="controller?command=listOfUsers&page=1&sort=id"><strong><fmt:message key="admin_panel_jsp.button.users"/></strong></a>
        </td>
    </tr>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
