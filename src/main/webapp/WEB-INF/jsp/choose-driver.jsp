<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.drivers" var="PAGE_NAME"/>
<fmt:message bundle="${loc}" key="local.user.name" var="NAME"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="SURNAME"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.choose" var="CHOOSE"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
    </div>
</nav>


<table class="table">
    <thead>
    <tr>
        <th scope="col">${NAME}</th>
        <th scope="col">${SURNAME}</th>
        <th scope="col">${ACTIONS}</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="drivers" scope="request" type="java.util.List"/>
    <c:forEach items="${drivers}" var="driver">
        <c:if test="${driver.currentAttachment.automobile eq null}">
            <tr>
                <td>${driver.driver.name}</td>
                <td>${driver.driver.surname}</td>
                <td>
                    <form method="post" action="/motor_depot/user/dispatcher/attach-driver">
                        <input type="hidden" value="${driver.driver.id}" name="driverId"/>
                        <input type="hidden" value="${automobileId}" name="automobileId"/>
                        <button class="btn btn-secondary"
                                role="button" type="submit" value="Select">${CHOOSE}</button>
                    </form>
                </td>

            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>