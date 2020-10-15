<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List of faculties (admin rights)" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <c:if test="${not empty successNewFacMessage}">
                <h3><span style="color: #1B860A"><c:out value="${successNewFacMessage}"/></span></h3><br>
                <c:remove var="successNewFacMessage" scope="session"/>
            </c:if>

            <c:if test="${not empty successFacEditMessage}">
                <h3><span style="color: #1B860A"><c:out value="${successFacEditMessage}"/></span></h3><br>
                <c:remove var="successFacEditMessage" scope="session"/>
            </c:if>

            <strong><fmt:message key="faculties_jsp.text.sort_by"/></strong>
            <a href="controller?command=listOfFaculties&page=1&sort=number">
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
            <a href="controller?command=listOfFaculties&page=1&sort=nameDesc">
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
                        <td><localizer:facultyName currentLocaleName="${currentLocale}" faculty="${faculty}"/></td>
                        <td>${faculty.totalSeats}</td>
                        <td>${faculty.budgetSeats}</td>
                        <td>
                            <c:forEach var="criterion" items="${faculty.criteria}">
                                <localizer:criterionName currentLocaleName="${currentLocale}" criterion="${criterion}"/><br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${faculty.statusId == 1}">
                                    <span style="color:  rgb(204, 0, 0);"><strong><fmt:message
                                            key="faculties_jsp.text.recruitment_closed"/></strong></span><br>
                                    <a href="controller?command=viewReportSheetPage&facultyId=${faculty.id}"><strong><fmt:message
                                            key="faculties_jsp.button.view_report"/></strong></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="controller?command=deleteFaculty&facultyId=${faculty.id}"><strong><fmt:message
                                            key="faculties_admin_jsp.button.delete"/></strong></a>
                                    <a href="controller?command=updateFacultyPage&facultyId=${faculty.id}"><strong><fmt:message
                                            key="faculties_admin_jsp.button.edit"/></strong></a>
                                    <a href="controller?command=viewFacultyApplications&facultyId=${faculty.id}"><strong><fmt:message
                                            key="faculties_admin_jsp.button.view_entrants"/></strong></a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <a href="controller?command=createNewFacultyPage"><strong><fmt:message
                    key="faculties_admin_jsp.button.add_faculty"/></strong></a>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
