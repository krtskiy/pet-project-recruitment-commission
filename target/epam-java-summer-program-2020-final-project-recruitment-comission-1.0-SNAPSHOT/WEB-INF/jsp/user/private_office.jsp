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

            <c:if test="${not empty requestScope.successMessage}">
                <span style="color: #1B860A">Login data changed successfully</span>
            </c:if>

            <h3>Personal information:</h3>
            <p><c:out value="Email | ${user.email}"/></p>
            <p><c:out value="Name | ${user.name}"/></p>
            <p><c:out value="Surname | ${user.surname}"/></p>
            <p><c:out value="Patronymic | ${user.patronymic}"/></p>
            <p><c:out value="Region | ${user.region}"/></p>
            <p><c:out value="City | ${user.city}"/></p>
            <p><c:out value="Educational institution | ${user.institutionName}"/></p>

            <a href="controller?command=updateUserPage"><strong>Change login data</strong></a>

            <c:if test="${not empty userMarks}">
                <h3>Your marks:</h3>
                <c:forEach var="mark" items="${userMarks}">
                    <c:choose>
                        <c:when test="${currentLocale == 'uk'}">
                            <p>${criteria[mark.criterionId - 1].nameUk} : <strong>${mark.mark}</strong></p>
                        </c:when>
                        <c:when test="${currentLocale == 'en'}">
                            <p>${criteria[mark.criterionId - 1].nameEn} : <strong>${mark.mark}</strong></p>
                        </c:when>
                        <c:otherwise>
                            <p>${criteria[mark.criterionId - 1].nameEn} : <strong>${mark.mark}</strong></p>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

            <c:if test="${not empty applications}">
                <h3>You applied for:</h3>
                <c:forEach var="application" items="${applications}">
                    <c:choose>
                        <c:when test="${currentLocale == 'uk'}">
                            <p>${faculties[application.facultyId - 1].nameUk}</p>
                        </c:when>
                        <c:when test="${currentLocale == 'en'}">
                            <p>${faculties[application.facultyId - 1].nameEn}</p>
                        </c:when>
                        <c:otherwise>
                            <p>${faculties[application.facultyId - 1].nameEn}</p>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
