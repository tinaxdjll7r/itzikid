<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.tittle"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="label.subject.mark"/>
		</h1>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subjectStudents.dni"/></th>
		        <th><s:text name="label.subjectStudents.nombre"/></th>
		        <th><s:text name="label.subjectStudents.telefono"/></th>
		        <th><s:text name="label.subjectStudents.nota"/></th>
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
		        <th><s:text name="label.lecturerSubjects.code"/></th>
		        <th><s:text name="label.lecturerSubjects.name"/></th>
		        <th><s:text name="label.lecturerSubjects.credits"/></th>
		        <th><s:text name="label.lecturerSubjects.professor"/></th>
		        <th><s:text name="label.lecturerSubjects.numberOfStudents"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
	        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
	            <td class="nowrap"><s:property value="asignatura.codigo"/></td>
	            <td class="nowrap"><s:property value="asignatura.nombre"/></td>
	            <td class="nowrap"><s:property value="asignatura.creditos"/></td>
	            <td class="nowrap"><s:property value="asignatura.profesor.getNombre"/></td>
	            <td class="nowrap"><s:property value="asignatura.alumnos.size"/></td>
	        </tr>
		</table>
		
		<s:form action="doSubjectMarking!doSubmitMark" method="POST">
			<s:hidden name="subjectId" value="%{subjectId}"/>
			<s:hidden name="studentDni" value="%{studentDni}"/>
			<s:textfield name="concepto" label="%{getText('label.subject.concept')}" />
			<s:textfield name="nota" label="%{getText('label.subject.nota')}" />
			<s:submit value="%{getText('label.subject.send')}" align="center"/>
		</s:form>
		
		<s:form action="doListSubjects" >
			<s:submit value="%{getText('label.subjectStudents.cancelButton')}" align="center"/>
		</s:form>
		
	</body>
</html>