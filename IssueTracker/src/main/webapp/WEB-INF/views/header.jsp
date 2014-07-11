<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<div class=header>
	<a href="<c:url value="<%=Constants.ISSUES_URL%>"/>">Index</a>
    <s:message code="message.hello"/>&nbsp;
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.username" />!
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		guest
	</sec:authorize>
</div> 