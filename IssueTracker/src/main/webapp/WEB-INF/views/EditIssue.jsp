<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit issue</title>
</head>
<body>
	<c:import url="<%=JSPConstants.LOGIN_MENU_JSP %>" ></c:import>
	<hr>
	<h2><c:out value="${message}"></c:out></h2>
	<form name="addissue" method="post" action="<c:url value="<%=Constants.ADD_ISSUE_ACTION_URL %>"/>">
		Summary:
		<input type="text" name="<%=JSPConstants.SUMMARY%>">
		<br>
		Description:
		<input type="text" name="<%=JSPConstants.DESCRIPTION%>">
		<br>
		Status:
		<select name="<%=JSPConstants.STATUS%>">
			<c:forEach items="${requestScope.statuses}" var="status">
				<option value="${status.id}">${status.name}</option>
			</c:forEach>
		</select>
		<br>
		Type:
		<select name="<%=JSPConstants.TYPE%>">
			<c:forEach items="${requestScope.types}" var="type">
				<option>${type}</option>
			</c:forEach>
		</select>
		<br>
		Priority:
		<select name="<%=JSPConstants.PRIORITY%>">
			<c:forEach items="${requestScope.priorities}" var="priority">
				<option>${priority}</option>
			</c:forEach>
		</select>
		<br>
		Project:
		<select name="<%=JSPConstants.PROJECT%>" onchange="createBuilds(this)">
			<c:forEach items="${requestScope.projects}" var="project">
				<option value="${project.id}">${project.name}</option>
			</c:forEach>
		</select>
		<br>
		Build:
		<select name="<%=JSPConstants.BUILD%>" id="buildsList">
			<c:forEach items="${requestScope.projects}" var="project">
				<c:forEach items="${project.builds}" var="build">
					<option>${build}</option>
				</c:forEach>
			</c:forEach>
		</select>
		<br>
		Assignee:
		<select name="<%=JSPConstants.ASSIGNEE%>">
			<option></option>
			<c:forEach items="${requestScope.assignees}" var="assignee">
				<option value="${assignee.id}">${assignee.emailAddress}</option>
			</c:forEach>
		</select>
		<br>
		<input type="hidden" name="<%=JSPConstants.CREATEDBY%>" value="${sessionScope.user.id}">
		<input type="submit" value="Add">
	</form>
	</body>
</html>