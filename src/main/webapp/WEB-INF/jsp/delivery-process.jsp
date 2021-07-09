<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request.process" var="PAGE_NAME"/>
<%--<fmt:message bundle="${loc}" key="local.request.process" var="REQUEST_PROCESS"/>--%>
<fmt:message bundle="${loc}" key="local.request.number" var="r_number"/>
<fmt:message bundle="${loc}" key="local.request.filling.date" var="r_filling_date"/>
<fmt:message bundle="${loc}" key="local.request.charterer" var="r_charterer"/>
<fmt:message bundle="${loc}" key="local.request.required.auto.type" var="r_req_auto_type"/>
<fmt:message bundle="${loc}" key="local.request.required.loading.type" var="r_req_loading_type"/>
<fmt:message bundle="${loc}" key="local.request.status" var="r_status"/>
<fmt:message bundle="${loc}" key="local.request.deliveries" var="r_deliveries"/>
<fmt:message bundle="${loc}" key="local.delivery" var="DELIVERY"/>
<fmt:message bundle="${loc}" key="local.actions" var="r_actions"/>
<fmt:message bundle="${loc}" key="local.process" var="r_process"/>
<fmt:message bundle="${loc}" key="local.request.equipment" var="r_req_equipment"/>

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
<fmt:message bundle="${loc}" key="local.choose" var="CHOOSE"/>
<fmt:message bundle="${loc}" key="local.auto.number" var="NUMBER"/>
<fmt:message bundle="${loc}" key="local.auto.brand" var="BRAND"/>
<fmt:message bundle="${loc}" key="local.auto.enter.model" var="MODEL"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTION"/>
<fmt:message bundle="${loc}" key="local.units.fit" var="UNITS_FIT"/>
<fmt:message bundle="${loc}" key="local.loading.percent" var="LOADING_PERCENT"/>
<fmt:message bundle="${loc}" key="local.autos.suitable" var="SUITABLE_AUTOS"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>


<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>


<table class="table">


    <thead>
    <tr>
        <th scope="col"><c:out value="${DELIVERY} #${delivery.id}"/></th>
        <th scope="col">

        </th>
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
    <tr>
    <tr>
        <th scope="col">${SUITABLE_AUTOS}</th>
    </tr>
    <tr>
        <th scope="col">${NUMBER}</th>
        <th scope="col">${BRAND}</th>
        <th scope="col">${MODEL}</th>
        <th scope="col">${UNITS_FIT}</th>
        <th scope="col">${LOADING_PERCENT}</th>
        <th scope="col">${ACTION}</th>

    </tr>
    <c:forEach items="${suitableAutomobiles}" var="automobileLoadingDto">
        <c:if test="${automobileLoadingDto.loadingPercent ne 0}">
            <tr>
                <td>${automobileLoadingDto.automobile.id}</td>
                <td>${automobileLoadingDto.automobile.brand}</td>
                <td>${automobileLoadingDto.automobile.model}</td>
                <td>
                    <c:forEach items="${delivery.cargoList}" var="cargo">
                        <c:out value="${cargo.name} ${cargo.unit.type} - ${automobileLoadingDto.fitQuantityOfCargoMap[cargo]}"/><br/>
                    </c:forEach>

                </td>
                <td><c:out value="loading percent: "/><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                        value="${automobileLoadingDto.loadingPercent}"/></td>

                <td>
                    <form method="post" action="/motor_depot/user/dispatcher/create-ride">
                        <input type="hidden" value="${delivery.id}" name="deliveryId"/>
                        <input type="hidden" value="${automobileLoadingDto.automobile.id}" name="automobileId"/>
                        <button class="btn btn-secondary"
                                role="button" type="submit" value="Select">${CHOOSE}</button>
                    </form>
                </td>
            </tr>
        </c:if>
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

</table>


<jsp:include page="footer.jsp"/>