<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Private office" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf"%>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <a href="controller?command=updateUserPage">Change login data</a>
            <%-- todo add applications view --%>
            <a href="controller?command=listOfEntrantsApplications">View my applications</a>

            <h3>Personal information:</h3>
            <p><c:out value="Email | ${user.email}"/></p>
            <p><c:out value="Name | ${user.name}"/></p>
            <p><c:out value="Surname | ${user.surname}"/></p>
            <p><c:out value="Patronymic | ${user.patronymic}"/></p>
            <p><c:out value="Region | ${user.region}"/></p>
            <p><c:out value="City | ${user.city}"/></p>
            <p><c:out value="Educational institution | ${user.institutionName}"/></p>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
