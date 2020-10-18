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

            <c:if test="${not empty successMailingMessage}">
                <h3><span style="color: #1B860A"><fmt:message key="report_sheet_jsp.message.emails_sent"/></span>
                </h3><br>
                <c:remove var="successMailingMessage" scope="session"/>
            </c:if>

<%--            todo l10n --%>
            <c:if test="${not empty failedTxt}">
                <h3><span style="color: rgb(204, 0, 0);">failed to create txt report file</span>
                </h3><br>
                <c:remove var="failedTxt" scope="session"/>
            </c:if>

            <c:if test="${not empty failedPdf}">
                <h3><span style="color: rgb(204, 0, 0);">failed to create pdf report file</span>
                </h3><br>
                <c:remove var="failedPdf" scope="session"/>
            </c:if>

            <h3><fmt:message key="report_sheet_jsp.text.report_sheet"/>
                <localizer:name currentLocaleName="${currentLocale}" localizable="${faculty}"/></h3>

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
                        <th><localizer:name currentLocaleName="${currentLocale}"
                                            localizable="${criterion}"/></th>
                    </c:forEach>
                    <th><fmt:message key="faculty_entrants_jsp.text.entr_marks_sum"/></th>
                </tr>
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

            <c:if test="${userRole.name == 'admin'}">
                <a href="controller?command=downloadFile&facultyId=${faculty.id}&file=txt"><strong><fmt:message
                        key="report_sheet_jsp.button.download_txt"/></strong></a>
                <a href="controller?command=downloadFile&facultyId=${faculty.id}&file=pdf"><strong><fmt:message
                        key="report_sheet_jsp.button.download_pdf"/></strong></a>
            </c:if>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
