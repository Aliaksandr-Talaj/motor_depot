<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.auto.form" var="page_name"/>
<fmt:message bundle="${loc}" key="local.auto.number" var="NUMBER"/>
<fmt:message bundle="${loc}" key="local.auto.enter.number" var="E_NUMBER"/>
<fmt:message bundle="${loc}" key="local.auto.brand" var="BRAND"/>
<fmt:message bundle="${loc}" key="local.auto.enter.brand" var="E_BRAND"/>
<fmt:message bundle="${loc}" key="local.auto.model" var="MODEL"/>
<fmt:message bundle="${loc}" key="local.auto.enter.model" var="E_MODEL"/>
<fmt:message bundle="${loc}" key="local.auto.fuel.type" var="FUEL"/>
<fmt:message bundle="${loc}" key="local.auto.carrying" var="CARRYING"/>
<fmt:message bundle="${loc}" key="local.auto.enter.carrying" var="E_CARRYING"/>
<fmt:message bundle="${loc}" key="local.auto.type" var="TYPE"/>
<fmt:message bundle="${loc}" key="local.q.equipment" var="EQUIPMENT"/>
<fmt:message bundle="${loc}" key="local.auto.platform.length" var="P_LENGTH"/>
<fmt:message bundle="${loc}" key="local.auto.enter.platform.length" var="E_P_LENGTH"/>
<fmt:message bundle="${loc}" key="local.auto.platform.width" var="P_WIDTH"/>
<fmt:message bundle="${loc}" key="local.auto.enter.platform.width" var="E_P_WIDTH"/>
<fmt:message bundle="${loc}" key="local.auto.cargo.height.limit" var="C_H_LIMIT"/>
<fmt:message bundle="${loc}" key="local.auto.enter.cargo.height.limit" var="E_C_H_LIMIT"/>
<fmt:message bundle="${loc}" key="local.auto.cargo.volume.limit" var="C_V_LIMIT"/>
<fmt:message bundle="${loc}" key="local.auto.enter.cargo.volume.limit" var="E_C_V_LIMIT"/>
<fmt:message bundle="${loc}" key="local.choose" var="CHOOSE"/>
<fmt:message bundle="${loc}" key="local.petrol" var="PETROL"/>
<fmt:message bundle="${loc}" key="local.diesel" var="DIESEL"/>
<fmt:message bundle="${loc}" key="local.lpg" var="LPG"/>
<fmt:message bundle="${loc}" key="local.tent" var="TENT"/>
<fmt:message bundle="${loc}" key="local.van" var="VAN"/>
<fmt:message bundle="${loc}" key="local.flatbed" var="FLATBED"/>
<fmt:message bundle="${loc}" key="local.dump" var="DUMP"/>
<fmt:message bundle="${loc}" key="local.nothing" var="NOTHING"/>
<fmt:message bundle="${loc}" key="local.fridge" var="FRIDGE"/>
<fmt:message bundle="${loc}" key="local.hydroplatform" var="H_PLATFORM"/>
<fmt:message bundle="${loc}" key="local.selfloader" var="S_LOADER"/>
<fmt:message bundle="${loc}" key="local.yes" var="YES"/>
<fmt:message bundle="${loc}" key="local.no" var="NO"/>
<fmt:message bundle="${loc}" key="local.q.ltype.top" var="L_TYPE_TOP"/>
<fmt:message bundle="${loc}" key="local.q.ltype.rear" var="L_TYPE_REAR"/>
<fmt:message bundle="${loc}" key="local.q.ltype.side" var="L_TYPE_SIDE"/>
<fmt:message bundle="${loc}" key="local.submit" var="SUBMIT"/>


<!--Page name -->
<nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1"><c:out value="${page_name}"/></span>
    </div>
</nav>


<form action="/motor_depot/admin/add_auto" method="post">
    <div class="container">
        <br class="row g-3 ">
        <div class="col-md-4">
            <label class="form-label">${NUMBER}</label>
            <label>
                <input type="text" name="id" class="form-control" placeholder="${E_NUMBER}" required>
            </label>

        </div>

        <div class="col-md-4">
            <label class="form-label">${BRAND}</label>
            <label>
                <input type="text" name="brand" class="form-control" placeholder="${E_BRAND}" required>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${MODEL}</label>
            <label>
                <input type="text" name="model" class="form-control" PLACEHOLDER="${E_MODEL}" required>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${FUEL}</label>
            <label>
                <select class="form-select" name="fuel" required>
                    <option selected disabled value="">${CHOOSE}</option>
                    <option value="1">${PETROL}</option>
                    <option value="2">${DIESEL}</option>
                    <option value="3">${LPG}</option>
                </select>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${CARRYING}</label>
            <label>
                <input type="number" name="carrying" class="form-control" PLACEHOLDER="${E_CARRYING}" required>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${TYPE}</label>
            <label>
                <select class="form-select" name="type" required>
                    <option selected disabled value="">${CHOOSE}</option>
                    <option value="1">${TENT}</option>
                    <option value="2">${VAN}</option>
                    <option value="3">${FLATBED}</option>
                    <option value="4">${DUMP}</option>
                </select>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${EQUIPMENT}</label>
            <label>
                <select class="form-select" name="equipment1">
                    <option selected disabled value="0">${CHOOSE}</option>
                    <option value="0">${NOTHING}</option>
                    <option value="1">${FRIDGE}</option>
                    <option value="2">${H_PLATFORM}</option>
                    <option value="3">${S_LOADER}</option>
                </select>
            </label>
            <label>
                <select class="form-select" name="equipment2">
                    <option selected disabled value="0">${CHOOSE}</option>
                    <option value="0">${NOTHING}</option>
                    <option value="1">${FRIDGE}</option>
                    <option value="2">${H_PLATFORM}</option>
                    <option value="3">${S_LOADER}</option>
                </select>
            </label>
            <label>
                <select class="form-select" name="equipment3">
                    <option selected disabled value="0">${CHOOSE}</option>
                    <option value="0">${NOTHING}</option>
                    <option value="1">${FRIDGE}</option>
                    <option value="2">${H_PLATFORM}</option>
                    <option value="3">${S_LOADER}</option>
                </select>
            </label>

        </div>

        <div class="col-md-4">

            <label class="form-label">${L_TYPE_TOP}</label>
            <label>
                <select class="form-select" name="top" required>
                    <option selected disabled value="">${CHOOSE}</option>
                    <option value="1">${YES}</option>
                    <option value="0">${NO}</option>
                </select>
            </label>
            <label class="form-label">${L_TYPE_REAR}</label>
            <label>
                <select class="form-select" name="rear" required>
                    <option selected disabled value="">${CHOOSE}</option>
                    <option value="1">${YES}</option>
                    <option value="0">${NO}</option>
                </select>
            </label>
            <label class="form-label">${L_TYPE_SIDE}</label>
            <label>
                <select class="form-select" name="side" required>
                    <option selected disabled value="">${CHOOSE}</option>
                    <option value="1">${YES}</option>
                    <option value="0">${NO}</option>
                </select>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${P_LENGTH}</label>
            <label>
                <input type="number" name="p_length" class="form-control" PLACEHOLDER="${E_P_LENGTH}" required>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${P_WIDTH}</label>
            <label>
                <input type="number" name="p_width" class="form-control" PLACEHOLDER="${E_P_WIDTH}" required>
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${C_H_LIMIT}</label>
            <label>
                <input type="number" name="h_limit" class="form-control" PLACEHOLDER="${E_C_H_LIMIT}" step="any">
            </label>
        </div>

        <div class="col-md-4">
            <label class="form-label">${C_V_LIMIT}</label>
            <label>
                <input type="text" name="v_limit" class="form-control" PLACEHOLDER="${E_C_V_LIMIT}">
            </label>
        </div>


        <div class="col-12">
            <button class="btn btn-primary" type="submit" value="Save">${SUBMIT}</button>
        </div>
    </div>
</form>


<jsp:include page="footer.jsp"/>