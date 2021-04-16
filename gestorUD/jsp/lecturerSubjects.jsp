<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="label.languagedesigner.subject"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.languagedesigners.title"/></div>
		<h1><s:text name="label.lecturerSubjects.listBy"/> <s:property value="profesor.nombre"/> <s:property value="profesor.dni"/> </h1>
		<br/>
		<table>
			<tr>
				<td>
					<s:url id="urlLogout" action="showLogin" escapeAmp="false"/>
					<a href="<s:property value="#urlLogout"/>"><s:text name="label.lecturerSubjects.logout"/></a>
				</td>
			</tr>
		</table>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.lecturerSubjects.code"/></th>
		        <th><s:text name="label.lecturerSubjects.name"/></th>
		        <th><s:text name="label.lecturerSubjects.credits"/></th>
		        <th><s:text name="label.lecturerSubjects.professor"/></th>
		        <th><s:text name="label.lecturerSubjects.unitsByAsig"/></th>
		        <th><s:text name="label.lecturerSubjects.numberOfStudents"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="listaAsignaturas" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="codigo"/></td>
		            <td class="nowrap"><s:property value="nombre"/></td>
		            <td class="nowrap"><s:property value="creditos"/></td>
		            <td class="nowrap"><s:property value="%{profesor.getNombre()}"/></td>
		            <td class="nowrap"><s:property value="%{unidades.size()}"/></td>
		            <td class="nowrap"><s:property value="%{alumnos.size()}"/></td>
		        </tr>
		    </s:iterator>
		</table>
		
	</body>
</html>