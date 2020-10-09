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

            <h3>List of all users</h3>
            <strong>Sort by:</strong>
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
            <a href="controller?command=listOfUsers&page=1&sort=nameAsc">
                <c:choose>
                    <c:when test="${param.sort == 'nameAsc'}">
                        <strong>Name (a-z)</strong>
                    </c:when>
                    <c:when test="${param.sort != 'nameAsc'}">
                        Name (a-z)
                    </c:when>
                </c:choose>
            </a>
            <a href="controller?command=listOfUsers&page=1&sort=nameDesc">
                <c:choose>
                    <c:when test="${param.sort == 'nameDesc'}">
                        <strong>Name (z-a)</strong>
                    </c:when>
                    <c:when test="${param.sort != 'nameDesc'}">
                        Name (z-a)
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
                        <strong>Ban status</strong>
                    </c:when>
                    <c:when test="${param.sort != 'banStatus'}">
                        Ban status
                    </c:when>
                </c:choose>
            </a>

            <table id="list_users_table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>
                    <th>Region</th>
                    <th>City</th>
                    <th>Educational institution</th>
                    <th>Status</th>
                </tr>
                </thead>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.patronymic}</td>
                        <td>${user.region}</td>
                        <td>${user.city}</td>
                        <td>${user.institutionName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.statusId == 0}">
                                    Unblocked
                                    <c:if test="${user.roleId == 1}">
                                        <br><span style="color: #1B860A">Admin</span>
                                    </c:if>
                                    <form action="controller" method="get">
                                        <input name="command" type="hidden" value="updateUsersStatus"/>
                                        <input name="userId" type="hidden" value="${user.id}">
                                        <c:choose>
                                            <c:when test="${user.statusId == 0 and user.roleId != 1}">
                                                <button name="status" value="1">
                                                    Block
                                                </button>
                                            </c:when>
                                            <c:when test="${user.statusId == 1 and user.roleId != 1}">
                                                <button name="status" value="0">
                                                    Unblock
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </c:when>
                                <c:when test="${user.statusId == 1}">
                                    <span style="color: rgb(204, 0, 0); ">Blocked</span>
                                    <form action="controller" method="get">
                                        <input name="command" type="hidden" value="updateUsersStatus"/>
                                        <input name="userId" type="hidden" value="${user.id}">
                                        <c:choose>
                                            <c:when test="${user.statusId == 0 and user.roleId != 1}">
                                                <button name="status" value="1">
                                                    Block
                                                </button>
                                            </c:when>
                                            <c:when test="${user.statusId == 1 and user.roleId != 1}">
                                                <button name="status" value="0">
                                                    Unblock
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${totalPages > 1}">
                <c:set var="k" value="0"/>
                <c:forEach begin="1" end="${totalPages}">
                    <c:set var="k" value="${k+1}"/>
                    <c:choose>
                        <c:when test="${param.page == k}"><a
                                href="controller?command=listOfUsers&page=${k}&sort=${sortParam}"><strong>Page <c:out
                                value="${k}"/></strong></a></c:when>
                        <c:when test="${param.page != k}"><a
                                href="controller?command=listOfUsers&page=${k}&sort=${sortParam}">Page <c:out
                                value="${k}"/></a></c:when>
                    </c:choose>
                </c:forEach>
            </c:if>
            <br>
            <br>

            <h3>Find by email</h3>

            <c:if test="${not empty foundUser}">
                <table id="list_users_table">
                    <tr>
                        <th>id</th>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Patronymic</th>
                        <th>Region</th>
                        <th>City</th>
                        <th>Educational institution</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <td>${foundUser.id}</td>
                        <td>${foundUser.email}</td>
                        <td>${foundUser.name}</td>
                        <td>${foundUser.surname}</td>
                        <td>${foundUser.patronymic}</td>
                        <td>${foundUser.region}</td>
                        <td>${foundUser.city}</td>
                        <td>${foundUser.institutionName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${foundUser.statusId == 0}">
                                    Unblocked
                                    <c:if test="${foundUser.roleId == 1}">
                                        <br><span style="color: #1B860A">Admin</span>
                                    </c:if>
                                </c:when>
                                <c:when test="${foundUser.statusId == 1}"><span
                                        style="color: rgb(204, 0, 0); ">Blocked</span></c:when>
                            </c:choose>
                            <form action="controller" method="get">
                                <input name="command" type="hidden" value="updateUsersStatus"/>
                                <c:choose>
                                    <c:when test="${foundUser.statusId == 0 and foundUser.roleId != 1}">
                                        <button name="status" value="1">
                                            Block
                                        </button>
                                    </c:when>
                                    <c:when test="${foundUser.statusId == 1 and foundUser.roleId != 1}">
                                        <button name="status" value="0">
                                            Unblock
                                        </button>
                                    </c:when>
                                </c:choose>
                            </form>
                        </td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${not empty requestScope.notFoundMessage}">
                <span style="color: rgb(204, 0, 0); ">There is no such user</span>
            </c:if>

            <form action="controller?command=findUserByEmail" method="post">
                <table id="list_search_table">
                    <tr>
                        <td><input required type="email" name="email"
                                <c:choose>
                                    <c:when test="${not empty foundUser}">value="${foundUser.email}"</c:when>
                                    <c:when test="${empty foundUser}">placeholder="Email for search"</c:when>
                                </c:choose>/><br></td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit">Find</button>
                        </td>
                    </tr>
                </table>
            </form>

            <a href="controller?command=registerUserPage"><strong>Register new user</strong></a>


        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>