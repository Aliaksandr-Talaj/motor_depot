<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.address-form" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>
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
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<c:choose>
    <c:when test="${for_request eq 3}">
        <c:set var="ref" value="/motor_depot/user/dispatcher/request-form3"/>
    </c:when>
    <c:when test="${for_request eq 4}">
        <c:set var="ref" value="/motor_depot/user/dispatcher/request-form4"/>
    </c:when>
    <c:otherwise>
        <c:set var="ref" value="/motor_depot/user/dispatcher/add-address"/>
    </c:otherwise>
</c:choose>


<form action="${ref}" method="post">
    <div class="container">
        <br class="row g-3 needs-validation" novalidate>

        <div class="col-md-3">
            <label class="form-label">${COUNTRY}</label>
            <label>
                <input type="text" name="country" class="form-control"
                       PLACEHOLDER="${ENTER_COUNTRY}" required>
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${REGION}</label>
            <label>
                <input type="text" name="region" class="form-control"
                       PLACEHOLDER="${ENTER_REGION}" required>
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${LOCALITY}</label>
            <label>
                <input type="text" name="locality" class="form-control"
                       PLACEHOLDER="${ENTER_LOCALITY}" required>
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${STREET}</label>
            <label>
                <input type="text" name="street" class="form-control"
                       PLACEHOLDER="${ENTER_STREET}" required>
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${BUILDING}</label>
            <label>
                <input type="text" name="building" class="form-control"
                       PLACEHOLDER="${ENTER_BUILDING_NUMBER}" required>
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${APARTMENT}</label>
            <label>
                <input type="text" name="apartment" class="form-control"
                       PLACEHOLDER="${ENTER_APARTMENT}" required>
            </label>
        </div>
        <c:choose>
            <c:when test="${for_request eq 3}">
                <input type="hidden" name="goToReq3Form" value="1"/>
            </c:when>
            <c:when test="${for_request eq 4}">
                <input type="hidden" name="goToReq3Form" value="2"/>
            </c:when>
            <c:otherwise><input type="hidden" name="goToReq3Form" value="0"/></c:otherwise>
        </c:choose>
        <div class="col-12">
            <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
        </div>

    </div>
</form>


<jsp:include page="footer.jsp"/>