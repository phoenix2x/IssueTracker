<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Add issue</title>
</head>
<body>
<form name="addissue" method="post" action="<c:url value="<%=Constants.ADD_ISSUE_URL %>"/>">
			Summary:
			<input type="text" name="summary">
			<br>
			Description:
			<input type="text" name="description">
			<br>
			Status:
			<select name="status">
				<c:forEach items="${requestScope.statuses}" var="status">
					<option>${status}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Login">
		</form>
</body>
</html>