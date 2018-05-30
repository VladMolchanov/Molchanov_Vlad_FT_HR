<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 16.05.2018
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="content"/>

<html>
<head>

    <title><fmt:message key="content.error.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/error.css">

</head>
<body>
<div align="center">
    <a href="${pageContext.request.contextPath}/page/main.jsp"><fmt:message key="content.error.back"/> </a>
</div>
</body>
</html>