<%--
  Created by IntelliJ IDEA.
  User: hoi
  Date: 4/25/2018
  Time: 22:50
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
<div class="modal fade text-dark" id="sign-up-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.modal.header.sign.up"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="loginForm" method="POST" action="controller">
                <div class="modal-body ">
                    <div id="sign-up-as-user-form">
                        <input class="form-control" type="hidden" name="command" value="user_registration"/>
                        <br/><fmt:message key="content.user.first.name"/><br/>
                        <input class="form-control" type="text" name="first_name" placeholder="name*"
                               pattern="[A-ZА-Я][a-zа-я]{1,44}"/>
                        <br/><fmt:message key="content.user.last.name"/><br/>
                        <input class="form-control" type="text" name="last_name" placeholder="surname*"
                               pattern="[A-ZА-Я][a-zа-я]{1,44}"/>
                        <br/><fmt:message key="content.user.email"/><br/>
                        <input class="form-control" type="email" name="email" placeholder="default@example.com"
                               pattern="([a-z0-9!#$%&'*+/=?^_`{|}~-]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*)[@](([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z]))"
                               required/>
                        <br/><fmt:message key="content.user.password"/><br/>
                        <input class="form-control" type="password" name="repeat_password" placeholder="password*"
                               pattern="[\S]{1,45}" required/>
                        <br/><fmt:message key="content.user.repeat.password"/><br/>
                        <input class="form-control" type="password" name="password" placeholder="password*"
                               pattern="[\S]{1,45}" required/>
                        <br/>
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
