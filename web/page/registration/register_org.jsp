<%--
  Created by IntelliJ IDEA.
  User: 12ksa
  Date: 29.04.2018
  Time: 17:14
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
<input class="form-control" type="hidden" name="command" value="org_registration"/>
<label for="org-name"><fmt:message key="content.org.name"/></label>
<input class="form-control" id="org-name" type="text" name="org_name" placeholder="name*" title="<fmt:message key="content.input.title.org.name"/>" pattern="[-\wА-Яа-я\s]{1,45}"/>
<label for="org-site"><fmt:message key="content.org.website"/></label>
<input class="form-control" id="org-site" type="url" name="website" placeholder="your site*" title="<fmt:message key="content.input.title.website"/>" pattern="(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]{1,245}[-a-zA-Z0-9+&@#/%=~_|]"/>
<label for="org-description"><fmt:message key="content.org.description"/></label>
<textarea class="form-control" id="org-description" name="description" placeholder="description"
          required rows="4" cols="50" title="<fmt:message key="content.input.title.text"/>"></textarea>
<label for="org-type"><fmt:message key="content.org.type"/></label>
<select class="form-control" id="org-type" name="type">
    <option value="commercial" selected><fmt:message key="content.org.type.commercial"/></option>
    <option value="noncommercial"><fmt:message key="content.org.type.noncommercial"/></option>
</select>
<br/>
</body>
</html>