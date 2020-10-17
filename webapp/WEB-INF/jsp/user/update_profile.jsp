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

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="updateUser">

                <h3><fmt:message key="update_profile_jsp.text.enter_new"/></h3>
                <small><fmt:message key="update_profile_jsp.text.empty_message"/> </small><br>
                <input type="email" name="email" maxlength="255"
                       placeholder="<fmt:message key="update_profile_jsp.placeholder.new_email"/>"><br>
                <input type="password" name="password" minlength="6" maxlength="32"
                       placeholder="<fmt:message key="update_profile_jsp.placeholder.new_password"/>"><br>

                <div class="g-recaptcha"
                     data-sitekey="6LfrcdgZAAAAAF1KQ5-FSgCYsJn28fW4ZZyJQC_3"></div>

                <input type="submit" value="<fmt:message key="update_profile_jsp.button.apply"/>">
            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
