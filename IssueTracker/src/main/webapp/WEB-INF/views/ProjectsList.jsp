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
<title>Projects</title>
<link href="<c:url value="/resources/css/mystyle.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/tablestyle.css"/>" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	<!--
	$(document).ready(function() {
		var rootUrl = '<c:url value = "/Admin/Projects/"/>';
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
	        $.getJSON("<c:url value='/Admin/Projects/Paginator'/>",sortConfig , function( data ) {
	        	var tableBody = table.getElementsByTagName('tbody');
	        	$(tableBody).empty();
	        	for (var i = 0; i < data.length; i++) {
	        	    var tr = document.createElement('TR');
	        	    for (var j = 0; j < data[i].length - 1; j++) {
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
	    			var a;
	    			$.each(val, function(key, prop){
	    				switch(j) {
	    					case 0: 
	    						a = document.createElement('a');
	    		 				a.setAttribute("href", prefix + "/" + prop + suffix);
	    		 				break;
	    					case 1:
	    						a.innerHTML = prop;
			    		 		cells[j-1].appendChild(a);
			    		 		break;
			    		 	default:
			    		 		cells[j-1].innerHTML = prop;
			    		 		break;
	    				}
	    				
	    				
		    		 	
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
		<table id="myTable" class="tablesorter">
			<thead>
				<tr>
					<th class="header column"><s:message code="table.name"/></th>
					<th class="header column"><s:message code="table.manager"/></th>
					<th class="header column"><s:message code="table.description"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projectList}" var="project">
					<tr>
						<td>
						<a href="
								<c:url value="/Admin/Projects/${project.id}">
								</c:url>"><c:out value="${project.name}"/></a>
						</td>
						<td>
							<c:out value="${project.manager.emailAddress}"/>
						</td>
						<td class="projectDescription">
							<c:out value="${project.description}"/>
						</td>
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
	</div>
	<div id="substrate-footer"></div>
	</div>
	<div id="footer">
	<c:import url="<%=JSPConstants.FOOTER_JSP %>"/>
	</div>
</body>
</html>