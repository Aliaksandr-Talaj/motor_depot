<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registration" var="page_name"/>
<fmt:message bundle="${loc}" key="local.user.name" var="NAME"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="SURNAME"/>
<fmt:message bundle="${loc}" key="local.user.login" var="LOGIN"/>
<fmt:message bundle="${loc}" key="local.password" var="PASSWORD"/>
<fmt:message bundle="${loc}" key="local.user.role" var="ROLE"/>
<fmt:message bundle="${loc}" key="local.user.status" var="STATUS"/>
<fmt:message bundle="${loc}" key="local.user.enter.name" var="ENTER_NAME"/>
<fmt:message bundle="${loc}" key="local.user.enter.surname" var="ENTER_SURNAME"/>
<fmt:message bundle="${loc}" key="local.user.username" var="USERNAME"/>
<fmt:message bundle="${loc}" key="local.choose" var="CHOOSE"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>
<fmt:message bundle="${loc}" key="local.admin" var="ADMIN"/>
<fmt:message bundle="${loc}" key="local.dispatcher" var="DISPATCHER"/>
<fmt:message bundle="${loc}" key="local.driver" var="DRIVER"/>
<fmt:message bundle="${loc}" key="local.active" var="ACTIVE"/>
<fmt:message bundle="${loc}" key="local.blocked" var="BLOCKED"/>

<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>

<form action="/motor_depot/admin/add_user" method="post">
<div class="container">
    <br class="row g-3 needs-validation" novalidate>
    <div class="col-md-2">
        <label for="validationCustom01" class="form-label">${NAME}</label>
        <input type="text" name="name" class="form-control" id="validationCustom01" placeholder="${ENTER_NAME}" required>

    </div>
    <div class="col-md-2">
        <label for="validationCustom02" class="form-label">${SURNAME}</label>
        <input type="text" name="surname" class="form-control" id="validationCustom02" PLACEHOLDER="${ENTER_SURNAME}" required>

    </div>
    <div class="col-md-2">
        <label for="validationCustomUsername" class="form-label">${USERNAME}</label>
        <div class="input-group has-validation">
            <span class="input-group-text" id="inputGroupPrepend">${LOGIN}</span>
            <input type="text"  name="login" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend"
                   required>

        </div>
    </div>
    <div class="col-md-2">
        <label for="validationCustomUsername" class="form-label"></label>
        <div class="input-group has-validation">
            <span class="input-group-text" id="inputPasswd">${PASSWORD}</span>
            <label for="validationCustomPassword"></label><input type="text" name="password" class="form-control" id="validationCustomPassword" aria-describedby="inputGroupPrepend"
                                                                 required>

        </div>
    </div>
</div>
<div class="container">
    <div class="col-md-2">
        <label for="validationCustom04" class="form-label">${ROLE}</label>
        <select class="form-select" name="roleId" id="validationCustom04" required>
            <option selected disabled value="">${CHOOSE}</option>
            <option value="1">${ADMIN}</option>
            <option value="2">${DISPATCHER}</option>
            <option value="3">${DRIVER}</option>
        </select>

    </div>
    <div class="col-md-2">
        <label for="validationCustom04" class="form-label">${STATUS}</label>
        <select class="form-select" name="statusId" id="validationCustom05" required>
            <option selected disabled value="">${CHOOSE}</option>
            <option value="1">${ACTIVE}</option>
            <option value="2">${BLOCKED}</option>
        </select>

    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
    </div>
</div>
</form>

<jsp:include page="footer.jsp"/>