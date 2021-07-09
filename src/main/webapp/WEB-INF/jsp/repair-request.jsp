<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.malfunction-request" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.problem" var="PROBLEM"/>
<fmt:message bundle="${loc}" key="local.enter.problem" var="ENTER_PROBLEM"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>

<jsp:include page="auto-table.jsp"/>

<c:set var="ref" value="/motor_depot/user/driver/add-malfunction"/>

<form action="${ref}" method="post">
    <div class="container">
        <br class="row g-3 ">

        <div class="col-md-3">
            <label class="form-label">${PROBLEM}</label>
            <label>

                <textarea class="form-control" name="problem" id="problem" placeholder="${ENTER_PROBLEM}" rows="3" required></textarea>
            </label>
        </div>
        <div class="col-12">
            <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
        </div>

    </div>
</form>


<jsp:include page="footer.jsp"/>