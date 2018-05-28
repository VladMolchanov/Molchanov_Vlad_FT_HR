<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 13.05.2018
  Time: 15:59
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
<div class="container-fluid form-group">
    <form class="row" name="filter" method="POST" action="controller">
        <input class="form-control" type="hidden" name="command" value="vacancy_filter"/>

        <div class="col-3">
            <label for="vacancy-firstSelect"><fmt:message key="content.sort.field"/></label>
            <select name="sort_col" id="vacancy-firstSelect" class="form-control ">
                <option value="sort_by_empty_column"></option>
                <option value="sort_by_vac_name"><fmt:message key="content.vacancy.name"/></option>
                <option value="sort_by_organization"><fmt:message key="content.org.name"/></option>
            </select>
        </div>
        <div class="col-3">
            <label for="vacancy-secondSelect"><fmt:message key="content.sort.type"/></label>
            <select name="sort_type" id="vacancy-secondSelect" class="form-control ">
                <option value="empty"></option>
                <option value="decrease"><fmt:message key="content.sort.decrease"/></option>
                <option value="increase"><fmt:message key="content.sort.increase"/></option>
            </select>
        </div>

        <div class="col-5">
            <label for="vacancy-searchInput"><fmt:message key="content.search"/></label>
            <input class="form-control " id="vacancy-searchInput" type="text" name="search_field"
                   placeholder="<fmt:message key="content.search"/>" pattern="[-,.?!'()\wА-Яа-я\s]{1,45}"/>
        </div>
        <div class="col-1">
            <label for="vacancy-searchButton">&#160;</label>
            <c:choose>
                <c:when test="${sessionScope.vac_filter_flag == true}">
                    <input id="vacancy-searchButton" class="btn btn-primary float-left" type="submit"
                           value="<fmt:message key="content.reset"/>"/>
                </c:when>
                <c:otherwise>
                    <input id="vacancy-searchButton" class="btn btn-primary float-left" type="submit"
                           value="<fmt:message key="content.search"/>"/>
                </c:otherwise>
            </c:choose>

        </div>
    </form>
</div>

<table class="table table-hover">
    <tbody id="vacancy-table">
    <tr>
        <th><fmt:message key="content.vacancy.name"/></th>
        <th><fmt:message key="content.vacancy.date"/></th>
        <th><fmt:message key="content.org.name"/></th>
        <c:choose>
            <c:when test="${sessionScope.role == 'aspirant' || sessionScope.role == 'admin'}">
                <th><fmt:message key="content.more"/></th>
            </c:when>
            <c:when test="${sessionScope.role == 'director'}">
                <th><fmt:message key="content.delete.column"/></th>
                <th><fmt:message key="content.edit.column"/></th>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </tr>

    <c:forEach items="${requestScope.vacancy_list}" var="vacancy">
        <tr>
            <td>${vacancy.name}</td>
            <td>${vacancy.uploadDate}</td>
            <td>${vacancy.organization.name}</td>
            <c:choose>
                <c:when test="${sessionScope.role == 'aspirant'}">
                    <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#vacancy-${vacancy.id}-modal">
                        <fmt:message key="content.add.request"/>
                    </button>
                    <!-- The Modal -->
                    <div class="modal fade" id="vacancy-${vacancy.id}-modal">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">
                                    <fmt:message key="content.modal.header.request"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;
                                </button>
                            </div>
                            <form name="profileForm" method="POST" action="controller">
                                <input class="form-control" type="hidden" name="vacancy_id"
                                       value="${vacancy.id}"/>
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <h4><fmt:message key="content.vacancy.requirement"/></h4>
                                    <p>${vacancy.requirement}</p>
                                    <a href="${vacancy.organization.website}" target="_blank"><fmt:message
                                            key="content.go.to.website"/></a>
                                    <br>
                                    <input class="form-control" type="hidden" name="command"
                                           value="request_registration"/>
                                    <label for="vacancy-${vacancy.id}-req-resume"><fmt:message
                                            key="content.resume.text"/></label>
                                    <textarea class="form-control"
                                              id="vacancy-${vacancy.id}-req-resume" name="resume"
                                              placeholder="Resume*" required rows="4"
                                              cols="50"></textarea>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <input class="btn btn-primary float-left" type="submit"
                                           value="<fmt:message key="content.button.send"/>"/>
                                </div>
                            </form>

                        </div>
                    </div>
                </c:when>
                <c:when test="${sessionScope.role == 'admin'}">
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#vacancy-${vacancy.id}-modal">
                            <fmt:message key="content.view.vacancy"/>
                        </button>
                        <div class="modal fade" id="vacancy-${vacancy.id}-modal">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">
                                            <fmt:message key="content.modal.header.view.vacancy"/></h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;
                                        </button>
                                    </div>
                                    <input class="form-control" type="hidden" name="command"
                                           value="confirm_vacancy"/>
                                    <input class="form-control" type="hidden" name="vacancy_id"
                                           value="${vacancy.id}"/>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <h4><fmt:message key="content.vacancy.requirement"/></h4>
                                        <p>${vacancy.requirement}</p>
                                        <a href="${vacancy.organization.website}" target="_blank"><fmt:message
                                                key="content.go.to.website"/></a>
                                        <br>
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <form name="confirmForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="confirm_vacancy">
                                            <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                                            <input class="btn btn-primary" type="submit"
                                                   value="<fmt:message key="content.button.add.vacancy"/>"/>
                                        </form>

                                        <form name="deleteForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="delete_vacancy">
                                            <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                                            <input class="btn btn-primary" type="submit"
                                                   value=<fmt:message key="content.button.delete.vacancy"/>/>

                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">
                                                <fmt:message key="content.button.cancel"/>
                                            </button>
                                        </form>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </td>
                </c:when>
                <c:when test="${sessionScope.role == 'director' && sessionScope.user_info.organization.id == vacancy.organization.id}">
                    <td>
                        <form>
                            <input type="hidden" name="command" value="close_vacancy">
                            <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                            <input class="btn btn-primary" type="submit"
                                   value=<fmt:message key="content.button.close.vacancy"/>/>
                        </form>
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#dir_vacancy-${vacancy.id}-modal">
                            <fmt:message key="content.view.vacancy"/>
                        </button>
                        <!-- The Modal -->
                        <div class="modal fade" id="dir_vacancy-${vacancy.id}-modal">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">
                                            <fmt:message key="content.edit.column"/></h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;
                                        </button>
                                    </div>
                                    <form name="loginForm" method="POST" action="controller">
                                        <input type="hidden" name="command" value="edit_vacancy"/>
                                        <input type="hidden" name="vacancy_id" value="${vacancy.id}"/>
                                        <!-- Modal body -->
                                        <div class="modal-body">
                                            <label for="vacancy-org-site"><fmt:message key="content.vacancy.name"/></label>
                                            <input class="form-control" value="${vacancy.name}" id="vacancy-org-site" type="text" name="vacancy_name" title="<fmt:message key="content.input.title.extended.name"/>"
                                                   placeholder="Vacancy name*" pattern="[-,.?!'()\wА-Яа-я\s]{1,45}"/>
                                            <label for="vacancy-org-description"><fmt:message key="content.vacancy.requirement"/></label>
                                            <textarea class="form-control" id="vacancy-org-description" name="vacancy_requirement" placeholder="Requirement*" required
                                                      title="<fmt:message key="content.input.title.text"/>" rows="4" cols="50">${vacancy.requirement}</textarea><br/>
                                        </div>
                                        <!-- Modal footer -->
                                        <div class="modal-footer">
                                            <input class="btn btn-primary" type="submit"
                                                   value="<fmt:message key="content.button.submit"/>"/>

                                            <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">
                                                <fmt:message key="content.button.cancel"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="mainPagePagination">
    <c:set var="start" value="${requestScope.start_vacancy_number}"/>
    <c:set var="step" value="${requestScope.vacancies_quantity}"/>
    <c:set var="count" value="${requestScope.vacancies_count}"/>
    <c:set var="size" value="${fn:length(requestScope.vacancy_list)}"/>
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
                        href="${pageContext.request.contextPath}/controller?command=fill_content&start_vacancy_number=${start - step}&vacancies_quantity=${step}">
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
                                <a href="${pageContext.request.contextPath}/controller?command=fill_content&start_vacancy_number=${start + step * (i - 2)}&vacancies_quantity=${step}">
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
                        href="${pageContext.request.contextPath}/controller?command=fill_content&start_vacancy_number=${start + step}&vacancies_quantity=${step}">
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
                            <a href="${pageContext.request.contextPath}/controller?command=fill_content&start_vacancy_number=0&vacancies_quantity=10">10</a>${" "}
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
                            <a href="${pageContext.request.contextPath}/controller?command=fill_content&start_vacancy_number=0&vacancies_quantity=20">20</a>${" "}
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
</body>
</html>