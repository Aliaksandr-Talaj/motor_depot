<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.charterer-form" var="page_name"/>
<fmt:message bundle="${loc}" key="local.user.name" var="NAME"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="SURNAME"/>
<fmt:message bundle="${loc}" key="local.user.enter.name" var="ENTER_NAME"/>
<fmt:message bundle="${loc}" key="local.user.enter.surname" var="ENTER_SURNAME"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>
<fmt:message bundle="${loc}" key="local.own.address" var="OWN_ADDRESS"/>
<fmt:message bundle="${loc}" key="local.enter.address" var="ENTER_ADDRESS"/>
<fmt:message bundle="${loc}" key="local.country" var="COUNTRY"/>
<fmt:message bundle="${loc}" key="local.enter.country" var="ENTER_COUNTRY"/>
<fmt:message bundle="${loc}" key="local.region" var="REGION"/>
<fmt:message bundle="${loc}" key="local.enter.region" var="ENTER_REGION"/>
<fmt:message bundle="${loc}" key="local.locality" var="LOCALITY"/>
<fmt:message bundle="${loc}" key="local.enter.locality" var="ENTER_LOCALITY"/>
<fmt:message bundle="${loc}" key="local.street" var="STREET"/>
<fmt:message bundle="${loc}" key="local.enter.street" var="ENTER_STREET"/>
<fmt:message bundle="${loc}" key="local.building" var="BUILDING"/>
<fmt:message bundle="${loc}" key="local.enter.building" var="ENTER_BUILDING_NUMBER"/>
<fmt:message bundle="${loc}" key="local.apartment" var="APARTMENT"/>
<fmt:message bundle="${loc}" key="local.enter.apartment" var="ENTER_APARTMENT"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>

<c:set var="ref" value="/motor_depot/user/dispatcher/add-charterer"/>

<c:if test="${for_request eq 1}">
    <c:set var="ref" value="/motor_depot/user/dispatcher/request-form2"/>
</c:if>

<form action="${ref}" method="post">
    <div class="container">
        <br class="row g-3 needs-validation" novalidate>
        <div class="col-md-2">
            <label for="validationCustom01" class="form-label">${NAME}</label>
            <input type="text" name="name" class="form-control" id="validationCustom01" placeholder="${ENTER_NAME}"
                   required>
        </div>

        <div class="col-md-2">
            <label for="validationCustom02" class="form-label">${SURNAME}</label>
            <input type="text" name="surname" class="form-control" id="validationCustom02"
                   PLACEHOLDER="${ENTER_SURNAME}" required>
        </div>

        <div class="col-md-2">
            <label for="validationCustom02" class="form-label">${OWN_ADDRESS}</label>
        </div>

        <div class="col-md-3">
            <label for="validationCustom02" class="form-label">${COUNTRY}</label>
            <input type="text" name="country" class="form-control"
                   PLACEHOLDER="${ENTER_COUNTRY}" required>
        </div>
        <div class="col-md-3">
            <label for="validationCustom02" class="form-label">${REGION}</label>
            <input type="text" name="region" class="form-control"
                   PLACEHOLDER="${ENTER_REGION}" required>
        </div>
        <div class="col-md-3">
            <label for="validationCustom02" class="form-label">${LOCALITY}</label>
            <input type="text" name="locality" class="form-control"
                    PLACEHOLDER="${ENTER_LOCALITY}" required>
        </div>
        <div class="col-md-3">
            <label for="validationCustom02" class="form-label">${STREET}</label>
            <input type="text" name="street" class="form-control"
                    PLACEHOLDER="${ENTER_STREET}" required>
        </div>
        <div class="col-md-3">
            <label for="validationCustom02" class="form-label">${BUILDING}</label>
            <input type="text" name="building" class="form-control"
                   PLACEHOLDER="${ENTER_BUILDING_NUMBER}" required>
        </div>
        <div class="col-md-3">
            <label for="validationCustom02" class="form-label">${APARTMENT}</label>
            <input type="text" name="apartment" class="form-control"
                   PLACEHOLDER="${ENTER_APARTMENT}" required>
        </div>


        <div class="col-12">
            <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
        </div>

    </div>
</form>


<jsp:include page="footer.jsp"/>