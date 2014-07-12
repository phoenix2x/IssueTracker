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
<title>Search issues</title>
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
			<sf:form name="addissue" method="post" modelAttribute="searchIssue">
				<table>
					<tr>
						<td><s:message code="table.createdBy" />:</td>
						<td><sf:select path="createdByEmail" id="assigneeSelect">
								<option value="" />
								<sf:options items="${userList}" itemValue="emailAddress"
									itemLabel="emailAddress" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.modifiedBy" />:</td>
						<td><sf:select path="modifiedByEmail" id="assigneeSelect">
								<option value="" />
								<sf:options items="${userList}" itemValue="emailAddress"
									itemLabel="emailAddress" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.summary" />:</td>
						<td><sf:input path="summary" /> <sf:errors path="summary"
								cssClass="error" /></td>
					</tr>
					<tr>
						<td><s:message code="table.description" />:</td>
						<td><sf:input path="description" /> <sf:errors
								path="description" cssClass="error" /></td>
					</tr>
					<tr>
						<td><s:message code="table.status" />:</td>
						<td><sf:select path="status" id="statusSelect">
								<option value="" />
								<sf:options items="${statusList}" itemValue="name"
									itemLabel="name" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.resolution" />:</td>
						<td><sf:select path="resolution" id="resolutionSelect">
								<option value="" />
								<sf:options items="${resolutionList}" itemValue="name"
									itemLabel="name" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.type" />:</td>
						<td><sf:select path="type">
								<option value="" />
								<sf:options items="${typeList}" itemValue="name"
									itemLabel="name" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.priority" />:</td>
						<td><sf:select path="priority">
								<option value="" />
								<sf:options items="${priorityList}" itemValue="name"
									itemLabel="name" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.project" />:</td>
						<td><sf:select path="projectName" id="projectSelect">
								<option value="" />
								<sf:options items="${projectList}" itemValue="name"
									itemLabel="name" />
							</sf:select></td>
					</tr>
					<tr>
						<td><s:message code="table.assignee" />:</td>
						<td><sf:select path="assigneeEmail" id="assigneeSelect">
								<option value="" />
								<sf:options items="${userList}" itemValue="emailAddress"
									itemLabel="emailAddress" />
							</sf:select></td>
					</tr>
				</table>
				<input type="submit" value="<s:message code="button.search"/>"
					class="issueformbutton">
			</sf:form>
			<c:choose>
				<c:when test="${empty issueList}">
					<s:message code="message.noissues" />
				</c:when>
				<c:otherwise>
					<table id="myTable" class="tablesorter">
						<thead>
							<tr>
								<th class="header firstColumn"><s:message code="table.id" /></th>
								<th class="header column"><s:message code="table.priority" /></th>
								<th class="header column"><s:message code="table.assignee" /></th>
								<th class="header column"><s:message code="table.type" /></th>
								<th class="header column"><s:message code="table.status" /></th>
								<th class="header column"><s:message code="table.summary" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${issueList}" var="issue">
								<tr>
									<td><a
										href="
									<c:url value="/Issues/${issue.id}">
									</c:url>"><c:out
												value="${issue.id}" /></a></td>
									<td class="pr${issue.priority.name}"><c:out
											value="${issue.priority.name}" /></td>
									<td><c:out value="${issue.assignee.emailAddress}" /></td>
									<td><c:out value="${issue.type.name}" /></td>
									<td><c:out value="${issue.status.name}" /></td>
									<td><c:out value="${issue.summary}" /></td>
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