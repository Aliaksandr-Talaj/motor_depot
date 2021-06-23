<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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


<table class="table">
    <thead>
    <tr>

        <th scope="col">${c_number}</th>
        <th scope="col">${c_name}</th>
        <th scope="col">${c_surname}</th>
        <th scope="col">${c_country}</th>
        <th scope="col">${c_region}</th>
        <th scope="col">${c_locality}</th>
        <th scope="col">${c_street}</th>
        <th scope="col">${c_building}</th>
        <th scope="col">${c_apartment}</th>

        <c:if test="${new_request eq 1}">
            <th scope="col">${actions}</th>
        </c:if>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${charterers}" var="address">
        <tr>
            <td>${address.id}</td>
            <td>${address.name}</td>
            <td>${address.surname}</td>
            <td>${address.ownAddress.country}</td>
            <td>${address.ownAddress.region}</td>
            <td>${address.ownAddress.locality}</td>
            <td>${address.ownAddress.street}</td>
            <td>${address.ownAddress.building}</td>
            <td>${address.ownAddress.apartment}</td>

            <c:if test="${new_request eq 1}">

                <td>
                    <form method="post" action="/motor_depot/user/dispatcher/charterer_chosen">
                        <input type="hidden" value="${address.id}" name="chartererId"/>
                        <button class="btn btn-secondary"
                                role="button" type="submit" value="Save">${CHOOSE_THAT}</button>
                    </form>
                </td>

            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>


