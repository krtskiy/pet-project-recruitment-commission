<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List of faculties" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <c:if test="${not empty facultyRegisteredFor}">
                <h3><span style="color: #1B860A"><fmt:message key="faculties_jsp.message.registered_for"/>
                <c:out value="${currentLocale eq 'uk' ? facultyRegisteredFor.nameUk : facultyRegisteredFor.nameEn}"/><br>
                </span></h3>
                <c:remove var="facultyRegisteredFor" scope="session"/>
            </c:if>

            <strong><fmt:message key="faculties_jsp.text.sort_by"/></strong>
            <a href="controller?command=listOfFaculties&sort=number">
                <c:choose>
                    <c:when test="${param.sort == 'number'}">
                        <strong><fmt:message key="faculties_jsp.button.sort_number"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'number'}">
                        <fmt:message key="faculties_jsp.button.sort_number"/>
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&sort=nameAsc">
                <c:choose>
                    <c:when test="${param.sort == 'nameAsc'}">
                        <strong><fmt:message key="faculties_jsp.button.sort_name_az"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'nameAsc'}">
                        <fmt:message key="faculties_jsp.button.sort_name_az"/>
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&sort=nameDesc">
                <c:choose>
                    <c:when test="${param.sort == 'nameDesc'}">
                        <strong><fmt:message key="faculties_jsp.button.sort_name_za"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'nameDesc'}">
                        <fmt:message key="faculties_jsp.button.sort_name_za"/>
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&sort=budgetSeats">
                <c:choose>
                    <c:when test="${param.sort == 'budgetSeats'}">
                        <strong><fmt:message key="faculties_jsp.button.sort_budget_seats"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'budgetSeats'}">
                        <fmt:message key="faculties_jsp.button.sort_budget_seats"/>
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&sort=totalSeats">
                <c:choose>
                    <c:when test="${param.sort == 'totalSeats'}">
                        <strong><fmt:message key="faculties_jsp.button.sort_total_seats"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'totalSeats'}">
                        <fmt:message key="faculties_jsp.button.sort_total_seats"/>
                    </c:when>
                </c:choose>
            </a>

            <table id="list_faculties_table">
                <thead>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="faculties_jsp.text.faculty_name"/></th>
                    <th><fmt:message key="faculties_jsp.text.faculty_total_seats"/></th>
                    <th><fmt:message key="faculties_jsp.text.faculty_budget_seats"/></th>
                    <th><fmt:message key="faculties_jsp.text.faculty_selection_criteria"/></th>
                </tr>
                </thead>
                <c:forEach var="faculty" items="${faculties}">
                    <tr>
                        <td>${faculty.id}</td>

                        <td><localizer:name currentLocaleName="${currentLocale}" localizable="${faculty}"/></td>

                        <td>${faculty.totalSeats}</td>
                        <td>${faculty.budgetSeats}</td>
                        <td>
                            <c:forEach var="criterion" items="${faculty.criteria}">
                                <localizer:name currentLocaleName="${currentLocale}" localizable="${criterion}"/><br>
                            </c:forEach>
                        </td>
                        <c:if test="${userRole.name == 'user' and user.statusId != 1}">
                            <td>
                                <c:choose>
                                    <c:when test="${faculty.statusId == 1}">
                                        <span style="color:  rgb(204, 0, 0);"><strong><fmt:message
                                                key="faculties_jsp.text.recruitment_closed"/></strong></span><br>
                                        <a href="controller?command=viewReportSheetPage&facultyId=${faculty.id}"><strong><fmt:message
                                                key="faculties_jsp.button.view_report"/></strong></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="controller?command=viewFacultyApplications&facultyId=${faculty.id}"><strong><fmt:message
                                                key="faculties_jsp.button.view_entrants"/></strong></a>
                                        <c:choose>
                                            <c:when test="${fn:contains(userFaculties, faculty.nameEn)}">
                                                <strong><span style="color: #1B860A"><fmt:message
                                                        key="faculties_jsp.text.already_registered"/></span></strong>
                                            </c:when>
                                            <c:when test="${faculty.statusId ne 1}">
                                                <a href="controller?command=registerForFacultyPage&facultyId=${faculty.id}"><strong><fmt:message
                                                        key="faculties_jsp.button.register"/></strong></a>
                                            </c:when>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <br>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
