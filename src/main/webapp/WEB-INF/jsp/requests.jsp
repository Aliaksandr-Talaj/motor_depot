<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.requests.mp" var="page_name"/>
<fmt:message bundle="${loc}" key="local.request.number" var="r_number"/>
<fmt:message bundle="${loc}" key="local.request.filling.date" var="r_filling_date"/>
<fmt:message bundle="${loc}" key="local.request.charterer" var="r_charterer"/>
<fmt:message bundle="${loc}" key="local.request.required.auto.type" var="r_req_auto_type"/>
<fmt:message bundle="${loc}" key="local.request.required.loading.type" var="r_req_loading_type"/>
<fmt:message bundle="${loc}" key="local.request.status" var="r_status"/>
<fmt:message bundle="${loc}" key="local.request.deliveries" var="r_deliveries"/>
<fmt:message bundle="${loc}" key="local.delivery" var="DELIVERY"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>

<table class="table">
    <thead>
    <tr>
        <th scope="col">${r_number}</th>
        <th scope="col">${r_filling_date}</th>
        <th scope="col">${r_charterer}</th>
        <th scope="col">${r_req_auto_type}</th>
        <th scope="col">${r_req_loading_type}</th>
        <th scope="col">${r_deliveries}</th>
        <th scope="col">${r_status}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requests}" var="req">
        <tr>
            <td>${req.id}</td>
            <td>${req.fillingDate}</td>
            <td>
                <a href="motor-depot/user/dispatcher/charterer?id=${req.charterer.id}">${req.charterer.name} ${req.charterer.surname}</a>
            </td>
            <td>${req.requiredAutomobileType.type}</td>
            <td>${req.requiredLoadingType.type}</td>
            <td><c:forEach items="${req.deliveryList}" var="delivery">

                <c:out value="${DELIVERY}"/><a href="motor-depot/user/delivery"> #${delivery.id}</a><br/>

            </c:forEach>
            </td>
            <td>${req.executionStatus.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>