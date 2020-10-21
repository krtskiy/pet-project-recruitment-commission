<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Reset password" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <c:choose>
                <c:when test="${not empty user}">
                    <h3><span style="color: rgb(204, 0, 0); "><fmt:message key="login_jsp.text.already_logged"/></span>
                    </h3>
                </c:when>
                <c:otherwise>

                    <h1><fmt:message key="reset_password_jsp.text.reset"/></h1>
                    <h6><fmt:message key="reset_password_jsp.text.reset_hint"/></h6>

                    <form id="login_form" method="post" action="controller">
                        <input type="hidden" name="command" value="resetPassword">

                        <input type="email" name="email" maxlength="255" placeholder="Email"><br>

                        <input type="text" name="name" pattern="[A-Za-z]{1,45}"
                               required title="<fmt:message key="register_jsp.field_popup.name_format"/>"
                               placeholder="<fmt:message key="register_jsp.placeholder.name"/>"><br>

                        <input type="text" name="surname" pattern="[A-Za-z]{1,45}"
                               required title="<fmt:message key="register_jsp.field_popup.surname_format"/>"
                               placeholder="<fmt:message key="register_jsp.placeholder.surname"/>"><br>

                        <div class="g-recaptcha"
                             data-sitekey="6LfrcdgZAAAAAF1KQ5-FSgCYsJn28fW4ZZyJQC_3"></div>

                        <input type="submit" value="<fmt:message key="reset_password_jsp.button.reset"/>">
                    </form>

                </c:otherwise>
            </c:choose>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
