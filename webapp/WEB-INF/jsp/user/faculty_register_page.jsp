<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Sign Up" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<script src="https://www.google.com/recaptcha/api.js"></script>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf" %>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="registerForFaculty">

                <h3><fmt:message key="faculty_register_page_jsp.text.enter_grades"/></h3>
                <c:forEach var="criterion" items="${faculty.criteria}">
                    <c:choose>
                        <c:when test="${criterion.id == 1}">
                            <small><localizer:name currentLocaleName="${currentLocale}"
                                                            localizable="${criterion}"/>:</small><br><input type="number"
                                                                                                          required
                                                                                                          name="marks"
                                                                                                          min="0"
                                                                                                          max="12"><br>
                        </c:when>
                        <c:otherwise>
                            <small><localizer:name currentLocaleName="${currentLocale}"
                                                            localizable="${criterion}"/>:</small><br><input type="number"
                                                                                                          required
                                                                                                          name="marks"
                                                                                                          min="0"
                                                                                                          max="200"><br>
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
