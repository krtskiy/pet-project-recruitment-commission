<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Welcome" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%-- TODO l10n --%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <h1>Welcome to the website of the recruitment commission of our university! Glad to see you here!</h1><br>
            <h3>About us:</h3>
            <ul>
                <li>One of the most rated universities in Europe</li>
                <li>The most qualified teachers in Ukraine</li>
                <li>3 faculties, 5,000 students, 100,000+ graduates</li>
            </ul>
            <br>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>

