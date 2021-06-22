<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.drivers" var="page_name"/>
<fmt:message bundle="${loc}" key="local.user.name" var="NAME"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="SURNAME"/>
<fmt:message bundle="${loc}" key="local.current.attached.auto" var="CURENT_ATT"/>
<fmt:message bundle="${loc}" key="local.attachment.date" var="ATT_DATE"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>


<table class="table">
    <thead>
    <tr>
        <th scope="col">${NAME}</th>
        <th scope="col">${SURNAME}</th>
        <th scope="col">${CURENT_ATT}</th>
        <%--    <th scope="col">${actions}</th>--%>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="drivers" scope="request" type="java.util.List"/>
    <c:forEach items="${drivers}" var="driver">
        <tr>
            <td>${driver.driver.name}</td>
            <td>${driver.driver.surname}</td>
            <td>
                <c:if test="${driver.currentAttachment.automobile ne null}">
                    <c:out value="${driver.currentAttachment.automobile.id} ${driver.currentAttachment.automobile.brand} ${driver.currentAttachment.automobile.model}"/>
                    <br/>
                    <fmt:formatDate value="${driver.currentAttachment.dateOfAttachment}" var="att_date"
                                    pattern="dd-MM-yyyy"/>
                    <c:out value="${ATT_DATE}: ${att_date}"/>
                </c:if>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>