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
<title>Add issue</title>

<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<c:url value="/resources/js/crbuilds.js"/>"></script>
<script type="text/javascript">
			<!--
			$( document ).ready(function() {
				var projects = document.getElementById('projectSelect');
				var rootUrl = '<c:url value = '/Issues/Builds/'/>';
				var index = rootUrl.lastIndexOf(';');
				if (index !== -1) {
					var prefix = rootUrl.slice(0, index);
					var suffix = rootUrl.slice(index);
				} else {
					prefix = rootUrl;
					suffix = '';
				}
				createBuilds(projects.value, prefix, suffix);
				$(projects).bind('change', function (event) {
					createBuilds(event.target.value, prefix, suffix);
				});
				$('#statusSelect').bind('change', function (event) {
					changeStatus(event.target.value);
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
			<sf:form name="addissue" method="post" modelAttribute="issue">
				<table>
					<tr>
						<td><s:message code="table.summary"/>:</td>
						<td>
							<sf:input path="summary" />
							<sf:errors path="summary" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><s:message code="table.description"/>:</td>
						<td>
							<sf:input path="description" />
							<sf:errors path="description" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td><s:message code="table.status"/>:</td>
						<td>
							<sf:select path="status.id" id="statusSelect">
								<sf:options items="${statusList}" itemValue="id" itemLabel="name"/>
							</sf:select>
						</td>
					</tr>
					<tr>
						<td><s:message code="table.type"/>:</td>
						<td>
							<sf:select path="type.id" >
								<sf:options items="${typeList}" itemValue="id" itemLabel="name"/>
							</sf:select>
						</td>
					</tr>
					<tr>
						<td><s:message code="table.priority"/>:</td>
						<td>
							<sf:select path="priority.id" >
								<sf:options items="${priorityList}" itemValue="id" itemLabel="name"/>
							</sf:select>
						</td>
					</tr>
					<tr>
						<td><s:message code="table.project"/>:</td>
						<td>
							<sf:select path="project.id" id="projectSelect">
								<c:forEach items="${projectList}" var="project">
									<option value="${project.id}" >${project.name}</option>
								</c:forEach>
							</sf:select>
						</td>
					</tr>
					<tr>
						<td><s:message code="table.build"/>:</td>
						<td>
							<sf:select path="buildFound.id" id="buildSelect">
							</sf:select>
						</td>
					</tr>
					<tr>
						<td><s:message code="table.assignee"/>:</td>
						<td>
							<sf:select path="assignee.id"  id="assigneeSelect" disabled='true'>
								<sf:options items="${userList}" itemValue="id" itemLabel="emailAddress"/>
							</sf:select>
						</td>
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