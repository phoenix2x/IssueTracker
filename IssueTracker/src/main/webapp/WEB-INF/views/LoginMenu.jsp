<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@page import="org.example.issuetracker.model.enums.UserRoles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="guest" scope="page" value="<%=Constants.GUEST_USER%>"/>
<c:choose>
	<c:when test="${sessionScope.user eq pageScope.guest}">
		<div class="left">
			<form name="login" method="post" action="<c:url value="<%=Constants.LOGIN_ACTION_URL %>"/>">
				<label>Login:<input type="text" name="emailaddress" required></label>
				<label>Password:<input type="password" name="password" required></label>
				<br>
				<input type="submit" value="Login">
			</form>
			<button>Search</button>
		</div>
		<div class="right">
		</div>
	</c:when>
	<c:otherwise>
		<div class="left">
			<br>
			<a href="#">Edit profile</a>
			<button onclick="location.href='<c:url value="<%=Constants.ADD_ISSUE_URL %>"/>'">Submit Issue</button>
			<button>Search</button>
			<form name="logout" method="post" action="<c:url value="<%=Constants.LOGOUT_URL %>"/>">
				<input type="submit" value="Logout">
			</form>
			<c:set var="adminRole" value="<%=UserRoles.ADMINISTRATOR %>"></c:set>
		</div>
		<div class="right">
			<c:if test="${sessionScope.user.userRole eq adminRole}">
				<a href="#">Projects</a>
				<a href="#">Statuses</a>
				<a href="#">Resolutions</a>
				<a href="#">Priorities</a>
				<a href="#">Types</a>
				<br>
				<a href="#">Add project</a>
				<a href="#">Add resolution</a>
				<a href="#">Add priority</a>
				<a href="#">Add type</a>
				<br>
				<a href="#">Search user</a>
				<a href="#">Add user</a>
			</c:if>
		</div>
	</c:otherwise>
</c:choose>