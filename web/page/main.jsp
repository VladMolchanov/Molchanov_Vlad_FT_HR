<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 20.04.2018
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%request.setCharacterEncoding("UTF-8");%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="../style/style.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script src="../javascript/main-script.js"></script>
    <title><fmt:message key="content.static.main.title"/></title>
</head>
<body>

<c:import url="log-in.jsp"/>

<c:import url="logotype.jsp"/>

<c:import url="registration/register_user.jsp"/>

<c:import url="user_profile.jsp"/>

<c:import url="edit_user_profile.jsp"/>

<div class="full-wrapper">
    <header class="container-fluid bg-dark text-white fixed-top p-2">

        <div class="container ">
            <div class="row">
                <div class="col-2">
                    <nav>
                        <ul class="nav nav-pills ">
                            <li class="nav-item">
                                <a data-toggle="modal" data-target="#logo-site-info" href="#" class="nav-link"><img
                                        style="width:3rem;" src="../resources/logo.png"></a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col clearfix">
                    <div class="float-right">
                        <c:choose>
                            <c:when test="${sessionScope.role==null}">
                                <button id="log-in-button" class="btn btn-dark " data-toggle="modal"
                                        data-target="#log-in-modal">
                                    <fmt:message key="content.button.user.log.in"/>
                                </button>
                                <!-- Button to Open the Modal -->
                                <button id="sign-up-button" class="btn btn-primary " data-toggle="modal"
                                        data-target="#sign-up-modal">
                                    <fmt:message key="content.button.user.sign.up"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${sessionScope.role == 'admin'}">
                                    <a href="${pageContext.request.contextPath}/controller?command=close_old_vacancy">
                                        <button class="btn btn-primary ">
                                            <fmt:message key="content.button.user.close.old.vacancy"/>
                                        </button>
                                    </a>
                                </c:if>
                                <button class="btn btn-primary " data-toggle="modal"
                                        data-target="#user-edit-profile-modal">
                                    <fmt:message key="content.button.profile.edit"/>
                                </button>
                                <button class="btn btn-dark " data-toggle="modal" data-target="#user-profile-modal">
                                    <fmt:message key="content.button.user.profile"/>
                                </button>
                                <!-- Button to Open the Modal -->
                                <a href="${pageContext.request.contextPath}/controller?command=log_out">
                                    <button class="btn btn-primary ">
                                        <fmt:message key="content.button.user.log.out"/>
                                    </button>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <div class="dropdown btn">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                <fmt:message key="content.box.language"/>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=est_russian_locale"><img
                                        src="http://konsomejona.github.io/OctoMouse/images/flag_ru.png"/>Русский</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=est_english_locale"><img
                                        src="https://whitehousefoods.com/wp-content/themes/whitehouse/img/flag.gif">English</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=est_belorussian_locale"><img
                                        src="https://lipis.github.io/flag-icon-css/flags/4x3/by.svg">Беларускі</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="wrapper container">
        <section class="jumbotron">
            <h1><fmt:message key="content.main.slogan.first"/></h1>
            <p><fmt:message key="content.main.slogan.second"/></p>
        </section>
        <c:choose>
            <c:when test="${sessionScope.role == 'admin'|| sessionScope.role == 'director'}">
                <div id="accordion">
                    <div class="card">
                        <div class="card-header">
                            <a class="card-link" data-toggle="collapse" href="#collapseOne">
                                <fmt:message key="content.vacancy"/>
                            </a>
                        </div>
                        <div id="collapseOne" class="collapse show" data-parent="#accordion">
                            <div class="card-body">
                                <c:import url="content/vacancy_content.jsp"/>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'director'}">
                            <div class="card">
                                <div class="card-header">
                                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseTwo">
                                        <fmt:message key="content.request"/>
                                    </a>
                                </div>
                                <div id="collapseTwo" class="collapse" data-parent="#accordion">
                                    <div class="card-body">
                                        <c:import url="content/request_content.jsp"/>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseThree">
                                        <fmt:message key="content.button.add.vacancy"/>
                                    </a>
                                </div>
                                <div id="collapseThree" class="collapse" data-parent="#accordion">
                                    <div class="card-body">
                                        <c:import url="registration/register_vacancy.jsp"/>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card">
                                <div class="card-header">
                                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseFour">
                                        <fmt:message key="content.users"/>
                                    </a>
                                </div>
                                <div id="collapseFour" class="collapse" data-parent="#accordion">
                                    <div class="card-body">
                                        <c:import url="content/user_content.jsp"/>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseFive">
                                        <fmt:message key="content.organizations"/>
                                    </a>
                                </div>
                                <div id="collapseFive" class="collapse" data-parent="#accordion">
                                    <div class="card-body">
                                        <c:import url="content/organization_content.jsp"/>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:when>
            <c:otherwise>
                <c:import url="content/vacancy_content.jsp"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<c:if test="${requestScope.info != null}">
    <script>
        alert("<fmt:message key="${requestScope.info}"/>");
    </script>
</c:if>
<footer class="container-fluid bg-dark text-white p-2">
    <div class="container">
        <ctg:role-time role="${sessionScope.role}"/>
    </div>
</footer>
</body>
</html>
