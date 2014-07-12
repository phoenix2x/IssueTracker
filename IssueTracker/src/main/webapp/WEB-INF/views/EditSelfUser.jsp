<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit profile</title>
<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type='text/javascript'>
<!--
	$(document).ready(function(){
		$(".userformbutton").click(function(){
			if ($("#pass").get(0).value != $("#passConfirm").get(0).value) {
		        alert('passwords dont match');
		        return false;
		    } 
		});
	});
-->
</script>
</head>
<body>
	<div id="page">
		<c:import url="<%=JSPConstants.HEADER_JSP%>"/>
		<div id="head">
			<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>"/>
		</div>
		<div id="body">
			<sf:form name="editUser" method="post" modelAttribute="user">
				<table>
					<tr>
						<td><s:message code="table.emailaddress"/>:</td>
						<td>
							<sf:input path="emailAddress" />
							<sf:errors path="emailAddress" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><s:message code="table.firstname"/>:</td>
						<td>
							<sf:input path="firstName" />
							<sf:errors path="firstName" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><s:message code="table.lastname"/>:</td>
						<td>
							<sf:input path="lastName" />
							<sf:errors path="lastName" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><s:message code="label.password"/>:</td>
						<td>
							<sf:password path="password" id="pass"/>
							<sf:errors path="password" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><s:message code="label.passwordConfirm"/>:</td>
						<td>
							<input type="password" id="passConfirm">
						</td>
					</tr>
				</table>
				<input type="submit" value="<s:message code="button.edit"/>" class="userformbutton issueformbutton">
			</sf:form>
		</div>
			<div id="substrate-footer"></div>
		</div>
		<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
		</div>
	</body>
</html>