<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.tittle"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="label.subjectStudents.marksStudentsBy"/> " <s:property value="profesor.nombre"/>"
		    <s:text name="label.subjectStudents.subject"/>" <s:property value="subjectName"/>"
		</h1>
		<br/>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subjectStudents.dni"/></th>
		        <th><s:text name="label.subjectStudents.nombre"/></th>
		        <th><s:text name="label.subjectStudents.telefono"/></th>
		        <th><s:text name="label.subjectStudents.nota"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="listaAlumnos" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="dni"/></td>
		            <td class="nowrap"><s:property value="nombre"/></td>
		            <td class="nowrap"><s:property value="telefono"/></td>
		            <td class="nowrap">
		            	<s:url id="addMark" action="doListSubjects!addMark">
		           		<s:param name="studentDni" value="%{dni}" />
		           		</s:url>
						<s:a href="%{addMark}"><s:text name="label.subjectStudents.addMark"/></s:a>
					</td>
		        </tr>
		    </s:iterator>
		</table>
		<s:form action="doListSubjects" method="backListSubjects">
			<s:submit value="%{getText('label.subjectStudents.cancelButton')}" align="center"/>
		</s:form>
	</body>
</html>