<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request.charterer" var="page_name"/>
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

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>


<table class="table">
    <tbody>
    <tr>
        <td scope="col">${c_number}</td>
        <td scope="col">${charterer.id}</td>
    </tr>


    <tr>
        <td>${c_named}</td>
        <td>${charterer.name}</td>
    </tr>

    <tr>
        <td>${c_surname}</td>
        <td>${charterer.surname}</td>
    </tr>

    <tr>
        <td>${o_address}</td>
    </tr>

    <tr>
        <td>${c_country}</td>
        <td>${charterer.ownAddress.country}</td>
    </tr>

    <tr>
        <td>${c_region}</td>
        <td>${charterer.ownAddress.region}</td>
    </tr>

    <tr>
        <td>${c_locality}</td>
        <td>${charterer.ownAddress.locality}</td>
    </tr>

    <tr>
        <td>${c_street}</td>
        <td>${charterer.ownAddress.street}</td>
    </tr>

    <tr>
        <td>${c_building}</td>
        <td>${charterer.ownAddress.building}</td>
    </tr>

    <tr>
        <td>${c_apartment}</td>
        <td>${charterer.ownAddress.apartment}</td>
    </tr>

    </tbody>
</table>


<jsp:include page="footer.jsp"/>