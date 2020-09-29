<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1>Registration form:</h1><br>

<form method="post" action="controller" class="form-style-7">
    <input type="hidden" name="command" value="registerUser">

    <label>
        <input type="text" name="name" maxlength="45">
    </label>Name<br>

    <label>
        <input type="text" name="surname" maxlength="45">
    </label>Surname<br>

    <label>
        <input type="text" name="patronymic" maxlength="45">
    </label>Patronymic<br>

    <label>
        <input type="email" name="email" maxlength="255">
    </label>Email<br>

    <label>
        <input type="password" name="password" minlength="6" maxlength="32">
    </label>Password<br>

    <input type="submit" value="Register">


</form>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
