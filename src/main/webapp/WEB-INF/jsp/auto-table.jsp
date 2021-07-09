<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.auto" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.auto.number" var="AUTO_NUMBER"/>
<fmt:message bundle="${loc}" key="local.auto.brand" var="AUTO_BRAND"/>
<fmt:message bundle="${loc}" key="local.auto.model" var="AUTO_MODEL"/>
<fmt:message bundle="${loc}" key="local.auto.fuel.type" var="AUTO_FUEL"/>
<fmt:message bundle="${loc}" key="local.auto.carrying" var="AUTO_CARRYING"/>
<fmt:message bundle="${loc}" key="local.auto.type" var="AUTO_TYPE"/>
<fmt:message bundle="${loc}" key="local.auto.equipment" var="AUTO_EQUIPMENT"/>
<fmt:message bundle="${loc}" key="local.auto.loading.types" var="AUTO_TYPES"/>
<fmt:message bundle="${loc}" key="local.auto.platform.length" var="AUTO_P_LENGTH"/>
<fmt:message bundle="${loc}" key="local.auto.platform.width" var="AUTO_P_WIDTH"/>
<fmt:message bundle="${loc}" key="local.auto.cargo.height.limit" var="AUTO_H_LIMIT"/>
<fmt:message bundle="${loc}" key="local.auto.cargo.volume.limit" var="AUTO_V_LIMIT"/>
<fmt:message bundle="${loc}" key="local.auto.status" var="AUTO_STATUS"/>
<fmt:message bundle="${loc}" key="local.view.attachments" var="V_ATTACHMENTS"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.weight.units" var="W_UNIT"/>
<fmt:message bundle="${loc}" key="local.length.units" var="L_UNIT"/>
<fmt:message bundle="${loc}" key="local.unit.volume" var="V_UNIT"/>

<table class="table">
    <thead>
    <tr>
        <th scope="col">${AUTO_NUMBER}</th>
        <th scope="col">${AUTO_BRAND}</th>
        <th scope="col">${AUTO_MODEL}</th>
        <th scope="col">${AUTO_FUEL}</th>
        <th scope="col">${AUTO_CARRYING}<br/>${W_UNIT}</th>
        <th scope="col">${AUTO_TYPE}</th>
        <th scope="col">${AUTO_EQUIPMENT}</th>
        <th scope="col">${AUTO_TYPES}</th>
        <th scope="col">${AUTO_P_LENGTH}<br/>${L_UNIT}</th>
        <th scope="col">${AUTO_P_WIDTH}<br/>${L_UNIT}</th>
        <th scope="col">${AUTO_H_LIMIT}<br/>${L_UNIT}</th>
        <th scope="col">${AUTO_V_LIMIT}<br/>${V_UNIT}</th>
        <th scope="col">${AUTO_STATUS}</th>

    </tr>
    </thead>
    <tbody>
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

    </tr>
    </tbody>
</table>
