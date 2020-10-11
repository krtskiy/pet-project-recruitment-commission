<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edit faculty" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/blocked_user.jspf" %>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="updateFaculty">

                <h3>Enter new faculty info</h3>
                <small>leave the field blank if dont want to change it</small><br>
                <input type="text" name="nameEn" maxlength="100" placeholder="New name in English" style="width: 250px"><br>
                <input type="text" name="nameUk" maxlength="100" placeholder="New name in Ukrainian"
                       style="width: 250px"><br>
                <input type="number" name="totalSeats" min="0" placeholder="New total amount of seats"
                       style="width: 250px"><br>
                <input type="number" name="budgetSeats" min="0" placeholder="New amount of budget seats"
                       style="width: 250px"><br>

                <strong>New criteria for selection </strong><small>(select 4):</small><br>
                <c:forEach var="criterion" items="${criteria}">
                    <input type="checkbox" name="criterionId" value="${criterion.id}"> ${criterion.nameEn}<br>
                </c:forEach>

                <input type="submit" value="Apply">
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
