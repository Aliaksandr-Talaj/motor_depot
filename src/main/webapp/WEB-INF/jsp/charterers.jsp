<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.charterers" var="page_name"/>

<fmt:message bundle="${loc}" key="local.charterers" var="page_name"/>
<fmt:message bundle="${loc}" key="local.user.name" var="c_name"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="c_surname"/>
<fmt:message bundle="${loc}" key="local.own.address" var="o_address"/>
<fmt:message bundle="${loc}" key="local.auto.number" var="c_number"/>
<fmt:message bundle="${loc}" key="local.country" var="c_country"/>
<fmt:message bundle="${loc}" key="local.region" var="c_region"/>
<fmt:message bundle="${loc}" key="local.locality" var="c_locality"/>
<fmt:message bundle="${loc}" key="local.street" var="c_street"/>
<fmt:message bundle="${loc}" key="local.building" var="c_building"/>
<fmt:message bundle="${loc}" key="local.apartment" var="c_apartment"/>
<fmt:message bundle="${loc}" key="local.actions" var="actions"/>
<fmt:message bundle="${loc}" key="local.choose.that" var="CHOOSE_THAT"/>

<!--Page name -->

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>


<jsp:include page="table-charterers.jsp"/>

<jsp:include page="footer.jsp"/>