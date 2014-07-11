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
<title>Edit project</title>

<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="page">
		<c:import url="<%=JSPConstants.HEADER_JSP%>"/>
		<div id="head">
			<c:import url="<%=JSPConstants.LOGIN_MENU_JSP%>"/>
		</div>
		<div id="body">
			<sf:form name="editProject" method="post" modelAttribute="project">
				<table>
					<tr>
						<td><s:message code="table.project"/>:</td>
						<td>
							<sf:input path="name" />
							<sf:errors path="name" cssClass="error" />
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
						<td><s:message code="table.build"/>:</td>
						<td>
						<!-- 
							<input type="text" name="builds[0].name">
						
						 	<sf:input path="builds[0].name" />
							<sf:errors path="builds[0]" cssClass="error" />
						-->
							<sf:select path="builds[0].id">
								<sf:options items="${buildList}" itemValue="id" itemLabel="name"/>
							</sf:select>
							
						</td>
					</tr>
					<tr>
						<td><s:message code="table.manager"/>:</td>
						<td>
							<sf:select path="manager.id">
								<sf:options items="${userList}" itemValue="id" itemLabel="emailAddress"/>
							</sf:select>
						</td>
					</tr>
				</table>
				<input type="submit" value="<s:message code="button.edit"/>" class="issueformbutton">
			</sf:form>
			<div id="addBuild">
				<c:url var="addBuildUrl" value='/Admin/Projects/${project.id}/AddBuild'/>
				<sf:form name="addBuild" method="post" modelAttribute="build" action="${addBuildUrl}">
					<table>
						<tr>
							<td><s:message code="table.name"/>:</td>
							<td>
								<sf:input path="name" />
								<sf:errors path="name" cssClass="error" />
							</td>
						</tr>
					</table>
					<input type="submit" value="<s:message code="button.add"/>" class="issueformbutton">
				</sf:form>
			</div>
		</div>
			<div id="substrate-footer"></div>
		</div>
		<div id="footer">
		<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
		</div>
	</body>
</html>