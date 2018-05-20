<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 13.05.2018
  Time: 16:01
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
<div class="container-fluid form-group">
    <form class="row" name="filter" method="POST" action="controller">
        <input class="form-control" type="hidden" name="command" value="request_filter"/>
        <div class="col-3">
            <label for="request-firstSelect"><fmt:message key="content.sort.field"/></label>
            <select name="sort_col" id="request-firstSelect" class="form-control ">
                <option value="sort_by_empty_column"></option>
                <option value="sort_by_vac_name"><fmt:message key="content.vacancy.name"/></option>
                <option value="sort_by_user_name"><fmt:message key="content.aspirant.email"/></option>
            </select>
        </div>
        <div class="col-3">
            <label for="request-secondSelect"><fmt:message key="content.sort.type"/></label>
            <select name="sort_type" id="request-secondSelect" class="form-control ">
                <option value="empty"></option>
                <option value="decrease"><fmt:message key="content.sort.decrease"/></option>
                <option value="increase"><fmt:message key="content.sort.increase"/></option>
            </select>
        </div>

        <div class="col-5">
            <label for="request-searchInput"><fmt:message key="content.search"/></label>
            <input class="form-control " id="request-searchInput" type="text" name="search_field"
                   placeholder="<fmt:message key="content.search"/>"/>
        </div>
        <div class="col-1">
            <label for="request-searchButton">&#160;</label>
            <input id="request-searchButton" class="btn btn-primary float-left" type="submit"
                   value="<fmt:message key="content.search"/>"/>
        </div>
    </form>
</div>

<table class="table table-hover">
    <tbody id="request-table">
    <tr>
        <th><fmt:message key="content.vacancy.name"/></th>
        <th><fmt:message key="content.aspirant.email"/></th>
        <th><fmt:message key="content.more"/></th>
    </tr>

    <c:forEach items="${requestScope.request_list}" var="request">
        <tr>
            <td>${request.jobVacancy.name}</td>
            <td>${request.user.email}</td>
            <td>
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#request-${request.id}-modal">
                    <fmt:message key="content.view.request"/>
                </button>
                <!-- The Modal -->
                <div class="modal fade" id="request-${request.id}-modal">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">
                                    <fmt:message key="content.modal.header.view.request"/></h4>
                                <button type="button" class="close" data-dismiss="modal">&times;
                                </button>
                            </div>
                            <form name="requestForm" method="POST" action="controller">
                                <input class="form-control" type="hidden" name="request_id"
                                       value="${request.id}"/>
                                <input type="hidden" name="command" value="send_request_answer">
                                <input type="hidden" name="aspirant_email" value="${request.user.email}">
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <h4><fmt:message key="content.resume.text"/></h4>
                                    <p>${request.resume}</p>

                                    <br/><fmt:message key="content.message.theme"/><br/>
                                    <input class="form-control" type="text" name="message_theme"
                                           placeholder="theme*"/>
                                    <label for="request-${request.id}-message"><fmt:message key="content.message.text"/></label>
                                    <textarea class="form-control"
                                              id="request-${request.id}-org-description" name="answer_message"
                                              placeholder="Resume*" required rows="4"
                                              cols="50"></textarea>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <input class="btn btn-primary" type="submit"
                                           value="<fmt:message key="content.button.send.answer"/>"/>

                                    <button type="button" class="btn btn-secondary"
                                            data-dismiss="modal">
                                        <fmt:message key="content.button.cancel"/>
                                    </button>
                                </div>
                            </form>
                            <form name="closeForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="close_request">
                                <input type="hidden" name="request_id" value="${request.id}">
                                <input class="btn btn-primary" type="submit"
                                       value="<fmt:message key="content.button.close.request"/>"/>
                            </form>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<ul class="pagination text-center">
    <li class="page-item">
        <button class="page-link disabled" disabled><fmt:message key="content.next.page"/></button>
    </li>
    <li class="page-item">
        <button class="page-link"><fmt:message key="content.previous.page"/></button>
    </li>
</ul>
</body>
</html>
