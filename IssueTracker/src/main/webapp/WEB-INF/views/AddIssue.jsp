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
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
			<!--
			$( document ).ready(function() {
				var projects = document.getElementById('projectSelect');
				createBuilds(projects.value);
			});
			function createBuilds(projectId){
				var url = document.getElementById('hiddenLabel').innerHTML;
				$.getJSON(url, { 'projectId': projectId}, function( data ) {
						$("#buildsSelect").empty();
						$.each( data, function( key, val ) {
						var builds = document.getElementById('buildsSelect');
						$.each( val, function(  key, val ) {
							 var option = document.createElement('option');
							 option.innerHTML = val;
							 builds.appendChild(option);
						});
					});
				});
			}
			function changeStatus(){
				var status = document.getElementById('statusSelect');
				var assignee = document.getElementById('assigneeSelect');
				if(!status || !assignee){
					return;
				}
				if (status.value === '2') {
					assignee.disabled = false;
				} else {
					assignee.disabled = true;
				}
			}
			-->
</script>
</head>
<body>
	<label id="hiddenLabel" style="display: none;"><c:url value='<%=Constants.BUILDS_AJAX_SERVLET_URL %>'/></label>
	<c:import url="<%=JSPConstants.LOGIN_MENU_JSP %>" ></c:import>
	<hr>
	<h2><c:out value="${message}"></c:out></h2>
	<form name="addissue" method="post" action="<c:url value="<%=Constants.ADD_ISSUE_ACTION_URL %>"/>">
		Summary:
		<input type="text" name="<%=JSPConstants.SUMMARY%>">
		<br>
		Description:
		<input type="text" name="<%=JSPConstants.DESCRIPTION%>">
		<br>
		Status:
		<select name="<%=JSPConstants.STATUS%>" onchange="changeStatus()" id="statusSelect">
			<c:forEach items="${requestScope.statuses}" var="status">
				<option value="${status.id}">${status.name}</option>
			</c:forEach>
		</select>
		<br>
		Type:
		<select name="<%=JSPConstants.TYPE%>">
			<c:forEach items="${requestScope.types}" var="type">
				<option>${type}</option>
			</c:forEach>
		</select>
		<br>
		Priority:
		<select name="<%=JSPConstants.PRIORITY%>">
			<c:forEach items="${requestScope.priorities}" var="priority">
				<option>${priority}</option>
			</c:forEach>
		</select>
		<br>
		Project:
		<select name="<%=JSPConstants.PROJECT%>" onchange="createBuilds(this.value)" id="projectSelect">
			<c:forEach items="${requestScope.projects}" var="project">
				<option value="${project.id}">${project.name}</option>
			</c:forEach>
		</select>
		<br>
		Build:
		<select name="<%=JSPConstants.BUILD%>" id="buildsSelect">
		</select>
		<br>
		Assignee:
		<select name="<%=JSPConstants.ASSIGNEE%>" id="assigneeSelect" disabled>
			<c:forEach items="${requestScope.assignees}" var="assignee">
				<option value="${assignee.id}">${assignee.emailAddress}</option>
			</c:forEach>
		</select>
		<br>
		<input type="hidden" name="<%=JSPConstants.CREATEDBY%>" value="${sessionScope.user.id}">
		<input type="submit" value="Add">
	</form>
	</body>
</html>