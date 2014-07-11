<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Statuses</title>
<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/tablestyle.css"/>" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<div id="page">
<c:import url="<%=JSPConstants.HEADER_JSP%>"/>
	<div id="head">
	<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>"/>
	</div>
	<div id="body">
		<table id="myTable" class="tablesorter">
			<thead>
				<tr>
					<th><s:message code="table.name"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statusList}" var="status">
					<tr>
						<td>
						<a href="
								<c:url value="/Admin/Statuses/${status.id}">
								</c:url>"><c:out value="${status.name}"/></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="substrate-footer"></div>
	</div>
	<div id="footer">
	<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
	</div>
</body>
</html>