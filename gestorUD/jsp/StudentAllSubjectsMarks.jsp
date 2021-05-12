<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.tittle"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="label.marks.tittle"/></h1>
		<br/>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.marks.asignatura"/></th>
		        <th><s:text name="label.marks.id"/></th>
		        <th><s:text name="label.marks.concepto"/></th>
		        <th><s:text name="label.marks.nota"/></th>
		    </tr>
		    <tr>
		    
		</table>
		
	</body>
</html>