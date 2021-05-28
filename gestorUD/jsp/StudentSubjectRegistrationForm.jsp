<%@ page contentType="text/html;charset=UTF-8" language="java" import="iso3.pt.action.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.title"/></title>
	</head>
	<body>
				
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1>
			<s:text name="label.matricular.title"/>
		</h1>
		
		<s:form action="doListSubjectsAlumno!Matricular" method="POST">
		    <s:select name="subjectId" list="listaTodasAsignaturas" listKey="id" listValue="nombre" label="%{getText('label.matricular.asignatura')}" />     
			<s:submit value="%{getText('label.matricular.entregar')}" align="center"/>
		</s:form>
		
		<s:form action="doListSubjectsAlumno" >
			<s:submit value="%{getText('label.subjectStudents.cancelButton')}" align="center"/>
		</s:form>
	
	</body>
</html>