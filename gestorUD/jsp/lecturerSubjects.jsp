<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.tittle"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
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
		            <td class="nowrap">
		           		<s:url id="unitList" action="doSubject!ShowSubjectUnits">
		           		<s:param name="subjectId" value="%{id}" />
		           		</s:url>
						<s:a href="%{unitList}"><s:text name="%{unidades.size()}"/></s:a>
					</td>
		            <td class="nowrap"><s:property value="%{alumnos.size()}"/></td>
		            <td class="nowrap">
		           		<s:url id="studentList" action="doListSubjects!ShowSubjectStudents">
		           		<s:param name="subjectId" value="%{id}" />
		           		</s:url>
						<s:a href="%{studentList}"><s:text name="label.lecturerSubjects.students"/></s:a>
					</td>
		        </tr>
		    </s:iterator>
		</table>
		
	</body>
</html>