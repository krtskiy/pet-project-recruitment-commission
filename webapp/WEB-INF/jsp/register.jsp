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

    <c:if test="${userRole.name == 'user'}">
        <c:set var="errorMessage" value="You already registered!" scope="request"/>
        <jsp:forward page="error_page.jsp"/>
    </c:if>

    <tr>
        <td class="content center">

            <h1><fmt:message key="register_jsp.text.registration_form"/></h1>
            <small><fmt:message key="register_jsp.text.transliteration_message"/></small>

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="registerUser">

                <input type="email" name="email" maxlength="255" size="35"
                       required pattern=".*\S+.*" placeholder="Email"><br>

                <input type="password" name="password" minlength="6" maxlength="32" size="35"
                       required pattern=".*\S+.*"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.password"/> "><br>

                <input type="text" name="name" pattern="[A-Za-z]{1,45}" size="35"
                       required title="<fmt:message key="register_jsp.field_popup.name_format"/>"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.name"/>"><br>

                <input type="text" name="surname" pattern="[A-Za-z]{1,45}" size="35"
                       required title="<fmt:message key="register_jsp.field_popup.surname_format"/>"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.surname"/>"><br>

                <input type="text" name="patronymic" pattern="[A-Za-z]{1,45}" size="35"
                       required title="<fmt:message key="register_jsp.field_popup.patronymic_format"/>"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.patronymic"/>"><br>

                <select name="region" id="selectRegion">
                    <option disabled selected><fmt:message key="register_jsp.select_region.placeholder"/></option>
                    <option value="Autonomous Republic of Crimea"><fmt:message
                            key="register_jsp.select_region.crimea"/></option>
                    <option value="Vinnytsia obl."><fmt:message key="register_jsp.select_region.vinnytsia"/></option>
                    <option value="Volyn obl."><fmt:message key="register_jsp.select_region.volyn"/></option>
                    <option value="Dnipropetrovsk obl."><fmt:message
                            key="register_jsp.select_region.dnipropetrovsk"/></option>
                    <option value="Donetsk obl."><fmt:message key="register_jsp.select_region.donetsk"/></option>
                    <option value="Zhytomyr obl."><fmt:message key="register_jsp.select_region.zhytomyr"/></option>
                    <option value="Zakarpattia obl."><fmt:message
                            key="register_jsp.select_region.zakarpattia"/></option>
                    <option value="Zaporizhia obl."><fmt:message key="register_jsp.select_region.zaporizhia"/></option>
                    <option value="Ivano-Frankivsk obl."><fmt:message
                            key="register_jsp.select_region.ivanofrankivsk"/> obl.
                    </option>
                    <option value="Kyiv obl."><fmt:message key="register_jsp.select_region.kyiv"/></option>
                    <option value="Kirovohrad obl."><fmt:message
                            key="register_jsp.select_region.kirovohrad"/></option>
                    <option value="Luhansk obl."><fmt:message key="register_jsp.select_region.luhansk"/></option>
                    <option value="Lviv obl."><fmt:message key="register_jsp.select_region.lviv"/></option>
                    <option value="Mykolaiv obl."><fmt:message key="register_jsp.select_region.mykolaiv"/></option>
                    <option value="Odessa obl."><fmt:message key="register_jsp.select_region.odesa"/></option>
                    <option value="Poltava obl."><fmt:message key="register_jsp.select_region.poltava"/></option>
                    <option value="Rivne obl."><fmt:message key="register_jsp.select_region.rivne"/></option>
                    <option value="Sumy obl."><fmt:message key="register_jsp.select_region.sumy"/></option>
                    <option value="Ternopil obl."><fmt:message key="register_jsp.select_region.ternopil"/></option>
                    <option value="Kharkiv obl."><fmt:message key="register_jsp.select_region.kharkiv"/></option>
                    <option value="Kherson obl."><fmt:message key="register_jsp.select_region.kherson"/></option>
                    <option value="Khmelnytskyi obl."><fmt:message
                            key="register_jsp.select_region.khmelnytskyi"/></option>
                    <option value="Cherkasy obl."><fmt:message key="register_jsp.select_region.cherkasy"/></option>
                    <option value="Chernivtsi obl."><fmt:message key="register_jsp.select_region.chernivtsi"/></option>
                    <option value="Chernihiv obl."><fmt:message key="register_jsp.select_region.chernihiv"/></option>
                </select><br>

                <input type="text" name="city" size="35"
                       required pattern="[A-Za-z]{1,45}" title="<fmt:message key="register_jsp.field_popup.city_format"/>"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.city"/>"><br>

                <input type="text" name="institutionName" size="35"
                       required title="<fmt:message key="register_jsp.field_popup.institution_format"/>"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.institution"/>"><br>

                <input type="submit" value="<fmt:message key="register_jsp.button.submit"/> ">
            </form>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
