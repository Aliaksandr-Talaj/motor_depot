<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.req5" var="REQUEST_PAGE_5"/>
<fmt:message bundle="${loc}" key="local.cargo.name" var="CARGO_NAME"/>
<fmt:message bundle="${loc}" key="local.cargo.name.description" var="CARGO_NAME_DESC"/>
<fmt:message bundle="${loc}" key="local.unit.type" var="UNIT_TYPE"/>
<fmt:message bundle="${loc}" key="local.unit.type.description" var="UNIT_TYPE_DESC"/>
<fmt:message bundle="${loc}" key="local.unit.length" var="UNIT_LENGTH"/>
<fmt:message bundle="${loc}" key="local.unit.width" var="UNIT_WIDTH"/>
<fmt:message bundle="${loc}" key="local.unit.height" var="UNIT_HEIGHT"/>
<fmt:message bundle="${loc}" key="local.auto.enter.platform.length" var="L_UNITS"/>
<fmt:message bundle="${loc}" key="local.unit.weight" var="UNIT_WEIGHT"/>
<fmt:message bundle="${loc}" key="local.auto.enter.carrying" var="W_UNITS"/>
<fmt:message bundle="${loc}" key="local.unit.quantity" var="UNIT_QUANTITY"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">${REQUEST_PAGE_5}</span>
    </div>
</nav>

<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>
<jsp:useBean id="generatingDelivery" class="by.talai.model.Delivery" scope="session" type="by.talai.model.Delivery"/>
<jsp:useBean id="generatingCargo" class="by.talai.model.Cargo" scope="session" type="by.talai.model.Cargo"/>
<jsp:useBean id="generatingUnit" class="by.talai.model.Unit" scope="session" type="by.talai.model.Unit"/>



<c:set var="ref" value="/motor_depot/user/dispatcher/add_cargo"/>



<form action="${ref}" method="post">
    <div class="container">
        <br class="row g-3 needs-validation" novalidate>

        <div class="col-md-4">
            <label class="form-label">${CARGO_NAME}</label>
            <label>
                <input type="text" name="cargoName" class="form-control"
                       PLACEHOLDER="${CARGO_NAME_DESC}" required>
            </label>
        </div>
        <div class="col-md-4">
            <label class="form-label">${UNIT_TYPE}</label>
            <label>
                <input type="text" name="unitType" class="form-control"
                       PLACEHOLDER="${UNIT_TYPE_DESC}" required>
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${UNIT_LENGTH}</label>
            <label>
                <input type="number" name="unitLength" class="form-control"
                       PLACEHOLDER="${L_UNITS}" required maxlength="6">
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${UNIT_WIDTH}</label>
            <label>
                <input type="number" name="unitWidth" class="form-control"
                       PLACEHOLDER="${L_UNITS}" required maxlength="6">
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${UNIT_HEIGHT}</label>
            <label>
                <input type="number" name="unitHeight" class="form-control"
                       PLACEHOLDER="${L_UNITS}" required step="any" maxlength="6">
            </label>
        </div>
        <div class="col-md-3">
            <label class="form-label">${UNIT_WEIGHT}</label>
            <label>
                <input type="number" name="unitWeight" class="form-control"
                       PLACEHOLDER="${W_UNITS}" required step="any" maxlength="6">
            </label>
        </div>


        <div class="col-md-3">
            <label class="form-label">${UNIT_QUANTITY}</label>
            <label>
                <input type="number" name="quantity" class="form-control"
                        required step="any" maxlength="6">
            </label>
        </div>

        <div class="col-12">
            <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
        </div>

    </div>
</form>


<jsp:include page="footer.jsp"/>