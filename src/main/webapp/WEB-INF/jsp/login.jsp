<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.login" var="page_name"/>
<fmt:message bundle="${loc}" key="local.login.btn" var="login_btn"/>
<fmt:message bundle="${loc}" key="local.login.sub" var="login_sub"/>
<fmt:message bundle="${loc}" key="local.loginform" var="login_ph"/>
<fmt:message bundle="${loc}" key="local.password" var="password_sub"/>
<fmt:message bundle="${loc}" key="local.passwordform" var="password_ph"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>


</br>
</br>
<center>
    <form method="POST" action="j_security_check">
        <table border="0">
            <tr>
                <td><c:out value="${login_sub}"/></td>
                <td><input type="text" placeholder="<c:out value="${login_ph}"/>" name="j_username"></td>
            </tr>
            <tr>
                <td><c:out value="${password_sub}"/></td>
                <td><input type="password" placeholder="<c:out value="${password_ph}"/>" name="j_password"></td>
            </tr>
        </table>
        <div class="row">
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-success"><c:out value="${login_btn}"/></button>
                </div>
            </div>
        </div>
    </form>
    <div class="row">
        <div class="col"></div>
        <div class="col">
            <p class="login_error"><%=request.getAttribute("error") == null ? "" : request.getAttribute("error")%>
            </p>
        </div>
    </div>
</center>

<jsp:include page="footer.jsp"/>