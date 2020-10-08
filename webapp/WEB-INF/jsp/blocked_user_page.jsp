<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">
            <%-- CONTENT --%>

            <h2 class="error">
                You are blocked by an administrator.<br>
                Contacts: admin@krtskiy.com
            </h2>


        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>