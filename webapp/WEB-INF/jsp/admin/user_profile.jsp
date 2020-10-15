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

            <c:if test="${not empty successAppDeleteMessage}">
                <h3><span style="color: #1B860A"><fmt:message key="private_office_jsp.message.application_deleted"/><br> </span>
                </h3>
                <c:remove var="successAppDeleteMessage" scope="session"/>
            </c:if>

            <h3><fmt:message key="private_office_jsp.text.personal_info"/></h3>
            <p>Email | <strong>${profileOwner.email}</strong></p>
            <p><fmt:message key="private_office_jsp.text.name"/> | <strong>${profileOwner.name}</strong></p>
            <p><fmt:message key="private_office_jsp.text.surname"/> | <strong>${profileOwner.surname}</strong></p>
            <p><fmt:message key="private_office_jsp.text.patronymic"/> | <strong>${profileOwner.patronymic}</strong></p>
            <p><fmt:message key="private_office_jsp.text.region"/> | <strong>${profileOwner.region}</strong></p>
            <p><fmt:message key="private_office_jsp.text.city"/> | <strong>${profileOwner.city}</strong></p>
            <p><fmt:message key="private_office_jsp.text.institution"/> |
                <strong>${profileOwner.institutionName}</strong></p>
            <p>
                <fmt:message key="user_profile_jsp.text.status"/> |
                <c:choose>
                <c:when test="${profileOwner.statusId == 0}">
                <strong><fmt:message key="users_jsp.text.unblocked"/></strong>
                </c:when>
                <c:when test="${profileOwner.statusId == 1}"><span style="color: rgb(204, 0, 0); "><strong><fmt:message
                    key="users_jsp.text.blocked"/></strong></span></c:when>
                </c:choose>
            <form action="controller" method="get">
                <input name="command" type="hidden" value="updateUsersStatus"/>
                <input name="userId" type="hidden" value="${profileOwner.id}">
                <input name="forwardTo" type="hidden" value="profile">
                <c:choose>
                    <c:when test="${profileOwner.statusId == 0 and profileOwner.roleId != 1}">
                        <button name="status" value="1">
                            <fmt:message key="users_jsp.button.block"/>
                        </button>
                    </c:when>
                    <c:when test="${profileOwner.statusId == 1 and profileOwner.roleId != 1}">
                        <button name="status" value="0">
                            <fmt:message key="users_jsp.button.unblock"/>
                        </button>
                    </c:when>
                </c:choose>
            </form>
            </p>

            <c:if test="${not empty userMarks}">
                <h3><fmt:message key="user_profile_jsp.text.user_marks"/></h3>
                <c:forEach var="mark" items="${userMarks}">
                    <c:choose>
                        <c:when test="${currentLocale == 'uk'}">
                            <p>${mark.criterionNameUk} : <strong>${mark.mark}</strong></p>
                        </c:when>
                        <c:when test="${currentLocale == 'en'}">
                            <p>${mark.criterionNameEn} : <strong>${mark.mark}</strong></p>
                        </c:when>
                        <c:otherwise>
                            <p>${mark.criterionNameEn} : <strong>${mark.mark}</strong></p>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

            <c:if test="${not empty userFaculties}">
                <h3><fmt:message key="user_profile_jsp.text.user_applications"/></h3>
                <c:forEach var="faculty" items="${userFaculties}">
                    <c:choose>
                        <c:when test="${faculty.facultyStatusId == 0}">
                            <c:choose>
                                <c:when test="${currentLocale == 'uk'}">
                                    <p>${faculty.facultyNameUk} |
                                        <a href="controller?command=viewFacultyApplications&facultyId=${faculty.facultyId}"><strong><fmt:message
                                                key="private_office_jsp.button.view_entrants"/> </strong></a> |
                                        <a href="controller?command=deleteUserApplication&facultyId=${faculty.facultyId}&userId=${profileOwner.id}"><strong><fmt:message
                                                key="user_profile_jsp.button.delete_application"/></strong></a>
                                    </p>
                                </c:when>
                                <c:when test="${currentLocale == 'en'}">
                                    <p>${faculty.facultyNameEn} |
                                        <a href="controller?command=viewFacultyApplications&facultyId=${faculty.facultyId}"><strong><fmt:message
                                                key="private_office_jsp.button.view_entrants"/></strong></a> |
                                        <a href=href="controller?command=deleteUserApplication&facultyId=${faculty.facultyId}&userId=${profileOwner.id}"><strong><fmt:message
                                                key="user_profile_jsp.button.delete_application"/></strong></a>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p>${faculty.facultyNameEn} |
                                        <a href="controller?command=viewFacultyApplications&facultyId=${faculty.facultyId}"><strong><fmt:message
                                                key="private_office_jsp.button.view_entrants"/></strong></a> |
                                        <a href="controller?command=deleteUserApplication&facultyId=${faculty.facultyId}&userId=${profileOwner.id}"><strong><fmt:message
                                                key="user_profile_jsp.button.delete_application"/></strong></a>
                                    </p>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${faculty.facultyStatusId == 1}">
                            <c:choose>
                                <c:when test="${currentLocale == 'uk'}">
                                    <p>${faculty.facultyNameUk} | <span style="color:  rgb(204, 0, 0);"><strong><fmt:message
                                            key="faculties_jsp.text.recruitment_closed"/></strong></span> |
                                        <a href="controller?command=viewReportSheetPage&facultyId=${faculty.facultyId}"><strong><fmt:message
                                                key="faculties_jsp.button.view_report"/></strong></a>
                                    </p>
                                </c:when>
                                <c:when test="${currentLocale == 'en'}">
                                    <p>${faculty.facultyNameEn} | <span style="color:  rgb(204, 0, 0);"><strong><fmt:message
                                            key="faculties_jsp.text.recruitment_closed"/></strong></span> |
                                        <a href="controller?command=viewReportSheetPage&facultyId=${faculty.facultyId}"><strong><fmt:message
                                                key="faculties_jsp.button.view_report"/></strong></a>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p>${faculty.facultyNameEn} | <span style="color:  rgb(204, 0, 0);"><strong><fmt:message
                                            key="faculties_jsp.text.recruitment_closed"/></strong></span> |
                                        <a href="controller?command=viewReportSheetPage&facultyId=${faculty.facultyId}"><strong><fmt:message
                                                key="faculties_jsp.button.view_report"/></strong></a>
                                    </p>
                                </c:otherwise>
                            </c:choose>
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
