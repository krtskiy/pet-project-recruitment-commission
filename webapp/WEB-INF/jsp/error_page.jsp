<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Something went wrong!" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body><table id="main-container">

<%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content center">
<h3>Something went wrong!</h3>
        </td>
    </tr>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
