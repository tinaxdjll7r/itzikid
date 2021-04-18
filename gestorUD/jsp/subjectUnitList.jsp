<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	    <title><s:text name="application.tittle"/></title>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="label.subjects.listUnits"/> <s:property value="asignatura.nombre"/> </h1>
		<br/>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subjects.unitId"/></th>
		        <th><s:text name="label.subjects.unitTittle"/></th>
		        <th><s:text name="label.subjects.unitContent"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="listaUnidades" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="id"/></td>
		            <td class="nowrap"><s:property value="titulo"/></td>
		            <td class="nowrap"><s:property value="contenido"/></td>
		        </tr>
		    </s:iterator>
		</table>
		
	</body>
</html>