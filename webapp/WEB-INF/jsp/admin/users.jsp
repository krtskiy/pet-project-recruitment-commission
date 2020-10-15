<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List of users" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">

            <h3><fmt:message key="users_jsp.text.list_of_users"/></h3>
            <strong><fmt:message key="users_jsp.text.sort_by"/></strong>
            <a href="controller?command=listOfUsers&page=1&sort=id">
                <c:choose>
                    <c:when test="${param.sort == 'id'}">
                        <strong>Id</strong>
                    </c:when>
                    <c:when test="${param.sort != 'id'}">
                        Id
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfUsers&page=1&sort=surnameAsc">
                <c:choose>
                    <c:when test="${param.sort == 'surnameAsc'}">
                        <strong><fmt:message key="users_jsp.button.sort_name_az"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'surnameAsc'}">
                        <fmt:message key="users_jsp.button.sort_name_az"/>
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfUsers&page=1&sort=surnameDesc">
                <c:choose>
                    <c:when test="${param.sort == 'surnameDesc'}">
                        <strong><fmt:message key="users_jsp.button.sort_name_za"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'surnameDesc'}">
                        <fmt:message key="users_jsp.button.sort_name_za"/>
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfUsers&page=1&sort=emailAsc">
                <c:choose>
                    <c:when test="${param.sort == 'emailAsc'}">
                        <strong>Email (a-z)</strong>
                    </c:when>
                    <c:when test="${param.sort != 'emailAsc'}">
                        Email (a-z)
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfUsers&page=1&sort=emailDesc">
                <c:choose>
                    <c:when test="${param.sort == 'emailDesc'}">
                        <strong>Email (z-a)</strong>
                    </c:when>
                    <c:when test="${param.sort != 'emailDesc'}">
                        Email (z-a)
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfUsers&page=1&sort=banStatus">
                <c:choose>
                    <c:when test="${param.sort == 'banStatus'}">
                        <strong><fmt:message key="users_jsp.button.sort_ban_status"/></strong>
                    </c:when>
                    <c:when test="${param.sort != 'banStatus'}">
                        <fmt:message key="users_jsp.button.sort_ban_status"/>
                    </c:when>
                </c:choose>
            </a>

            <table id="list_users_table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Email</th>
                    <th><fmt:message key="users_jsp.text.user_name"/></th>
                    <th><fmt:message key="users_jsp.text.user_surname"/></th>
                    <th><fmt:message key="users_jsp.text.user_status"/></th>
                </tr>
                </thead>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.statusId == 0}">
                                    <fmt:message key="users_jsp.text.unblocked"/>
                                    <c:if test="${user.roleId == 1}">
                                        <br><span style="color: #1B860A">Admin</span>
                                    </c:if>
                                    <form action="controller" method="post">
                                        <input name="command" type="hidden" value="updateUsersStatus"/>
                                        <input name="userId" type="hidden" value="${user.id}">
                                        <input name="sort" type="hidden" value="${sortParam}">
                                        <input name="page" type="hidden" value="${param.page}">
                                        <c:choose>
                                            <c:when test="${user.statusId == 0 and user.roleId != 1}">
                                                <button name="status" value="1">
                                                    <fmt:message key="users_jsp.button.block"/>
                                                </button>
                                            </c:when>
                                            <c:when test="${user.statusId == 1 and user.roleId != 1}">
                                                <button name="status" value="0">
                                                    <fmt:message key="users_jsp.button.unblock"/>
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </c:when>
                                <c:when test="${user.statusId == 1}">
                                    <span style="color: rgb(204, 0, 0); "><fmt:message
                                            key="users_jsp.text.blocked"/></span>
                                    <form action="controller" method="post">
                                        <input name="command" type="hidden" value="updateUsersStatus"/>
                                        <input name="userId" type="hidden" value="${user.id}">
                                        <input name="sort" type="hidden" value="${sortParam}">
                                        <input name="page" type="hidden" value="${param.page}">
                                        <c:choose>
                                            <c:when test="${user.statusId == 0 and user.roleId != 1}">
                                                <button name="status" value="1">
                                                    <fmt:message key="users_jsp.button.block"/>
                                                </button>
                                            </c:when>
                                            <c:when test="${user.statusId == 1 and user.roleId != 1}">
                                                <button name="status" value="0">
                                                    <fmt:message key="users_jsp.button.unblock"/>
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </c:when>
                            </c:choose>
                        </td>
                        <td><a href="controller?command=viewUserProfilePage&userId=${user.id}"/><strong><fmt:message
                                key="users_jsp.button.view_profile"/></strong></td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${totalPages > 1}">
                <c:set var="k" value="0"/>
                <c:forEach begin="1" end="${totalPages}">
                    <c:set var="k" value="${k+1}"/>
                    <c:choose>
                        <c:when test="${param.page == k}"><a
                                href="controller?command=listOfUsers&page=${k}&sort=${sortParam}"><strong><fmt:message
                                key="users_jsp.button.page"/> <c:out
                                value="${k}"/></strong></a></c:when>
                        <c:when test="${param.page != k}"><a
                                href="controller?command=listOfUsers&page=${k}&sort=${sortParam}"><fmt:message
                                key="users_jsp.button.page"/> <c:out
                                value="${k}"/></a></c:when>
                    </c:choose>
                </c:forEach>
            </c:if>
            <br>
            <br>

            <h3><fmt:message key="users_jsp.text.find_by_email"/></h3>

            <c:if test="${not empty notFoundMessage}">
                <h3><span style="color:  rgb(204, 0, 0);"><fmt:message key="users_jsp.message.user_not_found"/></span>
                </h3>
                <c:remove var="notFoundMessage" scope="session"/>
            </c:if>

            <c:if test="${not empty foundUser}">
                <table id="list_users_table">
                    <tr>
                        <th>id</th>
                        <th>Email</th>
                        <th><fmt:message key="users_jsp.text.user_name"/></th>
                        <th><fmt:message key="users_jsp.text.user_surname"/></th>
                        <th><fmt:message key="users_jsp.text.user_status"/></th>
                    </tr>
                    <tr>
                        <td>${foundUser.id}</td>
                        <td>${foundUser.email}</td>
                        <td>${foundUser.name}</td>
                        <td>${foundUser.surname}</td>
                        <td>
                            <c:choose>
                                <c:when test="${foundUser.statusId == 0}">
                                    <fmt:message key="users_jsp.text.unblocked"/>
                                    <c:if test="${foundUser.roleId == 1}">
                                        <br><span style="color: #1B860A">Admin</span>
                                    </c:if>
                                </c:when>
                                <c:when test="${foundUser.statusId == 1}"><span
                                        style="color: rgb(204, 0, 0); "><fmt:message
                                        key="users_jsp.text.blocked"/></span></c:when>
                            </c:choose>
                            <form action="controller" method="get">
                                <input name="sort" type="hidden" value="${sortParam}">
                                <input name="page" type="hidden" value="${param.page}">
                                <input name="command" type="hidden" value="updateUsersStatus"/>
                                <c:choose>
                                    <c:when test="${foundUser.statusId == 0 and foundUser.roleId != 1}">
                                        <button name="status" value="1">
                                            <fmt:message key="users_jsp.button.block"/>
                                        </button>
                                    </c:when>
                                    <c:when test="${foundUser.statusId == 1 and foundUser.roleId != 1}">
                                        <button name="status" value="0">
                                            <fmt:message key="users_jsp.button.unblock"/>
                                        </button>
                                    </c:when>
                                </c:choose>
                            </form>
                        </td>
                        <td><a href="controller?command=viewUserProfilePage&userId=${foundUser.id}"/><strong><fmt:message
                                key="users_jsp.button.view_profile"/></strong></td>
                    </tr>
                </table>
            </c:if>

            <form action="controller?command=findUserByEmail" method="post">
                <input name="sort" type="hidden" value="${sortParam}">
                <input name="page" type="hidden" value="${param.page}">
                <table id="list_search_table">
                    <tr>
                        <td><input required type="email" name="email"
                                <c:choose>
                                    <c:when test="${not empty foundUser}">value="${foundUser.email}"</c:when>
                                    <c:when test="${empty foundUser}">placeholder="<fmt:message
                                            key="users_jsp.placeholder.email_for_search"/>"</c:when>
                                </c:choose>/><br></td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit"><fmt:message key="users_jsp.button.find"/></button>
                        </td>
                    </tr>
                </table>
            </form>

            <c:if test="${not empty successRegMessage}">
                <h3><span style="color: #1B860A"><fmt:message key="users_jsp.message_user_registered"/></span></h3><br>
                <c:remove var="successRegMessage" scope="session"/>
            </c:if>

            <a href="controller?command=registerUserPage"><strong><fmt:message
                    key="users_jsp.button.register_new_user"/></strong></a>

        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>