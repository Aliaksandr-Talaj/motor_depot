<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
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

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>


<jsp:include page="auto-table.jsp"/>


<jsp:include page="footer.jsp"/>