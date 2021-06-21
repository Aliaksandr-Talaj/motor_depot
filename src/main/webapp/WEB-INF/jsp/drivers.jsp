<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.drivers" var="page_name"/>



<!--Page name -->
<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
  </div>
</nav>



<%--<table class="table">--%>
<%--  <thead>--%>
<%--  <tr>--%>
<%--    <th scope="col">${driver_name}</th>--%>
<%--    <th scope="col">${driver_surname}</th>--%>
<%--    <th scope="col">${driver_login}</th>--%>
<%--    <th scope="col">${driver_role}</th>--%>
<%--    <th scope="col">${driver_current_attachment}</th>--%>
<%--    <th scope="col">${actions}</th>--%>
<%--  </tr>--%>
<%--  </thead>--%>
<%--  <tbody>--%>
<%--  <c:forEach items="${usersDto.users}" var="user">--%>
<%--    <tr>--%>
<%--      <td>${user.name}</td>--%>
<%--      <td>${user.surname}</td>--%>
<%--      <td>${user.login}</td>--%>
<%--      <td>${user.role.name}</td>--%>
<%--      <td>${user.status.status}</td>--%>
<%--      <td>--%>
<%--        <c:choose>--%>
<%--          <c:when test="${user.status.status eq 'active'}">--%>
<%--            <a class="btn btn-primary"  href="/motor_depot/admin/users/c_status?id=<c:out value='${user.id}'/>&statusId=2" role="button"><c:out value="${u_block}"/></a>--%>
<%--          </c:when>--%>
<%--          <c:otherwise>--%>
<%--            <a class="btn btn-secondary"  href="/motor_depot/admin/users/c_status?id=<c:out value='${user.id}'/>&statusId=1" role="button"><c:out value="${u_unblock}"/></a>--%>
<%--          </c:otherwise>--%>
<%--        </c:choose>--%>
<%--      </td>--%>
<%--    </tr>--%>
<%--  </c:forEach>--%>
<%--  </tbody>--%>
<%--</table>--%>









<jsp:include page="footer.jsp"/>