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
		        <th><s:text name="label.subjectStudents.dni"/></th>
		        <th><s:text name="label.subjectStudents.nombre"/></th>
		        <th><s:text name="label.subjectStudents.telefono"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		        <td class="nowrap"><s:property value="alumno.dni"/></td>
		        <td class="nowrap"><s:property value="alumno.nombre"/></td>
		        <td class="nowrap"><s:property value="alumno.telefono"/></td>
		    </tr>
		
		</table>
		<br/>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subjectStudent.code"/></th>
		        <th><s:text name="label.subjectStudent.name"/></th>
		        <th><s:text name="label.subjectStudent.credits"/></th>
		        <th><s:text name="label.subjectStudent.professor"/></th>
		        <th><s:text name="label.subjectStudent.studentsNumber"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="codigo"/></td>
		            <td class="nowrap"><s:property value="asignatura.nombre"/></td>
		            <td class="nowrap"><s:property value="asignatura.creditos"/></td>
		            <td class="nowrap"><s:property value="%{asignatura.profesor.getNombre()}"/></td>
		            <td class="nowrap"><s:property value="%{asignatura.alumnos.size()}"/></td>            
		    </tr>
		</table>
		<br/>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.marks.id"/></th>
		        <th><s:text name="label.marks.concepto"/></th>
		        <th><s:text name="label.marks.nota"/></th>
		    </tr>
		    <s:iterator value="listaEvaluaciones" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="asignatura.id"/></td>
		            <td class="nowrap"><s:property value="concepto"/></td>
		            <td class="nowrap"><s:property value="nota"/></td> 
		        </tr>
		    </s:iterator>
		    
		</table>
		
		<s:form action="doListSubjectsAlumno" >
			<s:submit value="%{getText('label.subjectStudents.cancelButton')}" align="center"/>
		</s:form>
		
	</body>
</html>