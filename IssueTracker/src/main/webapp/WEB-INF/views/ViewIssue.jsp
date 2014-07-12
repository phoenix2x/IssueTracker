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
<title>View issue</title>
<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<c:url value="/resources/js/crbuilds.js"/>"></script>
</head>
	<body>
		<div id="page">
			<c:import url="<%=JSPConstants.HEADER_JSP%>"/>
			<div id="head">
				<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>"/>
			</div>
			<div id="body">
				<table>
					<tr>
						<td><s:message code="table.id"/>:</td>
						<td>${issue.id}</td>
					</tr>
					<tr>
						<td><s:message code="table.createDate"/>:</td>
						<td>${issue.createDate}</td>
					</tr>
					<tr>
						<td><s:message code="table.createdBy"/>:</td>
						<td>${issue.createdBy.emailAddress}</td>
					</tr>
					<tr>
						<td><s:message code="table.modifyDate"/>:</td>
						<td>${issue.modifyDate}</td>
					</tr>
					<tr>
						<td><s:message code="table.modifiedBy"/>:</td>
						<td>${issue.modifiedBy.emailAddress}</td>
					</tr>
					<tr>
						<td><s:message code="table.summary"/>:</td>
						<td>${issue.summary}</td>
					</tr>
					<tr>
						<td><s:message code="table.description"/>:</td>
						<td>${issue.description}</td>
					</tr>
					<tr>
						<td><s:message code="table.status"/>:</td>
						<td>
							${issue.status.name}
						</td>
					</tr>
					<tr>
						<td><s:message code="table.resolution"/>:</td>
						<td>
							${issue.resolution.name}
						</td>
					</tr>
					<tr>
						<td><s:message code="table.type"/>:</td>
						<td>
							${issue.type.name}
						</td>
					</tr>
					<tr>
						<td><s:message code="table.priority"/>:</td>
						<td>
							${issue.priority.name}
						</td>
					</tr>
					<tr>
						<td><s:message code="table.project"/>:</td>
						<td>
							${issue.project.name}
						</td>
					</tr>
					<tr>
						<td><s:message code="table.build"/>:</td>
						<td>
							${issue.buildFound.name}
						</td>
					</tr>
					<tr>
						<td><s:message code="table.assignee"/>:</td>
						<td>
							${issue.assignee.emailAddress}
						</td>
					</tr>
				</table>
				<table class="commentTable">
					<thead>
						<tr>
							<th><s:message code="table.createdBy"/></th>
							<th><s:message code="table.createDate"/></th>
							<th><s:message code="table.comment"/></th>
						</tr>
					</thead>
					<c:forEach var="comment" items="${issue.comments}">
						<tr>
							<td>${comment.addedBy.emailAddress}</td>
							<td>${comment.addDate}</td>
							<td>${comment.comment}</td>
						</tr>
					</c:forEach>
				</table>
				<table class="fileTable">
					<thead>
						<tr>
							<th><s:message code="table.createdBy"/></th>
							<th><s:message code="table.createDate"/></th>
							<th><s:message code="table.file"/></th>
						</tr>
					</thead>
					<c:forEach var="attachment" items="${issue.attachments}">
						<tr>
							<td>${attachment.addedBy.emailAddress}</td>
							<td>${attachment.addDate}</td>
							<td>
								<a href="<c:url value='${issue.id}/${attachment.id}'/>">${attachment.fileName}
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="substrate-footer"></div>
		</div>
		<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
		</div>
	</body>
</html>