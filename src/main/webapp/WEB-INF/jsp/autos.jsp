<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.autos" var="PAGE_NAME"/>

<fmt:message bundle="${loc}" key="local.auto.number" var="auto_number"/>
<fmt:message bundle="${loc}" key="local.auto.brand" var="auto_brand"/>
<fmt:message bundle="${loc}" key="local.auto.model" var="auto_model"/>
<fmt:message bundle="${loc}" key="local.auto.fuel.type" var="auto_fuel"/>
<fmt:message bundle="${loc}" key="local.auto.carrying" var="auto_carrying"/>
<fmt:message bundle="${loc}" key="local.auto.type" var="auto_type"/>
<fmt:message bundle="${loc}" key="local.auto.equipment" var="auto_equipment"/>
<fmt:message bundle="${loc}" key="local.auto.loading.types" var="auto_types"/>
<fmt:message bundle="${loc}" key="local.auto.platform.length" var="auto_p_length"/>
<fmt:message bundle="${loc}" key="local.auto.platform.width" var="auto_p_width"/>
<fmt:message bundle="${loc}" key="local.auto.cargo.height.limit" var="auto_h_limit"/>
<fmt:message bundle="${loc}" key="local.auto.cargo.volume.limit" var="auto_v_limit"/>
<fmt:message bundle="${loc}" key="local.auto.status" var="auto_status"/>
<fmt:message bundle="${loc}" key="local.view.attachments" var="v_att"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.weight.units" var="w_unit"/>
<fmt:message bundle="${loc}" key="local.length.units" var="l_unit"/>
<fmt:message bundle="${loc}" key="local.unit.volume" var="v_unit"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<table class="table">
    <thead>
    <tr>
        <th scope="col">${auto_number}</th>
        <th scope="col">${auto_brand}</th>
        <th scope="col">${auto_model}</th>
        <th scope="col">${auto_fuel}</th>
        <th scope="col">${auto_carrying}<br/>${w_unit}</th>
        <th scope="col">${auto_type}</th>
        <th scope="col">${auto_equipment}</th>
        <th scope="col">${auto_types}</th>
        <th scope="col">${auto_p_length}<br/>${l_unit}</th>
        <th scope="col">${auto_p_width}<br/>${l_unit}</th>
        <th scope="col">${auto_h_limit}<br/>${l_unit}</th>
        <th scope="col">${auto_v_limit}<br/>${v_unit}</th>
        <th scope="col">${auto_status}</th>
        <th scope="col">${ACTIONS}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${automobiles}" var="auto">
        <tr>
            <td>${auto.id}</td>
            <td>${auto.brand}</td>
            <td>${auto.model}</td>
            <td>${auto.fuelType.type}</td>
            <td>${auto.carrying}</td>
            <td>${auto.automobileType.type}</td>
            <td>
                <c:forEach items="${auto.equipmentSet}" var="equipment">
                    ${equipment.name}
                </c:forEach>
            </td>
            <td>
                <c:forEach items="${auto.loadingTypes}" var="l_type">
                    ${l_type.type}<br/>
                </c:forEach>
                    </td>
            <td>${auto.platformLength}</td>
            <td>${auto.platformWidth}</td>
            <td>${auto.cargoHeightLimit}</td>
            <td>${auto.cargoVolumeLimit}</td>
            <td>${auto.technicalStatus.status}</td>
            <td>
                <a class="btn btn-secondary"
                   href="/motor_depot/user/dispatcher/v_attachment?id=${auto.id}"
                   role="button">${v_att}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>