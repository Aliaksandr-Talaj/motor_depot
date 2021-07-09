<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.req7" var="REQUEST_PAGE_7"/>
<fmt:message bundle="${loc}" key="local.cargo.name" var="CARGO_NAME"/>
<fmt:message bundle="${loc}" key="local.unit.type" var="UNIT_TYPE"/>
<fmt:message bundle="${loc}" key="local.unit.length" var="UNIT_LENGTH"/>
<fmt:message bundle="${loc}" key="local.unit.width" var="UNIT_WIDTH"/>
<fmt:message bundle="${loc}" key="local.unit.height" var="UNIT_HEIGHT"/>
<fmt:message bundle="${loc}" key="local.unit.weight" var="UNIT_WEIGHT"/>
<fmt:message bundle="${loc}" key="local.auto.enter.carrying" var="W_UNITS"/>
<fmt:message bundle="${loc}" key="local.quantity" var="UNIT_QUANTITY"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>
<fmt:message bundle="${loc}" key="local.continue" var="CONTINUE"/>
<fmt:message bundle="${loc}" key="local.add.one.more.delivery" var="ADD_MORE"/>
<fmt:message bundle="${loc}" key="local.charterer" var="CHARTERER"/>
<fmt:message bundle="${loc}" key="local.delivery" var="DELIVERY"/>
<fmt:message bundle="${loc}" key="local.auto.number" var="c_number"/>
<fmt:message bundle="${loc}" key="local.country" var="c_country"/>
<fmt:message bundle="${loc}" key="local.region" var="c_region"/>
<fmt:message bundle="${loc}" key="local.locality" var="c_locality"/>
<fmt:message bundle="${loc}" key="local.street" var="c_street"/>
<fmt:message bundle="${loc}" key="local.building" var="c_building"/>
<fmt:message bundle="${loc}" key="local.apartment" var="c_apartment"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.goes.from" var="GOES_FROM"/>
<fmt:message bundle="${loc}" key="local.goes.to" var="GOES_TO"/>
<fmt:message bundle="${loc}" key="local.loading.date" var="L_DATE"/>
<fmt:message bundle="${loc}" key="local.term" var="TERM"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${REQUEST_PAGE_7}"/></span>
    </div>
</nav>

<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>
<jsp:useBean id="generatingDelivery" class="by.talai.model.Delivery" scope="session" type="by.talai.model.Delivery"/>
<jsp:useBean id="generatingCargo" class="by.talai.model.Cargo" scope="session" type="by.talai.model.Cargo"/>
<jsp:useBean id="generatingUnit" class="by.talai.model.Unit" scope="session" type="by.talai.model.Unit"/>


<table class="table">
    <thead>
    <tr>
        <th scope="col"><c:out value="${CHARTERER}:"/><br/>
            <c:out value="${generatingRequest.charterer.name} ${generatingRequest.charterer.surname}"/>
        </th>
    </tr>
    </thead>

    <c:forEach items="${generatingRequest.deliveryList}" var="delivery">
        <thead>
        <tr>
            <th scope="col"><c:out value="${DELIVERY} #${delivery.id}"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="col">
                    ${GOES_FROM}<br/>
                <c:out value="${c_country}: "/>
                    ${delivery.loadingPlace.country}
                <br/>
                <c:out value="${c_region}: "/>
                    ${delivery.loadingPlace.region}
                <br/>
                <c:out value="${c_locality}: "/>
                    ${delivery.loadingPlace.locality}
                <br/>
                <c:out value="${c_street}: "/>
                    ${delivery.loadingPlace.street}
                <br/>
                <c:out value="${c_building}: "/>
                    ${delivery.loadingPlace.building}
                <br/>
                <c:out value="${c_apartment}: "/>
                    ${delivery.loadingPlace.apartment}
                <br/>
                <br/>
                <c:out value="${L_DATE}: "/>
                    ${delivery.loadingDate}
                <br/>
            </th>

            <th scope="col">
                    ${GOES_TO}<br/>
                <c:out value="${c_country}: "/>
                    ${delivery.destination.country}
                <br/>
                <c:out value="${c_region}: "/>
                    ${delivery.destination.region}
                <br/>
                <c:out value="${c_locality}: "/>
                    ${delivery.destination.locality}
                <br/>
                <c:out value="${c_street}: "/>
                    ${delivery.destination.street}
                <br/>
                <c:out value="${c_building}: "/>
                    ${delivery.destination.building}
                <br/>
                <c:out value="${c_apartment}: "/>
                    ${delivery.destination.apartment}
                <br/>
                <br/>
                <c:out value="${TERM}: "/>
                    ${delivery.term}
                <br/>
            </th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </tbody>


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
        <c:forEach items="${delivery.cargoList}" var="cargo">
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
        <tr>
            <th scope="row"></th>
            <th scope="row"></th>
            <th scope="row"></th>
            <th scope="row"></th>
            <th scope="row"></th>
            <th scope="row"></th>
        </tr>
    </c:forEach>
</table>

<div class="d-grid gap-2 d-md-block">
    <form method="post" action="/motor_depot/user/dispatcher/save_delivery">
        <input type="hidden" value="true" name="addMoreDeliveries"/>

        <button class="btn btn-secondary"
                role="button" type="submit" value="Select">${ADD_MORE}</button>
    </form>
    <br/>

    <form method="post" action="/motor_depot/user/dispatcher/save_delivery">
        <input type="hidden" value="false" name="addMoreDeliveries"/>

        <button class="btn btn-secondary btn-lg"
                role="button" type="submit" value="Select">${CONTINUE}</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>