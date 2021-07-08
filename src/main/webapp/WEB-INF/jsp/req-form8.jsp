<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.request-form" var="page_name"/>
<fmt:message bundle="${loc}" key="local.req7" var="REQUEST_PAGE_7"/>
<fmt:message bundle="${loc}" key="local.loading.type.required" var="REQ_L_TYPE"/>
<fmt:message bundle="${loc}" key="local.auto.type.required" var="REQ_A_TYPE"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>
<fmt:message bundle="${loc}" key="local.nothing" var="NOTHING"/>
<fmt:message bundle="${loc}" key="local.tent" var="TENT"/>
<fmt:message bundle="${loc}" key="local.van" var="VAN"/>
<fmt:message bundle="${loc}" key="local.flatbed" var="FLATBED"/>
<fmt:message bundle="${loc}" key="local.dump" var="DUMP"/>
<fmt:message bundle="${loc}" key="local.loading.type.top" var="TOP"/>
<fmt:message bundle="${loc}" key="local.loading.type.rear" var="REAR"/>
<fmt:message bundle="${loc}" key="local.loading.type.side" var="SIDE"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>
<nav class="navbar navbar-WARNING bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${REQUEST_PAGE_7}"/></span>
    </div>
</nav>

<jsp:useBean id="generatingRequest" class="by.talai.model.Request" scope="session" type="by.talai.model.Request"/>
<jsp:useBean id="generatingDelivery" class="by.talai.model.Delivery" scope="session" type="by.talai.model.Delivery"/>
<jsp:useBean id="generatingCargo" class="by.talai.model.Cargo" scope="session" type="by.talai.model.Cargo"/>
<jsp:useBean id="generatingUnit" class="by.talai.model.Unit" scope="session" type="by.talai.model.Unit"/>

<c:set var="ref" value="/motor_depot/user/dispatcher/request_generated"/>

<form action="${ref}" method="post">
    <div class="container">
        <br class="row g-3 needs-validation" novalidate>

        <div class="col-md-4">
            <label class="form-label">${REQ_A_TYPE}</label>
            <label>
                <select class="form-select" name="type" >
                    <option selected value="0">${NOTHING}</option>
                    <option value="1">${TENT}</option>
                    <option value="2">${VAN}</option>
                    <option value="3">${FLATBED}</option>
                    <option value="4">${DUMP}</option>
                </select>
            </label>
        </div>
        <div class="col-md-4">
            <label class="form-label">${REQ_L_TYPE}</label>
            <label>
                <select class="form-select" name="loadingType" >
                    <option selected value="0">${NOTHING}</option>
                    <option value="1">${TOP}</option>
                    <option value="2">${REAR}</option>
                    <option value="3">${SIDE}</option>
                </select>
            </label>
        </div>


        <div class="col-12">
            <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
        </div>

    </div>
</form>

<jsp:include page="footer.jsp"/>