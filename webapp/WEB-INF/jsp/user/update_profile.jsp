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
                <h6><fmt:message key="update_profile_jsp.text.empty_message"/></h6>
                <input type="email" name="email" maxlength="255"
                       placeholder="<fmt:message key="update_profile_jsp.placeholder.new_email"/>"><br>
                <input type="password" name="password" minlength="6" maxlength="32" id="password"
                       placeholder="<fmt:message key="update_profile_jsp.placeholder.new_password"/>"><br>
                <input type="password" minlength="6" maxlength="32" id="confirm_password"
                       placeholder="<fmt:message key="register_jsp.placeholder.confirm_password"/>"><br>

                <div class="g-recaptcha"
                     data-sitekey="6LfrcdgZAAAAAF1KQ5-FSgCYsJn28fW4ZZyJQC_3"></div>

                <input type="submit" value="<fmt:message key="update_profile_jsp.button.apply"/>">
            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>
</html>
