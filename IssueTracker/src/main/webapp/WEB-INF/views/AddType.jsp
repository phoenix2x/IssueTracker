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
<title>Add type</title>

<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="page">
		<s:message code="message.hello"/>&nbsp;
		<security:authentication property="principal.username" />!
		<div id="head">
			<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>"/>
		</div>
		<div id="body">
			<sf:form name="addType" method="post" modelAttribute="type">
				<table>
					<tr>
						<td><s:message code="table.type"/>:</td>
						<td><sf:input path="name" /></td>
					</tr>
				</table>
				<input type="submit" value="<s:message code="button.add"/>" class="issueformbutton">
			</sf:form>
		</div>
			<div id="substrate-footer"></div>
		</div>
		<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
		</div>
	</body>
</html>