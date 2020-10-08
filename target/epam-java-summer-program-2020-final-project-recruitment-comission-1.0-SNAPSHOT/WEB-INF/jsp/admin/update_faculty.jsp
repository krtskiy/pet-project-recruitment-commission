<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edit faculty" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf"%>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="updateFaculty">

                <h3>Enter new faculty info</h3>
                <small>leave the field blank if dont want to change it</small><br>
                <input type="text" name="nameEn" maxlength="100" placeholder="New name in English" style="width: 250px"><br>
                <input type="text" name="nameUk" maxlength="100" placeholder="New name in Ukrainian" style="width: 250px"><br>
                <input type="number" name="totalSeats" placeholder="New total amount of seats" style="width: 250px"><br>
                <input type="number" name="budgetSeats" placeholder="New amount of budget seats" style="width: 250px"><br>

                <input type="submit" value="Apply">
            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
