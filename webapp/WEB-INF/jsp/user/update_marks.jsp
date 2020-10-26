<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edit profile" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf" %>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <h3><fmt:message key="update_marks_jsp.text.enter_new_marks"/></h3>
            <h6><fmt:message key="update_profile_jsp.text.empty_message"/></h6>

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="updateMarks">

                <c:forEach var="mark" items="${userMarks}">
                    <small><c:out
                            value="${currentLocale eq 'uk' ? mark.criterionNameUk : mark.criterionNameEn}"/></small><br>
                    <c:choose>
                        <c:when test="${mark.criterionId == 1}">
                            <input type="number" name="${mark.criterionId}" min="1" max="12" placeholder="${mark.mark}"><br>
                        </c:when>
                        <c:otherwise>
                            <input type="number" name="${mark.criterionId}" min="100" max="200" placeholder="${mark.mark}"><br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <input type="submit" value="<fmt:message key="update_profile_jsp.button.apply"/>">
            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
