<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="page_name"/>
<fmt:message bundle="${loc}" key="local.req6" var="REQUEST_PAGE_6"/>
<fmt:message bundle="${loc}" key="local.cargo.name" var="CARGO_NAME"/>
<fmt:message bundle="${loc}" key="local.unit.type" var="UNIT_TYPE"/>
<fmt:message bundle="${loc}" key="local.unit.length" var="UNIT_LENGTH"/>
<fmt:message bundle="${loc}" key="local.unit.width" var="UNIT_WIDTH"/>
<fmt:message bundle="${loc}" key="local.unit.height" var="UNIT_HEIGHT"/>
<fmt:message bundle="${loc}" key="local.unit.weight" var="UNIT_WEIGHT"/>
<fmt:message bundle="${loc}" key="local.auto.enter.carrying" var="W_UNITS"/>
<fmt:message bundle="${loc}" key="local.unit.quantity" var="UNIT_QUANTITY"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>
<fmt:message bundle="${loc}" key="local.continue" var="CONTINUE"/>
<fmt:message bundle="${loc}" key="local.add.one.more" var="ADD_MORE"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${REQUEST_PAGE_6}"/></span>
    </div>
</nav>

<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>
<jsp:useBean id="generatingDelivery" class="by.talai.model.Delivery" scope="session" type="by.talai.model.Delivery"/>
<jsp:useBean id="generatingCargo" class="by.talai.model.Cargo" scope="session" type="by.talai.model.Cargo"/>
<jsp:useBean id="generatingUnit" class="by.talai.model.Unit" scope="session" type="by.talai.model.Unit"/>

<table class="table">
    <thead>
    <tr>
        <th scope="col">${CARGO_NAME}</th>
        <th scope="col">${UNIT_TYPE}</th>
        <th scope="col">${UNIT_LENGTH}</th>
        <th scope="col">${UNIT_WIDTH}</th>
        <th scope="col">${UNIT_HEIGHT}</th>
        <th scope="col">${UNIT_WEIGHT}</th>
        <th scope="col">${UNIT_QUANTITY}</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${generatingDelivery.cargoList}" var="cargo">
        <tr>
            <td>${cargo.name}</td>
            <td>${cargo.unit.type}</td>
            <td>${cargo.unit.length}</td>
            <td>${cargo.unit.width}</td>
            <td>${cargo.unit.height}</td>
            <td>${cargo.unit.width}</td>
            <td>${cargo.quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="d-grid gap-2 d-md-block">
    <form method="post" action="/motor_depot/user/dispatcher/save_cargo">
        <input type="hidden" value="true" name="addMoreCargos"/>

        <button class="btn btn-secondary"
                role="button" type="submit" value="Select">${ADD_MORE}</button>
    </form>
<br/>

    <form method="post" action="/motor_depot/user/dispatcher/save_cargo">
        <input type="hidden" value="false" name="addMoreCargos"/>

        <button class="btn btn-secondary btn-lg"
                role="button" type="submit" value="Select">${CONTINUE}</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>