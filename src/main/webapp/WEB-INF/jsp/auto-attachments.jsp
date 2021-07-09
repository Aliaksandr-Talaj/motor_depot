<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.automobile.attachments" var="PAGE_NAME"/>

<fmt:message bundle="${loc}" key="local.attachment.date" var="a_date"/>
<fmt:message bundle="${loc}" key="local.detachment.date" var="d_date"/>
<fmt:message bundle="${loc}" key="local.driver" var="a_driver"/>
<fmt:message bundle="${loc}" key="local.actions" var="ACTIONS"/>
<fmt:message bundle="${loc}" key="local.change" var="CHANGE"/>
<fmt:message bundle="${loc}" key="local.detach" var="DETACH"/>
<fmt:message bundle="${loc}" key="local.attach" var="ATTACH"/>
<fmt:message bundle="${loc}" key="local.new.attach" var="NEW_ATTACH"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${PAGE_NAME}"/></span>
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
        <th scope="col">${ACTIONS}</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="addButtonIsNedded" value="false"/>
    <c:if test="${empty automobile.automobileAttachmentList}"><c:set var="addButtonIsNedded" value="true"/></c:if>
    <c:forEach items="${automobile.automobileAttachmentList}" var="attachment">
        <tr>
            <td><c:out value="${attachment.driver.name} ${attachment.driver.surname}"/></td>
            <td>${attachment.dateOfAttachment}</td>
            <td>${attachment.dateOfDetachment}</td>
            <td>
                <c:choose>
                    <c:when test="${attachment.dateOfDetachment eq null}">
                        <c:set var="hasActiveAttachment" value="true"/>
                        <form method="post" action="/motor_depot/user/dispatcher/change-attachment">
                            <input type="hidden" value="${attachment.id}" name="attachmentId"/>
                            <input type="hidden" value="${automobile.id}" name="automobileId"/>
                            <button class="btn btn-secondary"
                                    role="button" type="submit" value="Select">${CHANGE}</button>
                        </form>
                        <form method="post" action="/motor_depot/user/dispatcher/detach-driver">
                            <input type="hidden" value="${attachment.id}" name="attachmentId"/>
                            <button class="btn btn-secondary"
                                    role="button" type="submit" value="Select">${DETACH}</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form method="post" action="/motor_depot/user/dispatcher/attach-driver">
                            <input type="hidden" value="${automobile.id}" name="automobileId"/>
                            <input type="hidden" value="${attachment.driver.id}" name="driverId"/>
                            <button class="btn btn-secondary"
                                    role="button" type="submit" value="Select">${ATTACH}</button>
                        </form>
                    </c:otherwise>

                </c:choose>


            </td>
        </tr>
    </c:forEach>
    <c:if test="${addButtonIsNedded}">
        <form method="post" action="/motor_depot/user/dispatcher/attach-new-driver">
            <input type="hidden" value="${automobile.id}" name="automobileId"/>
            <button class="btn btn-secondary"
                    role="button" type="submit" value="Select">${NEW_ATTACH}</button>
        </form>
    </c:if>
    </tbody>
</table>


<jsp:include page="footer.jsp"/>