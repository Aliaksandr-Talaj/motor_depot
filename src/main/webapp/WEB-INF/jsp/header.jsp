<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="rptr" uri="https://role-printer" %>
<!doctype html>


<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.homepage.name" var="brand_name"/>
    <fmt:message bundle="${loc}" key="local.start" var="homepage_name"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.login.btn" var="login_button"/>
    <fmt:message bundle="${loc}" key="local.logout.btn" var="logout_button"/>
    <fmt:message bundle="${loc}" key="local.menu.btn" var="menu_button"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration_mp"/>
    <fmt:message bundle="${loc}" key="local.users.mp" var="users_mp"/>

    <fmt:message bundle="${loc}" key="local.requests.mp" var="requests_mp"/>
    <fmt:message bundle="${loc}" key="local.rides.mp" var="rides_mp"/>
    <fmt:message bundle="${loc}" key="local.my.auto.mp" var="my_auto_mp"/>
    <fmt:message bundle="${loc}" key="local.my.rides.mp" var="my_rides_mp"/>

    <fmt:message bundle="${loc}" key="local.autos.mp" var="autos_mp"/>
    <fmt:message bundle="${loc}" key="local.charterers.mp" var="charterers_mp"/>
    <fmt:message bundle="${loc}" key="local.drivers.mp" var="drivers_mp"/>
    <fmt:message bundle="${loc}" key="local.add.repair" var="add_repair_request"/>
    <fmt:message bundle="${loc}" key="local.add.request" var="add_request"/>
    <fmt:message bundle="${loc}" key="local.add.auto" var="add_auto"/>
    <fmt:message bundle="${loc}" key="local.add.charterer" var="add_charterer"/>

    <!--TEST-->
    <c:set var="sessionScope.role" value="admin"/>
    <!--test-->

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">


    <title><c:out value="${brand_name}"/></title>
</head>
<body>

<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><c:out value="${brand_name}"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">

                    <!-- Link to homepage -->
                    <a class="nav-link active" aria-current="page" href="/motor_depot"><c:out
                            value="${homepage_name}"/></a>
                </li>

                <!-- Admin menu -->
                <c:if test="${sessionScope.role eq 'admin'}">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/motor_depot/admin/registration"><c:out
                            value="${registration_mp}"/></a>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/motor_depot/admin/users"><c:out
                            value="${users_mp}"/></a>
                    </c:if>

                    <!-- Users menu -->

                    <c:if test="${(sessionScope.role eq 'driver') or (sessionScope.role eq 'dispatcher')}">


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <c:out value="${menu_button}"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a class="dropdown-item" href="/motor_depot/user/requests"><c:out
                                value="${requests_mp}"/></a></li>

                        <li><a class="dropdown-item" href="/motor_depot/user/rides"><c:out value="${rides_mp}"/></a>
                        </li>


                        <!-- DRIVERS MENU -->

                        <c:if test="${sessionScope.role eq 'driver'}">

                            <li><a class="dropdown-item" href="/motor_depot/user/driver/auto"><c:out value="${my_auto_mp}"/></a></li>

                            <li><a class="dropdown-item" href="/motor_depot/user/driver/rides"><c:out value="${my_rides_mp}"/></a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/motor_depot/user/driver/repair-request"><c:out value="${add_repair_request}"/></a></li>
                        </c:if>

                        <!-- DISPATCHERS MENU -->

                        <c:if test="${sessionScope.role eq 'dispatcher'}">

                            <li><a class="dropdown-item" href="/motor_depot/user/dispatcher/autos"><c:out
                                    value="${autos_mp}"/></a></li>

                            <li><a class="dropdown-item" href="/motor_depot/user/dispatcher/drivers"><c:out
                                    value="${drivers_mp}"/></a></li>

                            <li><a class="dropdown-item" href="/motor_depot/user/dispatcher/charterers"><c:out
                                    value="${charterers_mp}"/></a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/motor_depot/user/dispatcher/request-form"><c:out
                                    value="${add_request}"/></a></li>

                            <li><a class="dropdown-item" href="/motor_depot/user/dispatcher/auto-form"><c:out
                                    value="${add_auto}"/></a></li>

                            <li><a class="dropdown-item" href="/motor_depot/user/dispatcher/charterer-form"><c:out
                                    value="${add_charterer}"/></a></li>
                        </c:if>

                    </ul>
                </li>
                </c:if>
            </ul>


            <!--Right side-->

            <!--Login/logout button-->
            <rptr:printRole/>
            <c:choose>
                <c:when test="${(empty sessionScope.role)}">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <c:if test="${not(request.action eq 'login')}">
                            <a class="btn btn-success btn-lg" href="login" role="button "><c:out
                                    value="${login_button}"/></a>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <c:if test="${not(request.action eq 'login')}">
                            <a class="btn btn-dark btn-lg" href="logout" role="button "><c:out
                                    value="${logout_button}"/></a>
                        </c:if>
                    </div>
                </c:otherwise>
            </c:choose>


            <div class="d-grid gap-2 d-md-block">

                <!--Local RU button-->

                <c:choose>
                    <c:when test="${local eq 'ru'}">
                        <form class="d-flex">
                            <input class="btn btn-light" type="hidden" name="local" value="ru" aria-label="local"/>
                            <button class="btn btn-light" type="submit"><c:out value="${ru_button}"/></button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form class="d-flex">
                            <input class="btn btn-secondary btn-sm" type="hidden" name="local" value="ru"
                                   aria-label="local"/>
                            <button class="btn btn-secondary btn-sm" type="submit"><c:out
                                    value="${ru_button}"/></button>
                        </form>
                    </c:otherwise>
                </c:choose>


                <!--Local EN button-->

                <c:choose>
                    <c:when test="${local eq 'en'}">
                        <form class="d-flex">
                            <input class="btn btn-light" type="hidden" name="local" value="en" aria-label="local"/>
                            <button class="btn btn-light" type="submit"><c:out value="${en_button}"/></button>
                        </form>

                    </c:when>
                    <c:otherwise>
                        <form class="d-flex">
                            <input class="btn btn-secondary btn-sm" type="hidden" name="local" value="en"
                                   aria-label="local"/>
                            <button class="btn btn-secondary btn-sm" type="submit"><c:out
                                    value="${en_button}"/></button>
                        </form>
                    </c:otherwise>
                </c:choose>

            </div>


            <!--  <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Search</button>
              </form> -->

        </div>
    </div>
</nav>