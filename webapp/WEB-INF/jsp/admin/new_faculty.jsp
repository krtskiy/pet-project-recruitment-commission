<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Sign Up" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<script src="https://www.google.com/recaptcha/api.js"></script>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>


    <tr>
        <td class="content center">

            <h1><fmt:message key="register_jsp.text.registration_form"/></h1>
            <h4></h4>

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="createNewFaculty">

                <input type="number" name="id" placeholder="Id" style="width: 250px"><br>
                <input type="text" name="nameEn" maxlength="100" placeholder="Name in English" style="width: 250px"><br>
                <input type="text" name="nameUk" maxlength="100" placeholder="Name in Ukrainian" style="width: 250px"><br>
                <input type="number" name="totalSeats" placeholder="Total amount of seats" style="width: 250px"><br>
                <input type="number" name="budgetSeats" placeholder="Amount of budget seats" style="width: 250px"><br>

                <input type="submit" value="Submit">
            </form>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
