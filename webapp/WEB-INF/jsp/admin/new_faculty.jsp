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

                <input type="number" name="id" min="0" placeholder="Id" style="width: 250px"><br>
                <input type="text" name="nameEn" maxlength="100" placeholder="Name in English" style="width: 250px"><br>
                <input type="text" name="nameUk" maxlength="100" placeholder="Name in Ukrainian"
                       style="width: 250px"><br>
                <input type="number" name="totalSeats" min="0" placeholder="Total amount of seats" style="width: 250px"><br>
                <input type="number" name="budgetSeats" min="0" placeholder="Amount of budget seats"
                       style="width: 250px"><br>

                <strong>Select criteria for selection </strong><small>(select 4):</small><br>
                <c:forEach var="criterion" items="${criteria}">
                    <c:choose>
                        <c:when test="${criterion.id == 1}">
                            <input type="checkbox" name="criterionId" value="${criterion.id}" checked onclick="return false;"> ${criterion.nameEn}<br>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="criterionId" value="${criterion.id}"> ${criterion.nameEn}<br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <input type="submit" value="Submit">
            </form>


        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
    $("input[name=criterionId]").change(function () {
        var max = 4;
        if ($("input[name=criterionId]:checked").length == max) {
            $("input[name=criterionId]").attr('disabled', 'disabled');
            $("input[name=criterionId]:checked").removeAttr('disabled');
        } else {
            $("input[name=criterionId]").removeAttr('disabled');
        }
    })
</script>
</body>
</html>
