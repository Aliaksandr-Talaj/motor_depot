<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.rides.mp" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.request.number" var="r_number"/>
<fmt:message bundle="${loc}" key="local.loading.date" var="r_date"/>
<fmt:message bundle="${loc}" key="local.request" var="r_request"/>
<fmt:message bundle="${loc}" key="local.dispatcher" var="r_dispatcher"/>
<fmt:message bundle="${loc}" key="local.auto" var="r_auto"/>
<fmt:message bundle="${loc}" key="local.loading.place" var="r_loading_place"/>
<fmt:message bundle="${loc}" key="local.loading.date" var="r_loading_date"/>
<fmt:message bundle="${loc}" key="local.destination" var="r_destination"/>
<fmt:message bundle="${loc}" key="local.term" var="r_term"/>
<fmt:message bundle="${loc}" key="local.request.status" var="r_status"/>
<fmt:message bundle="${loc}" key="local.cargo" var="r_cargo"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<table class="table">
    <thead>
    <tr>
        <th scope="col">${r_number}</th>
        <th scope="col">${r_date}</th>
        <th scope="col">${r_request}</th>
        <th scope="col">${r_dispatcher}</th>
        <th scope="col">${r_auto}</th>
        <th scope="col">${r_loading_place}</th>
        <th scope="col">${r_loading_date}</th>
        <th scope="col">${r_destination}</th>
        <th scope="col">${r_term}</th>
        <th scope="col">${r_cargo}</th>
        <th scope="col">${r_status}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${rides}" var="ride">
        <tr>
            <td>${ride.id}</td>
            <td>${ride.date}</td>
            <td>
                <a href="motor-depot/user/request?id=${ride.request.id}">${ride.request.id}</a>
            </td>
            <td>${ride.dispatcher.id}<br/>
                    ${ride.dispatcher.name}
                <c:out value=" "/>
                    ${ride.dispatcher.surname}
            </td>
            <td>
                <a href="motor-depot/user/driver/auto?id=${ride.automobile.id}">${ride.automobile.id} ${ride.automobile.brand} ${ride.automobile.model}</a>
            </td>
            <td>${ride.loadingPlace.country}<br/>
                    ${ride.loadingPlace.region}<br/>
                    ${ride.loadingPlace.locality}<br/>
                    ${ride.loadingPlace.street}
                <c:out value=" "/>
                    ${ride.loadingPlace.building} <c:out value="-"/>
                    ${ride.loadingPlace.apartment}
            </td>
            <td>${ride.loadingDate}</td>
            <td>${ride.destination.country}<br/>
                    ${ride.destination.region}<br/>
                    ${ride.destination.locality}<br/>
                    ${ride.destination.street}
                <c:out value=" "/>
                    ${ride.destination.building} <c:out value="-"/>
                    ${ride.destination.apartment}
            </td>
            <td>${ride.term}</td>

            <td><c:forEach items="${ride.cargoList}" var="cargo">

                <a href="motor-depot/user/cargo?id=${cargo.id}"> #${cargo.id} ${cargo.name}</a><br/>

            </c:forEach>
            </td>

            <td>${ride.executionStatus.status}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>