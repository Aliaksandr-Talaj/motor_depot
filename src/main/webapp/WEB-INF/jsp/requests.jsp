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
<fmt:message bundle="${loc}" key="local.actions" var="r_actions"/>
<fmt:message bundle="${loc}" key="local.process" var="r_process"/>
<fmt:message bundle="${loc}" key="local.request.equipment" var="r_req_equipment"/>

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
        <th scope="col">${r_req_equipment}</th>
        <th scope="col">${r_deliveries}</th>
        <th scope="col">${r_status}</th>
        <th scope="col">${r_actions}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestsDto}" var="reqDto">
        <tr>
            <td>${reqDto.request.id}</td>
            <td>${reqDto.request.fillingDate}</td>
            <td>
                <a href="/motor_depot/user/dispatcher/charterer?id=${reqDto.request.charterer.id}">${reqDto.request.charterer.name} ${reqDto.request.charterer.surname}</a>
            </td>
            <td>${reqDto.request.requiredAutomobileType.type}</td>
            <td>${reqDto.request.requiredLoadingType.type}</td>
            <td><c:forEach items="${reqDto.equipmentSet}" var="equipment">
                ${equipment.name}
            </c:forEach><br/>
            </td>
            <td><c:forEach items="${reqDto.request.deliveryList}" var="delivery">

                <c:out value="${DELIVERY}"/><a href="motor_depot/user/delivery"> #${delivery.id}</a><br/>

            </c:forEach>
            </td>
            <td>${reqDto.request.executionStatus.status}</td>
            <td>
                <c:if test="${reqDto.request.executionStatus.id eq 1}">
                    <c:if test="${sessionScope.role eq 'dispatcher'}">
                        <a href="motor_depot/user/dispatcher/request-form?id=${reqDto.request.id}">${r_process}</a>
                    </c:if>
                </c:if>


            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>