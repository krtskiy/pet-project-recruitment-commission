<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Apply for faculty" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf" %>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="registerForFaculty">
                <input type="hidden" name="facultyId" value="${facultyId}">

                <h3><fmt:message key="faculty_register_page_jsp.text.enter_grades"/></h3>

                <c:forEach var="entry" items="${userMarksMap}">
                    <small><c:out value="${currentLocale eq 'uk' ? entry.key.nameUk : entry.key.nameEn}"/>:</small><br>
                    <c:choose>
                        <c:when test="${not empty entry.value.mark}">
                            <input type="number" placeholder="${entry.value.mark}" disabled style="width: 9ch">
                            <input type="hidden" name="marks" value="${entry.value.mark}"><br>
                        </c:when>
                        <c:when test="${entry.key.id == 1}">
                            <input type="number" required name="marks" min="1" max="12"><br>
                        </c:when>
                        <c:otherwise>
                            <input type="number" required name="marks" min="100" max="200"><br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <input type="submit" value="<fmt:message key="faculty_register_page_jsp.button.apply"/>">
            </form>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
