<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Welcome" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <h1><fmt:message key="welcome_jsp.text.welcome"/></h1><br>

            <h3><fmt:message key="welcome_jsp.list.about_us"/>:</h3>
            <ul>
                <li><fmt:message key="welcome_jsp.list.li1"/></li>
                <li><fmt:message key="welcome_jsp.list.li2"/></li>
                <li><fmt:message key="welcome_jsp.list.li3"/></li>
                <li><fmt:message key="welcome_jsp.list.li4"/></li>
            </ul>

            <p><fmt:message key="welcome_jsp.text.p1"/></p>

            <img src="images/university2.jpg" alt="image1" width="480" height="300">

            <p><fmt:message key="welcome_jsp.text.p2"/></p>

            <img src="images/university3.jpeg" alt="image2" width="480" height="300">

            <p><fmt:message key="welcome_jsp.text.p3"/></p>

            <img src="images/university4.jpg" alt="image3" width="480" height="300">

            <p><fmt:message key="welcome_jsp.text.p4"/></p>


        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>

