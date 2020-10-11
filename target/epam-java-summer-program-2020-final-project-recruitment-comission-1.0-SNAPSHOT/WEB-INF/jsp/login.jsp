<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Log In" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <c:if test="${not empty successRegMessage}">
                <h3><span style="color: #1B860A"><c:out value="${successRegMessage}"/></span></h3>
                <% request.getSession().removeAttribute("successRegMessage"); %>
            </c:if>

            <h1>Login form:</h1>

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="login">

                <input type="email" name="email" maxlength="255" placeholder="Email"><br>
                <input type="password" name="password" maxlength="32" placeholder="Password"><br>

                <input type="submit" value="Log In">
            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
