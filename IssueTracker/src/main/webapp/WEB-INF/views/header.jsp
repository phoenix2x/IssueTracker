<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class=header>
    <a href="<c:url value="<%=Constants.ISSUES_URL%>"/>">Index</a>
    <br>
	<span>Hello ${sessionScope.user.firstName}</span>
</div> 