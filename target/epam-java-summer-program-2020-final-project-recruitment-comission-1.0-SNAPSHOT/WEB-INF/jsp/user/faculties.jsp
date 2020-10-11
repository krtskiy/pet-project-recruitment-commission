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

            <strong>Sort by:</strong>
            <a href="controller?command=listOfFaculties&page=1&sort=nameAsc">
                <c:choose>
                    <c:when test="${param.sort == 'nameAsc'}">
                        <strong>Name (a-z)</strong>
                    </c:when>
                    <c:when test="${param.sort != 'nameAsc'}">
                        Name (a-z)
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&page=1&sort=nameDesc">
                <c:choose>
                    <c:when test="${param.sort == 'nameDesc'}">
                        <strong>Name (z-a)</strong>
                    </c:when>
                    <c:when test="${param.sort != 'nameDesc'}">
                        Name (z-a)
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&sort=budgetSeats">
                <c:choose>
                    <c:when test="${param.sort == 'budgetSeats'}">
                        <strong>Budget seats</strong>
                    </c:when>
                    <c:when test="${param.sort != 'budgetSeats'}">
                        Budget seats
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfFaculties&sort=totalSeats">
                <c:choose>
                    <c:when test="${param.sort == 'totalSeats'}">
                        <strong>Total seats</strong>
                    </c:when>
                    <c:when test="${param.sort != 'totalSeats'}">
                        Total seats
                    </c:when>
                </c:choose>
            </a>

            <table id="list_faculties_table">
                <thead>
                <tr>
                    <th>№</th>
                    <th>Faculty name</th>
                    <th>Total seats</th>
                    <th>Budget seats</th>
                    <th>Selection criteria</th>
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
                        <c:if test="${userRole.name == 'user'}">
                            <td>
                                <a href="controller?command=registerForFacultyPage&facultyId=${faculty.id}"><strong>Register
                                    for faculty</strong></a>
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
