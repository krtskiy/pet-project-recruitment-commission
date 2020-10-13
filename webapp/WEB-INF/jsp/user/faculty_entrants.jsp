<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Faculty entrants" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <c:if test="${not empty facultyApplications}">

                <h3>List of applications for ${faculty.nameEn}</h3>
                <small>sorted by sum of all marks</small><br>

                <table id="list_users_table">
                    <tr>
                        <th>#</th>
                        <th>Entrant name</th>
                        <th>Entrant surname</th>
                        <c:forEach var="criterion" items="${faculty.criteria}">
                            <th>${criterion.nameEn}</th>
                        </c:forEach>
                        <th>Sum of marks</th>
                    </tr>
                    <c:set var="k" value="0"/>
                    <c:forEach var="application" items="${facultyApplications}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td>${k}</td>
                            <td>${application.userName}</td>
                            <td>${application.userSurname}</td>
                            <c:set var="markSum" value="${0}"/>
                            <c:forEach var="mark" items="${application.userMarks}">
                                <c:set var="markSum" value="${markSum + mark.mark}"/>
                                <td>${mark.mark}</td>
                            </c:forEach>
                            <td><strong>${markSum}</strong></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
