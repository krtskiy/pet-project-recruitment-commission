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
                <c:choose>
                    <c:when test="${currentLocale == 'uk'}">
                        ${facultyRegisteredFor.nameUk}<br>
                    </c:when>
                    <c:when test="${currentLocale == 'en'}">
                        ${facultyRegisteredFor.nameEn}<br>
                    </c:when>
                    <c:otherwise>
                        ${facultyRegisteredFor.nameEn}<br>
                    </c:otherwise>
                </c:choose>
                </span></h3>
                <% request.getSession().removeAttribute("facultyRegisteredFor"); %>
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
                        <c:choose>
                            <c:when test="${currentLocale == 'uk'}">
                                <td>${faculty.nameUk}</td>
                            </c:when>
                            <c:when test="${currentLocale == 'en'}">
                                <td>${faculty.nameEn}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${faculty.nameEn}</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${faculty.totalSeats}</td>
                        <td>${faculty.budgetSeats}</td>
                        <td>
                            <c:forEach var="criterion" items="${faculty.criteria}">
                                <c:choose>
                                    <c:when test="${currentLocale == 'uk'}">
                                        ${criterion.nameUk}<br>
                                    </c:when>
                                    <c:when test="${currentLocale == 'en'}">
                                        ${criterion.nameEn}<br>
                                    </c:when>
                                    <c:otherwise>
                                        ${criterion.nameEn}<br>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </td>
                        <c:if test="${userRole.name == 'user' and user.statusId != 1}">
                            <td>
                                <a href="controller?command=viewFacultyApplications&facultyId=${faculty.id}"><strong><fmt:message
                                        key="faculties_jsp.button.view_entrants"/></strong></a>
                                <c:choose>
                                    <c:when test="${fn:contains(userFaculties, faculty.id)}">
                                        <strong><fmt:message key="faculties_jsp.text.already_registered"/></strong>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="controller?command=registerForFacultyPage&facultyId=${faculty.id}"><strong><fmt:message
                                                key="faculties_jsp.button.register"/></strong></a>
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
