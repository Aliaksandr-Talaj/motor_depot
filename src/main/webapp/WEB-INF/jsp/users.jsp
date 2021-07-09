<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.users" var="PAGE_NAME"/>

<fmt:message bundle="${loc}" key="local.user.name" var="user_name"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="user_surname"/>
<fmt:message bundle="${loc}" key="local.user.login" var="user_login"/>
<fmt:message bundle="${loc}" key="local.user.role" var="user_role"/>
<fmt:message bundle="${loc}" key="local.user.status" var="user_status"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.user.block" var="u_block"/>
<fmt:message bundle="${loc}" key="local.user.unblock" var="u_unblock"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
  </div>
</nav>

<!--CONTENT-->
<table class="table">
      <thead>
        <tr>
          <th scope="col">${user_name}</th>
          <th scope="col">${user_surname}</th>
          <th scope="col">${user_login}</th>
          <th scope="col">${user_role}</th>
          <th scope="col">${user_status}</th>
          <th scope="col">${ACTIONS}</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${usersDto.users}" var="driver">
        <tr>
          <td>${driver.name}</td>
          <td>${driver.surname}</td>
          <td>${driver.login}</td>
          <td>${driver.role.name}</td>
          <td>${driver.status.status}</td>
          <td>
            <c:choose>
                <c:when test="${driver.status.status eq 'active'}">
                    <a class="btn btn-primary" href="/motor_depot/admin/users/c_status?id=<c:out value='${driver.id}'/>&statusId=2" role="button"><c:out value="${u_block}"/></a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-secondary" href="/motor_depot/admin/users/c_status?id=<c:out value='${driver.id}'/>&statusId=1" role="button"><c:out value="${u_unblock}"/></a>
                </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>







<jsp:include page="footer.jsp"/>