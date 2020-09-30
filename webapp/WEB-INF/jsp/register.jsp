<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Sign Up" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <h1>Registration form:</h1>

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="registerUser">

                <input type="email" name="email" maxlength="255" size="35"
                       required pattern=".*\S+.*" title="Enter valid email address"
                       placeholder="Email"><br>

                <input type="password" name="password" minlength="6" maxlength="32" size="35"
                       required pattern=".*\S+.*" title="Use 6 to 32 characters for your password"
                       placeholder="Password (6-32 characters)"><br>

                <input type="text" name="name" pattern="[А-Яа-яҐґЄєЇїІі'`’ʼ]{1,45}" size="35"
                       required pattern=".*\S+.*" title="Enter your name in Ukrainian transliteration"
                       placeholder="Name in Ukrainian transliteration"><br>

                <input type="text" name="surname" pattern="[А-Яа-яҐґЄєЇїІі'`’ʼ]{1,45}" size="35"
                       required pattern=".*\S+.*" title="Enter your surname in Ukrainian transliteration"
                       placeholder="Surname in Ukrainian transliteration"><br>

                <input type="text" name="patronymic" pattern="[А-Яа-яҐґЄєЇїІі'`’ʼ]{1,45}" size="35"
                       required pattern=".*\S+.*" title="Enter your patronymic in Ukrainian transliteration"
                       placeholder="Patronymic in Ukrainian transliteration"><br>

                <input type="submit" value="Register">
            </form>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
