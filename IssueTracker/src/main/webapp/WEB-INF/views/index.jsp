<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Issues</title>
</head>
<body>
<c:import url="<%=JSPConstants.LOGIN_MENU_JSP %>" ></c:import>
<hr>
<h2><c:out value="${message}" /></h2>
<c:choose>
	<c:when test="${empty requestScope.issues}">
		No issues found.
	</c:when>
	<c:otherwise>
		<table border="1">
			<tr>
				<td>ID</td>
				<td>Priority</td>
				<td>Assigny</td>
				<td>Type</td>
				<td>Status</td>
				<td>Summary</td>
			</tr>
			<c:forEach items="${requestScope.issues}" var="issue">
				<tr>
					<td><c:out value="${issue.id}"></c:out></td>
					<td><c:out value="${issue.priority}"></c:out></td>
					<td><c:out value="${issue.assignee.firstName}"></c:out></td>
					<td><c:out value="${issue.type}"></c:out></td>
					<td><c:out value="${issue.status}"></c:out></td>
					<td><c:out value="${issue.summary}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

</body>
</html>