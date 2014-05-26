<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Issues</title>
<link href="css/mystyle.css" rel="stylesheet" type="text/css" />
<link href="css/tablestyle.css" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="js/jquerytablesorter.js"></script>
<script type="text/javascript">
	<!--
	function a(){
	$(document).ready(function() { 
			$("#myTable").tablesorter({
				sortList: [[0,1]] 
			}); 
		} 
	); 
	}
	$(document).ready(function() { 
		 var table = document.getElementById('myTable');

	      table.onclick = function(e) {
	        var target = e && e.target || window.event.srcElement;

	        if (target.tagName != 'TH') return;

	        // Если TH -- сортируем
	        if ($(target).hasClass("headerSortUp")) {
	        	
	        alert(target.cellIndex);
	        }
	      };
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
	<h2><c:out value="${message}"/></h2>
	<c:choose>
		<c:when test="${empty requestScope.issues}">
			No issues found.
		</c:when>
		<c:otherwise>
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th class="header headerSortUp">ID</th>
						<th class="header">Priority</th>
						<th class="header">Assigny</th>
						<th class="header">Type</th>
						<th class="header">Status</th>
						<th class="header">Summary</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.issues}" var="issue">
						<tr>
							<td>
							<a href="
									<c:url value="<%=Constants.EDIT_ISSUE_URL %>">
										<c:param name="<%=JSPConstants.ISSUE_ID %>" value="${issue.id}"/>
									</c:url>"><c:out value="${issue.id}"/></a>
							</td>
							<td><c:out value="${issue.priority}"/></td>
							<td><c:out value="${issue.assignee.firstName}"/></td>
							<td><c:out value="${issue.type}"/></td>
							<td><span class="displayNone"><c:out value="${issue.status.id}"/></span><c:out value="${issue.status.name}"/></td>
							<td><c:out value="${issue.summary}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagesrefs">
			<c:set var="rowslimit" value="<%=Constants.NUMBER_ISSUES%>"></c:set>
			<c:if test="${rowscount gt rowslimit}">
				<c:forEach begin="0" end="${rowscount / rowslimit}" var="val">
	   				<a href="<c:url value="<%=Constants.ISSUES_URL%>">
						<c:param name="<%=Constants.PAGE%>" value="${val}"/> 
					</c:url>"><c:out value="${val}"/></a>
				</c:forEach>
			</c:if>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
<div id="substrate-footer"></div>
</div>
<div id="footer">
<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
	</div>
</body>
</html>