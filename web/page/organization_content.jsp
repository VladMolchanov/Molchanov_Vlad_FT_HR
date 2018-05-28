<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 28.05.2018
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
</head>
<body>
<form name="user_control" method="POST" action="controller">
    <div class="container-fluid form-group">
        <input type="hidden" name="command" value="delete_organization">
        <input class="btn btn-primary " type="submit" value="<fmt:message key="content.button.delete"/>"/>
    </div>
    <table class="table table-hover">
        <tbody id="user-table">
        <tr>
            <th></th>
            <th><fmt:message key="content.org.name"/></th>
            <th><fmt:message key="content.org.description"/></th>
            <th><fmt:message key="content.org.website"/></th>
            <th><fmt:message key="content.org.type"/></th>
        </tr>
        </tbody>
        <c:forEach items="${requestScope.organization_list}" var="organization">
            <tr>
                <td><label>
                    <input type="checkbox" name="organization_id" value="${organization.id}">
                </label></td>
                <td>${organization.name}</td>
                <td>${organization.description}</td>
                <td>${organization.website}</td>
                <td>${organization.type}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<div class="mainPagePagination">
    <c:set var="start" value="${requestScope.start_organization_number}"/>
    <c:set var="step" value="${requestScope.organizations_quantity}"/>
    <c:set var="count" value="${requestScope.organizations_count}"/>
    <c:set var="size" value="${fn:length(requestScope.organization_list)}"/>
    <c:set var="maxPage"/>
    <c:choose>
        <c:when test="${count / step > 1}">
            <fmt:parseNumber var="maxPage" type="number" value="${count / step + 1}"/>
        </c:when>
        <c:otherwise>
            <c:set var="maxPage" value="${1}"/>
        </c:otherwise>
    </c:choose>

    <div id="pageInfo">
        <c:choose>
            <c:when test="${size != 0}">
                ${start + 1}
            </c:when>
            <c:otherwise>
                ${0}
            </c:otherwise>
        </c:choose>
        ${" - "}${start + size}${" "}
        из
        ${" "}${count}</div>
    ${"  "}<br>
    Страница${" "}
    <ul class="pagination text-center">
        <c:if test="${start != 0}">
            <li class="page-item">
                <div class="page-link"><a
                        href="${pageContext.request.contextPath}/controller?command=fill_content&start_organization_number=${start - step}&organizations_quantity=${step}">
                    Предыдущая</a></div>
            </li>
        </c:if>
        <c:forEach var="i" begin="0" end="4">
            <c:if test="${((start + step) / step + i - 2) > 0 && ((start + step) / step + i - 2) <= maxPage}">
                <c:choose>
                    <c:when test="${i != 2}">
                        <fmt:parseNumber var="page" type="number" value="${(start + step) / step + i - 2}"/>
                        <li class="page-item">
                            <div class="page-link">
                                <a href="${pageContext.request.contextPath}/controller?command=fill_content&start_organization_number=${start + step * (i - 2)}&organizations_quantity=${step}">
                                        ${page}</a>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <fmt:parseNumber var="presentPage" type="number" value="${(start + step) / step}"/>
                        <li class="page-item disabled">
                            <div class="page-link" style="color: black">
                                    ${presentPage}${" "}
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
        <c:if test="${(count - start) gt step}">
            <li class="page-item">
                <div class="page-link"><a
                        href="${pageContext.request.contextPath}/controller?command=fill_content&start_organization_number=${start + step}&organizations_quantity=${step}">
                    Следующая</a></div>
            </li>
        </c:if>
    </ul>
    <div class="rowNumber">
        Показывать${" "}
        <ul class="pagination text-center">
            <c:choose>
                <c:when test="${step == 10}">
                    <li class="page-item disabled">
                        <div class="page-link" style="color: black">
                            10${" "}
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <div class="page-link">
                            <a href="${pageContext.request.contextPath}/controller?command=fill_content&start_organization_number=0&organizations_quantity=10">10</a>${" "}
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${step == 20}">
                    <li class="page-item disabled">
                        <div class="page-link" style="color: black">
                            20${" "}
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <div class="page-link">
                            <a href="${pageContext.request.contextPath}/controller?command=fill_content&start_organization_number=0&organizations_quantity=20">20</a>${" "}
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
</body>
</html>
