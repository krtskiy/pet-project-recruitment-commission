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
            <h3><fmt:message key="update_faculty_jsp.text.new_info"/></h3>
            <h6><fmt:message key="update_faculty_jsp.text.new_info_hint"/></h6>

            <form id="login_form" method="post" action="controller">
                <input type="hidden" name="command" value="updateFaculty">
                <input type="hidden" name="facultyId" value="${faculty.id}">

                <small><fmt:message key="update_faculty_jsp.placeholder.new_name_en"/>:</small><br>
                <input type="text" name="nameEn" maxlength="100"
                       placeholder="${faculty.nameEn}"
                       style="width: 250px"><br>
                <small><fmt:message key="update_faculty_jsp.placeholder.new_name_uk"/>:</small><br>
                <input type="text" name="nameUk" maxlength="100"
                       placeholder="${faculty.nameUk}"
                       style="width: 250px"><br>
                <small><fmt:message key="update_faculty_jsp.placeholder.new_total_seats"/>:</small><br>
                <input type="number" name="totalSeats" min="0"
                       placeholder="${faculty.totalSeats}"
                       style="width: 250px"><br>
                <small><fmt:message key="update_faculty_jsp.placeholder.new_budget_seats"/>:</small><br>
                <input type="number" name="budgetSeats" min="0"
                       placeholder="${faculty.budgetSeats}"
                       style="width: 250px"><br>

                <c:choose>
                    <c:when test="${isEmpty eq true}">
                        <h6><span style="color: rgb(204, 0, 0); "><fmt:message
                                key="update_faculty_jsp.text.if_not_empty_applications"/><br><fmt:message
                                key="update_faculty_jsp.text.can_not_change"/></span>
                        </h6>
                    </c:when>
                    <c:otherwise>
                        <strong><fmt:message key="update_faculty_jsp.text.new_criteria"/> </strong><small><fmt:message
                            key="update_faculty_jsp.text.new_criteria_quantity"/></small><br>
                        <c:forEach var="criterion" items="${criteria}">
                            <input type="checkbox" name="criterionId"
                                   value="${criterion.id}"> <localizer:name currentLocaleName="${currentLocale}" localizable="${criterion}"/><br>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


                <input type="submit" value="<fmt:message key="update_faculty_jsp.button.apply"/>">
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
