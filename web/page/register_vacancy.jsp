<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 04.05.2018
  Time: 15:04
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
<form name="loginForm" method="POST" action="controller">
    <input class="form-control" type="hidden" name="command" value="vacancy_registration"/>
    <label for="vacancy-org-site"><fmt:message key="content.vacancy.name"/></label>
    <input class="form-control" id="vacancy-org-site" type="text" name="vacancy_name" title="<fmt:message key="content.input.title.extended.name"/>"
           placeholder="Vacancy name*" pattern="[-,.?!'()\wА-Яа-я\s]{1,45}"/>
    <label for="vacancy-org-description"><fmt:message key="content.vacancy.requirement"/></label>
    <textarea class="form-control" id="vacancy-org-description" name="vacancy_requirement" placeholder="Requirement*" required
              title="<fmt:message key="content.input.title.text"/>" rows="4" cols="50"></textarea><br/>
    <input type="submit" class="btn btn-primary" value="<fmt:message key="content.button.send"/>"/>
</form>
</body>
</html>
