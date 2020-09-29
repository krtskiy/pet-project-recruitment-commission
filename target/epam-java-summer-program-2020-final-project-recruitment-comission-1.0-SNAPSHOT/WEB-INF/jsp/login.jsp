<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1>Login:</h1>

<form method="post" action="controller" class="form-style-7">
    <input type="hidden" name="command" value="login">

    <label>
        <input type="email" name="email" maxlength="255">
    </label>Email<br>

    <label>
        <input type="password" name="password" maxlength="32">
    </label>Password<br>

    <input type="submit" value="Login">
</form>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
