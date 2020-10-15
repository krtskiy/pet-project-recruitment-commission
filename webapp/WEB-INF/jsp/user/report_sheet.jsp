<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Report sheet" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf" %>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <h3><fmt:message key="report_sheet_jsp.text.report_sheet"/>
                <localizer:facultyName currentLocaleName="${currentLocale}" faculty="${faculty}"/></h3>

            <svg width="15" height="15">
                <rect width="15" height="15" style="fill:#CCFF99;stroke-width:3;stroke:rgb(0,0,0)"/>
            </svg>
            <fmt:message key="report_sheet_jsp.text.applied_budget"/> (${faculty.budgetSeats})<br>
            <svg width="15" height="15">
                <rect width="15" height="15" style="fill:#9AD4FF;stroke-width:3;stroke:rgb(0,0,0)"/>
            </svg>
            <fmt:message key="report_sheet_jsp.text.applied_contract"/>
            (${faculty.totalSeats - faculty.budgetSeats})<br>

            <table id="list_users_table">
                <tr>
                    <th>#</th>
                    <th><fmt:message key="faculty_entrants_jsp.text.entr_name"/></th>
                    <th><fmt:message key="faculty_entrants_jsp.text.entr_surname"/></th>
                    <c:forEach var="criterion" items="${faculty.criteria}">
                        <th><localizer:criterionName currentLocaleName="${currentLocale}"
                                                     criterion="${criterion}"/></th>
                    </c:forEach>
                    <th><fmt:message key="faculty_entrants_jsp.text.entr_marks_sum"/></th>
                </tr>
                <%--                <c:choose>--%>
                <%--                    <c:when test="${not empty facultyApplications}">--%>
                <c:set var="k" value="0"/>
                <c:forEach var="application" items="${facultyApplications}">
                    <c:if test="${k < faculty.totalSeats}">
                        <c:set var="k" value="${k+1}"/>
                        <tr
                                <c:choose>
                                    <c:when test="${k <= faculty.budgetSeats}">bgcolor="#D3FFCA"</c:when>
                                    <c:when test="${k > faculty.budgetSeats and k <= faculty.totalSeats}">bgcolor="#CAF3FF"</c:when>
                                </c:choose>

                        >
                            <c:choose>
                                <c:when test="${user.id == application.userId}">
                                    <td><strong>${k}</strong></td>
                                    <td><strong>${application.userName}</strong></td>
                                    <td><strong>${application.userSurname}</strong></td>
                                    <c:set var="markSum" value="${0}"/>
                                    <c:forEach var="mark" items="${application.userMarks}">
                                        <c:set var="markSum" value="${markSum + mark.mark}"/>
                                        <td><strong>${mark.mark}</strong></td>
                                    </c:forEach>
                                    <td><strong>${markSum}</strong></td>
                                </c:when>
                                <c:otherwise>
                                    <td>${k}</td>
                                    <td>${application.userName}</td>
                                    <td>${application.userSurname}</td>
                                    <c:set var="markSum" value="${0}"/>
                                    <c:forEach var="mark" items="${application.userMarks}">
                                        <c:set var="markSum" value="${markSum + mark.mark}"/>
                                        <td>${mark.mark}</td>
                                    </c:forEach>
                                    <td><strong>${markSum}</strong></td>
                                    <c:if test="${user.roleId == 1}">
                                        <td>
                                            <a href="controller?command=viewUserProfilePage&userId=${application.userId}"><strong><fmt:message
                                                    key="users_jsp.button.view_profile"/></strong></a></td>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
