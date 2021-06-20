<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.users" var="page_name"/>
<!--<fmt:message bundle="${loc}" key="local.request.number" var="r_number"/>
<fmt:message bundle="${loc}" key="local.request.filling.date" var="r_filling_date"/>
<fmt:message bundle="${loc}" key="local.request.charterer" var="r_charterer"/>
<fmt:message bundle="${loc}" key="local.request.required.auto.type" var="r_req_auto_type"/>
<fmt:message bundle="${loc}" key="local.request.required.loading.type" var="r_req_loading_type"/>
<fmt:message bundle="${loc}" key="local.request.status" var="r_status"/>
<fmt:message bundle="${loc}" key="local.request.deliveries" var="r_deliveries"/>-->

<!--Page name -->
<nav class="navbar navbar-light bg-light">
  <div class="container-fluid">
    <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
  </div>
</nav>

<table class="table">
      <thead>
        <tr>
          <th scope="col">${r_number}</th>
          <th scope="col">${r_filling_date}</th>
          <th scope="col">${r_charterer}</th>
          <th scope="col">${r_req_auto_type}</th>
          <th scope="col">${r_req_loading_type}</th>
          <th scope="col">${r_deliveries}</th>
          <th scope="col">${r_status}</th>
        </tr>
      </thead>
      <tbody>
      <!--<c:forEach items="${requestDto.users}" var="user">
        <tr>
          <td>${user.name}</td>
          <td>${user.surname}</td>
          <td>${user.login}</td>
          <td>${user.role.name}</td>
          <td></td>
          <td></td>
           <td>${user.status.status}</td>
        </tr>
      </c:forEach>-->
      </tbody>
    </table>








<jsp:include page="footer.jsp"/>