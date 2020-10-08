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

    <c:if test="${userRole.name == 'admin' or userRole.name == 'user'}">
        <c:set var="errorMessage" value="You already registered!"/>
        <jsp:forward page="error_page.jsp"/>
    </c:if>

    <tr>
        <td class="content center">

            <h1><fmt:message key="register_jsp.text.registration_form"/></h1>
            <h4></h4>

            <form id="register_form" method="post" action="controller">
                <input type="hidden" name="command" value="registerUser">

                <input type="email" name="email" maxlength="255" size="35"
                       required pattern=".*\S+.*" title="Enter valid email address"
                       placeholder="Email"><br>

                <input type="password" name="password" minlength="6" maxlength="32" size="35"
                       required pattern=".*\S+.*" title="Use 6 to 32 characters for your password"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.password"/> "><br>

                <input type="text" name="name" pattern="[А-Яа-яҐґЄєЇїІі'`’ʼ]{1,45}" size="35"
                       required title="Enter your name in Ukrainian transliteration"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.name"/>"><br>

                <input type="text" name="surname" pattern="[А-Яа-яҐґЄєЇїІі'`’ʼ]{1,45}" size="35"
                       required title="Enter your surname in Ukrainian transliteration"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.surname"/>"><br>

                <input type="text" name="patronymic" pattern="[А-Яа-яҐґЄєЇїІі'`’ʼ]{1,45}" size="35"
                       required title="Enter your patronymic in Ukrainian transliteration"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.patronymic"/>"><br>

                <select name="region" id="selectRegion">
                    <option disabled selected><fmt:message key="register_jsp.select_region.placeholder"/></option>
                    <option value="АР Крим"><fmt:message key="register_jsp.select_region.crimea"/></option>
                    <option value="Вінницька обл."><fmt:message key="register_jsp.select_region.vinnytsia"/></option>
                    <option value="Волинська обл."><fmt:message key="register_jsp.select_region.volyn"/></option>
                    <option value="Дніпропетровська обл."><fmt:message
                            key="register_jsp.select_region.dnipropetrovsk"/></option>
                    <option value="Донецька обл."><fmt:message key="register_jsp.select_region.donetsk"/></option>
                    <option value="Житомирська обл."><fmt:message key="register_jsp.select_region.zhytomyr"/></option>
                    <option value="Закарпатська обл."><fmt:message
                            key="register_jsp.select_region.zakarpattia"/></option>
                    <option value="Запорізька обл."><fmt:message key="register_jsp.select_region.zaporizhia"/></option>
                    <option value="Івано-Франківська обл."><fmt:message
                            key="register_jsp.select_region.ivanofrankivsk"/> obl.
                    </option>
                    <option value="Київська обл."><fmt:message key="register_jsp.select_region.kyiv"/></option>
                    <option value="Кіровоградська обл."><fmt:message
                            key="register_jsp.select_region.kirovohrad"/></option>
                    <option value="Луганська обл."><fmt:message key="register_jsp.select_region.luhansk"/></option>
                    <option value="Львівська обл."><fmt:message key="register_jsp.select_region.lviv"/></option>
                    <option value="Миколаївська обл."><fmt:message key="register_jsp.select_region.mykolaiv"/></option>
                    <option value="Одеська обл."><fmt:message key="register_jsp.select_region.odesa"/></option>
                    <option value="Полтавська обл."><fmt:message key="register_jsp.select_region.poltava"/></option>
                    <option value="Рівненська обл."><fmt:message key="register_jsp.select_region.rivne"/></option>
                    <option value="Сумська обл."><fmt:message key="register_jsp.select_region.sumy"/></option>
                    <option value="Тернопільська обл."><fmt:message key="register_jsp.select_region.ternopil"/></option>
                    <option value="Харківська обл."><fmt:message key="register_jsp.select_region.kharkiv"/></option>
                    <option value="Херсонська обл."><fmt:message key="register_jsp.select_region.kherson"/></option>
                    <option value="Хмельницька обл."><fmt:message
                            key="register_jsp.select_region.khmelnytskyi"/></option>
                    <option value="Черкаська обл."><fmt:message key="register_jsp.select_region.cherkasy"/></option>
                    <option value="Чернівецька обл."><fmt:message key="register_jsp.select_region.chernivtsi"/></option>
                    <option value="Чернігівська обл."><fmt:message key="register_jsp.select_region.chernihiv"/></option>
                </select><br>

                <input type="text" name="city" size="35"
                       required pattern=".*\S+.*" title="Enter your city"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.city"/>"><br>

                <input type="text" name="institutionName" size="35"
                       required pattern=".*\S+.*" title="Enter your educational institution name"
                       placeholder="<fmt:message key="register_jsp.field_placeholder.institution"/>"><br>

                <input type="submit" value="<fmt:message key="register_jsp.button.submit"/> ">
            </form>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
