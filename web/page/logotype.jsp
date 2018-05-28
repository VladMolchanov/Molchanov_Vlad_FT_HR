<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 13.05.2018
  Time: 14:58
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
<!-- The Modal -->
<div class="modal fade text-dark" id="logo-site-info">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.site.description.title"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body ">
                <fmt:message key="content.site.description"/>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1176.830614665955!2d27.472465092327436!3d53.84888906322186!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbd085ccd23975%3A0xfb48adb66cee379c!2z0JzQsNC70LjQvdC-0LLQutCw!5e0!3m2!1sru!2sby!4v1527513054552" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">
                    <fmt:message key="content.button.cancel"/>
                </button>
            </div>

        </div>
    </div>
</div>
</body>
</html>
