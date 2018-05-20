<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 13.05.2018
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
</head>
<body>
<div class="modal fade text-dark" id="user-profile-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.modal.header.profile"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="profileForm" method="POST" action="controller">
                <div class="modal-body ">
                    <p><fmt:message key="content.user.first.name"/> - ${sessionScope.user_info.firstName}</p>
                    <p><fmt:message key="content.user.last.name"/> - ${sessionScope.user_info.lastName}</p>
                    <p><fmt:message key="content.user.email"/> - ${sessionScope.user_info.email}</p>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'admin'}">

                        </c:when>
                        <c:when test="${sessionScope.user_info.organization.id == '0'}">
                            <button class="btn btn-primary " data-toggle="collapse" data-target="#org-register-form">
                                <fmt:message key="content.button.add.organization"/>
                            </button>
                        </c:when>
                        <c:when test="${sessionScope.user_info.organization.id != '0'}">
                            <p><fmt:message key="content.org.name"/> - ${sessionScope.user_info.organization.name}</p>
                            <a href="${sessionScope.user_info.organization.website}"><fmt:message key="content.org.website"/></a>
                        </c:when>
                    </c:choose>

                    <div id="org-register-form" class="collapse">
                        <div id="sign-up-org-form"><c:import url="register_org.jsp"/></div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <input class="btn btn-primary float-left" type="submit"
                           value="<fmt:message key="content.button.submit"/>"/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <fmt:message key="content.button.cancel"/>
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
