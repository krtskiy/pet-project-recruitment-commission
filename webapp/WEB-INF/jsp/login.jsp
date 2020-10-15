<%@ page contentType="text/html; charset=UTF-8" %>
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

            <c:choose>
                <c:when test="${not empty user}">
                    <h3><span style="color: rgb(204, 0, 0); "><fmt:message key="login_jsp.text.already_logged"/></span>
                    </h3>
                </c:when>
                <c:otherwise>

                    <c:if test="${not empty successRegMessage}">
                        <h3><span style="color: #1B860A"><fmt:message key="login_jsp.text.success_message"/> </span>
                        </h3><br>
                        <% request.getSession().removeAttribute("successRegMessage"); %>
                    </c:if>

                    <h1><fmt:message key="login_jsp.text.login_form"/></h1>

                    <form id="login_form" method="post" action="controller">
                        <input type="hidden" name="command" value="login">

                        <input type="email" name="email" maxlength="255" placeholder="Email"><br>
                        <input type="password" name="password" maxlength="32"
                               placeholder="<fmt:message key="login_jsp.placeholder.password"/> "><br>

                        <input type="submit" value="<fmt:message key="login_jsp.button.log_in"/> ">
                    </form>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
