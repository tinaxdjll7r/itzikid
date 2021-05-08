<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="label.application.tittle"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="label.subjectStudent.student"/> <s:property value="alumno.nombre"/> <s:property value="alumno.dni"/> </h1>
		<br/>
		<table>
			<tr>
				<td>
					<s:url id="urlMatricular" action="doFormularioMatricular" escapeAmp="false"/>
					<a href="<s:property value="#urlMatricular"/>"><s:text name="label.subjectStudent.matricular"/></a>
				</td>
				<td>
					<s:url id="urlNotas" action="doShowMarks" escapeAmp="false"/>
					<a href="<s:property value="#urlNotas"/>"><s:text name="label.subjectStudent.showAllMarks"/></a>
				</td>
				<td>
					<s:url id="urlLogout" action="showLogin" escapeAmp="false"/>
					<a href="<s:property value="#urlLogout"/>"><s:text name="label.subjectStudent.logout"/></a>
				</td>
			</tr>
		</table>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subjectStudent.code"/></th>
		        <th><s:text name="label.subjectStudent.name"/></th>
		        <th><s:text name="label.subjectStudent.credits"/></th>
		        <th><s:text name="label.subjectStudent.professor"/></th>
		        <th><s:text name="label.subjectStudent.units"/></th>
		        <th><s:text name="label.subjectStudent.studentsNumber"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		     <s:iterator value="listaAsignaturas" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="codigo"/></td>
		            <td class="nowrap"><s:property value="nombre"/></td>
		            <td class="nowrap"><s:property value="creditos"/></td>
		            <td class="nowrap"><s:property value="%{profesor.getNombre()}"/></td>
		            <td class="nowrap">
		           		<s:url id="unitList" action="doSubject!ShowSubjectUnits">
		           		<s:param name="subjectId" value="%{id}" />
		           		</s:url>
						<s:a href="%{unitList}"><s:text name="%{unidades.size()}"/></s:a>
					</td>
		            <td class="nowrap"><s:property value="%{alumnos.size()}"/></td>
					<td class="nowrap">
						<s:url id="desmatricular" action="doListSubjectsAlumno!Desmatricular">
						</s:url>
						<s:a href="%{desmatricular}"><s:text name="label.subjectStudent.desmatricular"/></s:a>
					</td> 
					<td class="nowrap">
		           		<s:url id="notasAsignatura" action="doShowMarks!ShowMarks">
		           		</s:url>
		           		<s:a href="%{notasAsignatura}"><s:text name="label.subjectStudent.showMarks"/></s:a>
					</td>           
		        </tr>
		    </s:iterator>
		</table>
		
	</body>
</html>