<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Search users</title>
<link href="<c:url value="/resources/css/mystyle.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/tablestyle.css"/>"
	rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
	<div id="page">
		<c:import url="<%=JSPConstants.HEADER_JSP%>" />
		<div id="head">
			<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>" />
		</div>
		<div id="body">
			<sf:form name="searchUser" method="post" modelAttribute="user">
				<table>
					<tr>
						<td><s:message code="table.emailaddress" />:</td>
						<td><sf:input path="emailAddress" /> <sf:errors path="emailAddress"
								cssClass="error" /></td>
					</tr>
					<tr>
						<td><s:message code="table.firstname" />:</td>
						<td><sf:input path="firstName" /> <sf:errors
								path="firstName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><s:message code="table.lastname" />:</td>
						<td><sf:input path="lastName" /> <sf:errors
								path="lastName" cssClass="error" /></td>
					</tr>
				</table>
				<input type="submit" value="<s:message code="button.search"/>"
					class="issueformbutton">
			</sf:form>
			<c:choose>
				<c:when test="${empty userList}">
					<s:message code="message.nousers" />
				</c:when>
				<c:otherwise>
					<table id="myTable" class="tablesorter">
						<thead>
							<tr>
								<th class="header firstColumn"><s:message code="table.emailaddress" /></th>
								<th class="header column"><s:message code="table.firstname" /></th>
								<th class="header column"><s:message code="table.lastname" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<td><a
										href="
									<c:url value="/Admin/Users/${user.id}">
									</c:url>"><c:out
												value="${user.emailAddress}" /></a></td>
									<td><c:out value="${user.firstName}" /></td>
									<td><c:out value="${user.lastName}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div id="pagesrefs">
						<c:if test="${pages gt 1}">
							<c:forEach begin="0" end="${pages-1}" var="val">
								<a href="#"><c:out value="${val}" /></a>
							</c:forEach>
						</c:if>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="substrate-footer"></div>
	</div>
	<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP%>" />
	</div>
</body>
</html>