<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="guest" scope="page" value="<%=Constants.GUEST_USER%>" ></c:set>
<c:choose>
	<c:when test="${sessionScope.user eq pageScope.guest}">
		<form name="login" method="post" action="<c:url value="<%=Constants.LOGIN_ACTION_URL %>"/>">
			Login:<input type="text" name="emailaddress">
			Password:<input type="password" name="password">
			<br>
			<input type="submit" value="Login">
		</form>
		<button>Search</button>
	</c:when>
	<c:otherwise>
		Hello ${sessionScope.user.firstName} 
		<br>
		<a href="#">Edit profile</a>
		<button onclick="location.href='<c:url value="<%=Constants.ADD_ISSUE_URL %>"/>'">Submit Issue</button>
		<button>Search</button>
		<form name="logout" method="post" action="<c:url value="<%=Constants.LOGOUT_URL %>"/>">
			<input type="submit" value="Logout">
		</form>
	</c:otherwise>
</c:choose>