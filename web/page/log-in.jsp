<%--
  Created by IntelliJ IDEA.
  User: hoi
  Date: 4/25/2018
  Time: 22:58
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
<div class="modal fade text-dark" id="log-in-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.modal.header.log.in"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="loginForm" method="POST" action="controller">
                <div class="modal-body ">
                    <input class="form-control " type="hidden" name="command" value="authentication"/>
                    <br/><fmt:message key="content.user.email"/><br/>
                    <input class="form-control " type="email" name="email" value="" title="<fmt:message key="content.input.title.email"/>" placeholder="default@example.com" pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*[@]([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])" required/>
                    <br/><fmt:message key="content.user.password"/><br/>
                    <input class="form-control " type="password" name="password" value="" title="<fmt:message key="content.input.title.password"/>" placeholder="password" pattern="[\S]{1,45}" required/>
                    <br/>
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
