<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@page import="org.example.issuetracker.model.enums.UserRoles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<sec:authorize access="isAnonymous()">
		<div class="left">
			<c:if test="${not empty param.error}">
				<font color="red"> <s:message code="label.loginerror" />
				: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
			</c:if>
			<form method="POST" action="<c:url value="/j_spring_security_check" />">
			<table>
				<tr>
					<td align="right"><s:message code="label.login" /></td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td align="right"><s:message code="label.password" /></td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td align="right"><s:message code="label.remember" /></td>
					<td><input type="checkbox" name="_spring_security_remember_me" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="Login" />
					<input type="reset" value="Reset" /></td>
				</tr>
			</table>
			</form>
			<button><s:message code="button.search"/></button>
		</div>
		<div class="right">
		</div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
		<div class="left">
			<br>
			<a href="#"><s:message code="href.editProfile"/></a>
			<button onclick="location.href='<c:url value="<%=Constants.ADD_ISSUE_URL %>"/>'"><s:message code="button.submitIssue"/></button>
			<button><s:message code="button.search"/></button>
			<br>
			<button onclick="location.href='<c:url value="<%=Constants.LOGOUT_URL %>"/>'"><s:message code="button.logout"/></button>
		</div>
		<div class="right">
			<sec:authorize access="hasAnyAuthority('ADMINISTRATOR')">
				<a href="#"><s:message code="href.projects"/></a>
				<a href="#"><s:message code="href.statuses"/></a>
				<a href="#"><s:message code="href.resolutions"/></a>
				<a href="#"><s:message code="href.priorities"/></a>
				<a href="#"><s:message code="href.types"/></a>
				<br>
				<a href="<c:url value="/Admin/Projects/Add"/>"><s:message code="href.addProject"/></a>
				<a href="<c:url value="/Admin/Resolutions/Add"/>"><s:message code="href.addResolution"/></a>
				<a href="<c:url value="/Admin/Priorities/Add"/>"><s:message code="href.addPriority"/></a>
				<a href="<c:url value="/Admin/Types/Add"/>"><s:message code="href.addType"/></a>
				<br>
				<a href="#"><s:message code="href.searchUser"/></a>
				<a href="#"><s:message code="href.addUser"/></a>
			</sec:authorize>
		</div>
</sec:authorize>