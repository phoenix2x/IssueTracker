<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@page import="org.example.issuetracker.constants.JSPConstants"%>
<%@page import="org.example.issuetracker.constants.Constants"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Issues</title>
<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/tablestyle.css"/>" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	<!--
	$(document).ready(function() {
		var rootUrl = '<c:url value = "/Issues/"/>';
		var index = rootUrl.lastIndexOf(';');
		var prefix = rootUrl.slice(0, index);
		var suffix = rootUrl.slice(index);
		var sortConfig = {
				orderby: 0,
				order: 1,
				page: 0
		};
		var table = document.getElementById('myTable');
		var getIssues = function() {
	        $.getJSON("<c:url value='/IssuesPaginator'/>",sortConfig , function( data ) {
	        	var tableBody = table.getElementsByTagName('tbody');
	        	$(tableBody).empty();
	        	for (var i = 0; i < data.length; i++) {
	        	    var tr = document.createElement('TR');
	        	    for (var j = 0; j < data[i].length; j++) {
	        	        var td = document.createElement('TD');
	        	        tr.appendChild(td);
	        	    }
	        	    console.log(tr);
	        	    tableBody[0].appendChild(tr);
	        	}
	        	var rows = table.rows;
    			var i = 1;
	    		$.each( data, function( key, val ) {
	    			var cells = rows[i++].children;
	    			var j = 0;
	    			$.each(val, function(key, prop){
		    		 	if (j === 0) {
		    		 		var a = document.createElement('a');
		    		 		a.setAttribute("href", prefix + "/" + prop + suffix);
		    		 		a.innerHTML = prop;
		    		 		cells[j].appendChild(a);
		    		 	} else {
		    		 		if (j === 1) {
		    		 			cells[j].className = 'pr' + prop;
		    		 		}
			    		 	cells[j].innerHTML = prop;
		    		 	};
		    		 	j++;
	    			});
	    		});
	    	});
        };
		var pagesrefs = document.getElementById('pagesrefs').getElementsByTagName('a');
	    for(var i=0,l=pagesrefs.length;i<l;i++){
	    	$(pagesrefs[i]).click(function(e) {
			    var target = e && e.target || window.event.srcElement;
				sortConfig.page = target.innerHTML;
				e.preventDefault() || (e.returnValue = false);
				getIssues();
			});
	    }
		$("#myTable").click(function(e){
		  var target = e && e.target || window.event.srcElement;

	        if (target.tagName != 'TH') return;
        	var ths = table.getElementsByTagName('th');
        	if (sortConfig.order === 0) {
 	        	$(ths[sortConfig.orderby]).removeClass('headerSortUp');
 	        } else {
 	        	$(ths[sortConfig.orderby]).removeClass('headerSortDown');
 	        }
	        if (target.cellIndex === sortConfig.orderby) {
	        	if (sortConfig.order === 0) {
	        		sortConfig.order = 1;
	        	} else {
	        		sortConfig.order = 0;
	        	}
	        }
	        sortConfig.orderby = target.cellIndex;
	        if (sortConfig.order === 0) {
	        	$(ths[sortConfig.orderby]).addClass('headerSortUp');
	        } else {
	        	$(ths[sortConfig.orderby]).addClass('headerSortDown');
	        }
	        getIssues();
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
	<c:choose>
		<c:when test="${empty issueList}">
			<s:message code="message.noissues"/>
		</c:when>
		<c:otherwise>
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th class="header firstColumn"><s:message code="table.id"/></th>
						<th class="header column"><s:message code="table.priority"/></th>
						<th class="header column"><s:message code="table.assignee"/></th>
						<th class="header column"><s:message code="table.type"/></th>
						<th class="header column"><s:message code="table.status"/></th>
						<th class="header column"><s:message code="table.summary"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${issueList}" var="issue">
						<tr>
							<td>
							<a href="
									<c:url value="/Issues/${issue.id}">
									</c:url>"><c:out value="${issue.id}"/></a>
							</td>
							<td class="pr${issue.priority.name}"><c:out value="${issue.priority.name}"/></td>
							<td><c:out value="${issue.assignee.emailAddress}"/></td>
							<td><c:out value="${issue.type.name}"/></td>
							<td><c:out value="${issue.status.name}"/></td>
							<td><c:out value="${issue.summary}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pagesrefs">
				<c:if test="${pages gt 1}">
					<c:forEach begin="0" end="${pages-1}" var="val">
						<a href="#" ><c:out value="${val}"/></a>
					</c:forEach>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>
	<!--  
	<form name="sortForm" method="get" action="<c:url value="<%=Constants.ISSUES_URL %>"/>">
		<input name="<%=Constants.ORDER_BY%>" type="hidden">
		<input name="<%=Constants.ORDER%>" type="hidden">
		<input name="<%=Constants.PAGE%>" type="hidden">
	</form>
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
						<th class="header">ID</th>
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
							<td class="pr${issue.priority}"><c:out value="${issue.priority}"/></td>
							<td><c:out value="${issue.assignee.firstName}"/></td>
							<td><c:out value="${issue.type}"/></td>
							<td><span class="displayNone"><c:out value="${issue.status.id}"/></span><c:out value="${issue.status.name}"/></td>
							<td><c:out value="${issue.summary}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pagesrefs">
			<c:if test="${pages gt 1}">
				<c:forEach begin="0" end="${pages}" var="val">
	   				<a href="#" ><c:out value="${val}"/></a>
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
	-->
	</div>
<div id="substrate-footer"></div>
</div>
<div id="footer">
<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
	</div>
</body>
</html>