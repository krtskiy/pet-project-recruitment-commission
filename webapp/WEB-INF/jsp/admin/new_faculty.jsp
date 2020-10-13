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

            <h1><fmt:message key="new_faculty_jsp.text.add_faculty"/></h1>
            <h4></h4>

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="createNewFaculty">

                <input type="number" name="id" min="0" placeholder="<fmt:message key="new_faculty_jsp.placeholder.faculty_number"/>" style="width: 250px"><br>
                <input type="text" name="nameEn" maxlength="100" placeholder="<fmt:message key="update_faculty_jsp.placeholder.new_name_en"/>" style="width: 250px"><br>
                <input type="text" name="nameUk" maxlength="100" placeholder="<fmt:message key="update_faculty_jsp.placeholder.new_name_uk"/>"
                       style="width: 250px"><br>
                <input type="number" name="totalSeats" min="0" placeholder="<fmt:message key="update_faculty_jsp.placeholder.new_total_seats"/>" style="width: 250px"><br>
                <input type="number" name="budgetSeats" min="0" placeholder="<fmt:message key="update_faculty_jsp.placeholder.new_budget_seats"/>"
                       style="width: 250px"><br>

                <strong><fmt:message key="new_faculty_jsp.text.criteria"/> </strong><small><fmt:message key="new_faculty_jsp.text.criteria_quantity"/></small><br>
                <c:forEach var="criterion" items="${criteria}">
                    <c:choose>
                        <c:when test="${currentLocale == 'uk'}">
                            <input type="checkbox" name="criterionId" value="${criterion.id}"> ${criterion.nameUk}<br>
                        </c:when>
                        <c:when test="${currentLocale == 'en'}">
                            <input type="checkbox" name="criterionId" value="${criterion.id}"> ${criterion.nameEn}<br>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="criterionId" value="${criterion.id}"> ${criterion.nameEn}<br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <input type="submit" value="<fmt:message key="new_faculty_jsp.button.submit"/>">
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
