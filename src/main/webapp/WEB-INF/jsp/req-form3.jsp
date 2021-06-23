<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="page_name"/>
<fmt:message bundle="${loc}" key="local.add.new.one" var="ADD_NEW"/>
<fmt:message bundle="${loc}" key="local.req3" var="REQUEST_PAGE_3"/>
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

<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">${REQUEST_PAGE_3}</span>
    </div>
</nav>

<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>
<jsp:useBean id="generatingDelivery" class="by.talai.model.Delivery" scope="session" type="by.talai.model.Delivery"/>



<table class="table">
    <thead>
    <tr>
        <th scope="col">${c_country}</th>
        <th scope="col">${c_region}</th>
        <th scope="col">${c_locality}</th>
        <th scope="col">${c_street}</th>
        <th scope="col">${c_building}</th>
        <th scope="col">${c_apartment}</th>
        <th scope="col">${actions}</th>
    </tr>
    </thead>
    <tbody>
    <%--    OWN ADDRESS--%>

    <c:if test="${generatingRequest.charterer.ownAddress ne generatingDelivery.loadingPlace}">
        <tr>
            <td>${generatingRequest.charterer.ownAddress.country}</td>
            <td>${generatingRequest.charterer.ownAddress.region}</td>
            <td>${generatingRequest.charterer.ownAddress.locality}</td>
            <td>${generatingRequest.charterer.ownAddress.street}</td>
            <td>${generatingRequest.charterer.ownAddress.building}</td>
            <td>${generatingRequest.charterer.ownAddress.apartment}</td>
            <td>
                <form method="post" action="/motor_depot/user/dispatcher/address_chosen2">
                    <input type="hidden" name="destinationAddressId"
                           value="${generatingRequest.charterer.ownAddress.id}"/>
                    <button class="btn btn-secondary"

                            role="button" type="submit" value="Save">${CHOOSE_THAT}</button>
                </form>
            </td>
        </tr>
    </c:if>
    <%--USED ADDRESSES--%>
    <c:forEach items="${used_addresses}" var="address">
        <c:if test="${address ne generatingDelivery.loadingPlace}">
            <tr>
                <td>${address.country}</td>
                <td>${address.region}</td>
                <td>${address.locality}</td>
                <td>${address.street}</td>
                <td>${address.building}</td>
                <td>${address.apartment}</td>
                <td>
                    <form method="post" action="/motor_depot/user/dispatcher/address_chosen2">
                        <input type="hidden" value="${address}" name="destinationAddress"/>
                        <button class="btn btn-secondary"
                                role="button" type="submit" value="Save">${CHOOSE_THAT}</button>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>


<a class="btn btn-secondary" href="/motor_depot/user/dispatcher/create_destination_address4req"
   role="button"><c:out value="${ADD_NEW}"/></a>


<jsp:include page="footer.jsp"/>