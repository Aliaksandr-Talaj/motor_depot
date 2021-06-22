<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.automobile.attachments" var="page_name"/>

<fmt:message bundle="${loc}" key="local.attachment.date" var="a_date"/>
<fmt:message bundle="${loc}" key="local.detachment.date" var="d_date"/>
<fmt:message bundle="${loc}" key="local.driver" var="a_driver"/>
<fmt:message bundle="${loc}" key="local.actions" var="actions"/>
<fmt:message bundle="${loc}" key="local.change" var="CHANGE"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>


<table class="table">
    <thead>
    <th scope="col"><c:out value="${automobile.id} ${automobile.brand} ${automobile.model}"/></th>
    </thead>
</table>
<table class="table">
    <thead>
    <tr>
        <th scope="col">${a_driver}</th>
        <th scope="col">${a_date}</th>
        <th scope="col">${d_date}</th>
        <th scope="col">${actions}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${automobile.automobileAttachmentList}" var="attachment">
        <tr>
            <td><c:out value="${attachment.driver.name} ${attachment.driver.name}"/></td>
            <td>${attachment.dateOfAttachment}</td>
            <td>${attachment.dateOfDetachment}</td>
            <td><a class="btn btn-secondary"
                   href="/motor_depot/user/dispatcher/c_attachment?id=${attachment.id}"
                   role="button">${CHANGE}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>