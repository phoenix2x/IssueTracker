<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Add issue</title>
<link href="resources/css/mystyle.css" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="resources/js/crbuilds.js"></script>
<script type="text/javascript">
			<!--
			$( document ).ready(function() {
				var projects = document.getElementById('projectSelect');
				createBuilds(projects.value, "<c:url value='<%=Constants.BUILDS_AJAX_SERVLET_URL %>'/>");
			});
			-->
</script>
</head>
<body>
	<div id="page">
		header
		<div id="head">
			loginmenu
		</div>
		<div id="body">
			<sf:form name="addissue" method="post" modelAttribute="issue">
				<table>
					<tr>
						<td>Summary:</td>
						<td><sf:input path="summary" /></td>
					</tr>
					<tr>
						<td>Description:</td>
						<td><sf:input path="description" /></td>
					</tr>
					<tr>
						<td>Status:</td>
						<td>
							<sf:select path="status.id" id="statusSelect">
								<sf:options items="${statusList}" itemValue="id" itemLabel="name"/>
							</sf:select>
						</td>
					</tr>
					<tr>
						<td>Type:
							<input type="text" name="test">
						<td>
							<select name="<%=JSPConstants.TYPE%>">
								<c:forEach items="${requestScope.types}" var="type">
									<option>${type}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Priority:</td>
						<td>
							<select name="<%=JSPConstants.PRIORITY%>">
								<c:forEach items="${requestScope.priorities}" var="priority">
									<option>${priority}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Project:</td>
						<td>
							<select name="<%=JSPConstants.PROJECT%>" onchange="createBuilds(this.value, '<c:url value='<%=Constants.BUILDS_AJAX_SERVLET_URL %>'/>')" id="projectSelect">
								<c:forEach items="${requestScope.projects}" var="project">
									<option value="${project.id}">${project.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Build:</td>
						<td>
							<select name="<%=JSPConstants.BUILD%>" id="buildSelect">
							</select>
						</td>
					</tr>
					<tr>
						<td>Assignee:</td>
						<td>
							<select name="<%=JSPConstants.ASSIGNEE%>" id="assigneeSelect" disabled>
								<c:forEach items="${requestScope.assignees}" var="assignee">
									<option value="${assignee.id}">${assignee.emailAddress}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<input type="submit" value="Add" class="issueformbutton">
			</sf:form>
		</div>
			<div id="substrate-footer"></div>
		</div>
		<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
		</div>
	</body>
</html>