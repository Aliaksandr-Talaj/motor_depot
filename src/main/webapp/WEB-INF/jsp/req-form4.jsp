<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.add.new.one" var="ADD_NEW"/>
<fmt:message bundle="${loc}" key="local.req4" var="REQUEST_PAGE_4"/>
<fmt:message bundle="${loc}" key="local.auto.number" var="c_number"/>
<fmt:message bundle="${loc}" key="local.country" var="c_country"/>
<fmt:message bundle="${loc}" key="local.region" var="c_region"/>
<fmt:message bundle="${loc}" key="local.locality" var="c_locality"/>
<fmt:message bundle="${loc}" key="local.street" var="c_street"/>
<fmt:message bundle="${loc}" key="local.building" var="c_building"/>
<fmt:message bundle="${loc}" key="local.apartment" var="c_apartment"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.choose.that" var="CHOOSE_THAT"/>
<fmt:message bundle="${loc}" key="local.goes.from" var="GOES_FROM"/>
<fmt:message bundle="${loc}" key="local.goes.to" var="GOES_TO"/>
<fmt:message bundle="${loc}" key="local.invalid.date" var="INVALID_DATE"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">${REQUEST_PAGE_4}</span>
    </div>
</nav>
<c:if test="${!(empty invalidInput)}">
    <nav class="navbar navbar-light bg-warning">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1">${INVALID_DATE}</span>
        </div>
    </nav>
</c:if>
<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>
<jsp:useBean id="generatingDelivery" class="by.talai.model.Delivery" scope="session" type="by.talai.model.Delivery"/>

<form method="post" action="/motor_depot/user/dispatcher/dates_chosen">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">
                ${GOES_FROM}<br/>
                <c:out value="${c_country}: "/>
                ${generatingDelivery.loadingPlace.country}
                <br/>
                <c:out value="${c_region}: "/>
                ${generatingDelivery.loadingPlace.region}
                <br/>
                <c:out value="${c_locality}: "/>
                ${generatingDelivery.loadingPlace.locality}
                <br/>
                <c:out value="${c_street}: "/>
                ${generatingDelivery.loadingPlace.street}
                <br/>
                <c:out value="${c_building}: "/>
                ${generatingDelivery.loadingPlace.building}
                <br/>
                <c:out value="${c_apartment}: "/>
                ${generatingDelivery.loadingPlace.apartment}
                <br/>
            </th>

            <th scope="col">
                ${GOES_TO}<br/>
                <c:out value="${c_country}: "/>
                ${generatingDelivery.destination.country}
                <br/>
                <c:out value="${c_region}: "/>
                ${generatingDelivery.destination.region}
                <br/>
                <c:out value="${c_locality}: "/>
                ${generatingDelivery.destination.locality}
                <br/>
                <c:out value="${c_street}: "/>
                ${generatingDelivery.destination.street}
                <br/>
                <c:out value="${c_building}: "/>
                ${generatingDelivery.destination.building}
                <br/>
                <c:out value="${c_apartment}: "/>
                ${generatingDelivery.destination.apartment}
                <br/>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>

            <td>
                <input type="date" name="loadingDate"
                       placeholder="loading date"/>
            </td>
            <td>
                <input type="date" name="unloadingDate"
                       placeholder="unloading date"/>
            </td>
        </tr>
        </tbody>
    </table>
    <button class="btn btn-secondary"
            role="button" type="submit" value="Save">${CHOOSE_THAT}</button>
</form>


<jsp:include page="footer.jsp"/>