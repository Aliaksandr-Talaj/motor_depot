<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.add.new.one" var="ADD_NEW"/>
<fmt:message bundle="${loc}" key="local.req1" var="REQUEST_PAGE_1"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">${REQUEST_PAGE_1}</span>
    </div>
</nav>

<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>


<jsp:include page="table-charterers.jsp"/>

<a class="btn btn-secondary" href="/motor_depot/user/dispatcher/create_charterer4req"
   role="button"><c:out value="${ADD_NEW}"/></a>


<jsp:include page="footer.jsp"/>