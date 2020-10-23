<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Private office" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf" %>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <c:if test="${not empty successMessage}">
                <h3><span style="color: #1B860A"><fmt:message
                        key="private_office_jsp.message.data_changed"/><br> </span></h3>
                <c:remove var="successMessage" scope="session"/>
            </c:if>

            <c:if test="${not empty successAppDeleteMessage}">
                <h3><span style="color: #1B860A"><fmt:message key="private_office_jsp.message.application_deleted"/><br> </span>
                </h3>
                <c:remove var="successAppDeleteMessage" scope="session"/>
            </c:if>

            <a href="controller?command=updateUserPage"><strong><fmt:message
                    key="private_office_jsp.button.change_data"/></strong></a>

            <h3><fmt:message key="private_office_jsp.text.personal_info"/></h3>
            <p>Email | <strong>${user.email}</strong></p>
            <p><fmt:message key="private_office_jsp.text.name"/> | <strong>${user.name}</strong></p>
            <p><fmt:message key="private_office_jsp.text.surname"/> | <strong>${user.surname}</strong></p>
            <p><fmt:message key="private_office_jsp.text.patronymic"/> | <strong>${user.patronymic}</strong></p>
            <p><fmt:message key="private_office_jsp.text.region"/> | <strong>${user.region}</strong></p>
            <p><fmt:message key="private_office_jsp.text.city"/> | <strong>${user.city}</strong></p>
            <p><fmt:message key="private_office_jsp.text.institution"/> | <strong>${user.institutionName}</strong></p>

            <c:if test="${not empty userMarks}">
                <h3><fmt:message key="private_office_jsp.text.marks"/></h3>
                <c:forEach var="mark" items="${userMarks}">
                    <p><c:out value="${currentLocale eq 'uk' ? mark.criterionNameUk : mark.criterionNameEn}"/> :
                        <strong>${mark.mark}</strong></p>
                </c:forEach>
            </c:if>

            <c:if test="${not empty userFaculties}">
                <h3><fmt:message key="private_office_jsp.text.applications"/></h3>
                <c:forEach var="faculty" items="${userFaculties}">
                    <c:choose>
                        <c:when test="${faculty.facultyStatusId == 0}">
                            <p>
                                <c:out value="${currentLocale eq 'uk' ? faculty.facultyNameUk : faculty.facultyNameEn}"/>
                                |
                                <a href="controller?command=viewFacultyApplications&facultyId=${faculty.facultyId}"><strong><fmt:message
                                        key="private_office_jsp.button.view_entrants"/> </strong></a> |
                                <a href="controller?command=deleteUserApplication&facultyId=${faculty.facultyId}&userId=${user.id}"><strong><fmt:message
                                        key="user_profile_jsp.button.delete_application"/></strong></a>
                            </p>
                        </c:when>
                        <c:when test="${faculty.facultyStatusId == 1}">
                            <p>
                                <c:out value="${currentLocale eq 'uk' ? faculty.facultyNameUk : faculty.facultyNameEn}"/>
                                |
                                <span
                                        style="color:  rgb(204, 0, 0);"><strong><fmt:message
                                        key="faculties_jsp.text.recruitment_closed"/></strong></span> |
                                <a href="controller?command=viewReportSheetPage&facultyId=${faculty.facultyId}"><strong><fmt:message
                                        key="faculties_jsp.button.view_report"/></strong></a>
                            </p>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </c:if>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
