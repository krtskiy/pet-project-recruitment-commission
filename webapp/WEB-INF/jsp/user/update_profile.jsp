<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edit profile" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf"%>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="updateUser">

                <h3>Enter new login data</h3>
                <small>leave the field blank if dont want to change it</small><br>
                <input type="email" name="email" maxlength="255" placeholder="New email"><br>
                <input type="password" name="password" minlength="6" maxlength="32" placeholder="New password"><br>

                <input type="submit" value="Apply">
            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
