<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit issue</title>
<link href="css/mystyle.css" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="js/crbuilds.js"></script>
</head>
	<body>
		<div id="page">
			<c:import url="<%=JSPConstants.HEADER_JSP%>"/>
			<div id="head">
				<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>"/>
			</div>
			<div id="body">
				<c:set var="guestUserId" value="<%=Constants.GUEST_USER.getId()%>"/>
				<h2><c:out value="${message}"></c:out></h2>
				<form name="editissue" method="post" action="<c:url value="<%=Constants.EDIT_ISSUE_ACTION_URL %>"/>">
					<input type="hidden" name="<%=JSPConstants.ISSUE_ID%>" value="${issue.id}">
					<table>
						<tr>
							<td>ID:</td>
							<td>${issue.id}</td>
						</tr>
						<tr>
							<td>CreateDate:</td>
							<td>${issue.createDate}</td>
						</tr>
						<tr>
							<td>CreatedBy:</td>
							<td>${issue.createdBy.emailAddress}</td>
						</tr>
						<c:if test="${not empty issue.modifyDate}">
							<tr>
								<td>ModifyDate:</td>
								<td>${issue.modifyDate}</td>
							</tr>
						</c:if>
						<c:if test="${not empty issue.modifiedBy}">
							<tr>
								<td>ModifiedBy:</td>
								<td>${issue.modifiedBy.emailAddress}</td>
							</tr>
						</c:if>
						<tr>
							<td>Summary:</td>
							<td><input type="text" name="<%=JSPConstants.SUMMARY%>" id="summary" value="<c:out value="${issue.summary}"/>" required <c:if test="${guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>></td>
						</tr>
						<tr>
							<td>Description:</td>
							<td><input type="text" name="<%=JSPConstants.DESCRIPTION%>" id="description" value="<c:out value="${issue.description}"/>" required <c:if test="${guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>></td>
						</tr>
						<tr>
							<td>Status:</td>
							<td>
								<select name="<%=JSPConstants.STATUS%>" onchange="changeStatus(this.value)" id="statusSelect" <c:if test="${guestUserId eq sessionScope.user.id}">disabled</c:if>>
									<c:forEach items="${requestScope.statuses}" var="statusEntry">
										<option value="${statusEntry.key}" <c:if test="${statusEntry.key eq issue.status.id}">selected</c:if>>${statusEntry.value.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Resolution:</td>
							<td>
								<select name="<%=JSPConstants.RESOLUTION%>" id="resolutionSelect" <c:if test="${(issue.status.id ne '4' && issue.status.id ne '5') || guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>>
									<c:forEach items="${requestScope.resolutions}" var="resolution">
										<option <c:if test="${resolution eq issue.resolution}">selected</c:if>>${resolution}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Type:</td>
							<td>
								<select name="<%=JSPConstants.TYPE%>" id="typeSelect" <c:if test="${guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>>
									<c:forEach items="${requestScope.types}" var="type">
										<option <c:if test="${type eq issue.type}">selected</c:if>>${type}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Priority:</td>
							<td>
								<select name="<%=JSPConstants.PRIORITY%>" id="prioritySelect" <c:if test="${guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>>
									<c:forEach items="${requestScope.priorities}" var="priority">
										<option <c:if test="${priority eq issue.priority}">selected</c:if>>${priority}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Project:</td>
							<td>
								<select name="<%=JSPConstants.PROJECT%>" id="projectSelect" onchange="createBuilds(this.value, '<c:url value='<%=Constants.BUILDS_AJAX_SERVLET_URL %>'/>')" <c:if test="${guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>>
									<c:forEach items="${requestScope.projects}" var="project">
										<option value="${project.id}" <c:if test="${project.id eq issue.project.id}">selected</c:if>>${project.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Build:</td>
							<td>
								<select name="<%=JSPConstants.BUILD%>" id="buildSelect" <c:if test="${guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>>
									<c:forEach items="${issue.project.builds}" var="build">
										<option value="${build.id}"<c:if test="${issue.buildFound eq build}" >selected</c:if>>${build.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Assignee:</td>
							<td>
								<select name="<%=JSPConstants.ASSIGNEE %>" id="assigneeSelect" <c:if test="${issue.status.id eq '1' || guestUserId eq sessionScope.user.id || issue.status.id eq 5}">disabled</c:if>>
									<c:forEach items="${requestScope.assignees}" var="assignee">
										<option value="${assignee.id}" <c:if test="${assignee.id eq issue.assignee.id}">selected</c:if>>${assignee.emailAddress}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<c:if test="${guestUserId ne sessionScope.user.id}"><input type="submit" value="Update" class="issueformbutton"></c:if>
				</form>
			</div>
			<div id="substrate-footer"></div>
		</div>
		<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
		</div>
	</body>
</html>