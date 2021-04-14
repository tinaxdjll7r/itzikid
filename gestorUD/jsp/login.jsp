<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<s:form action="doLogin" method="POST">
			<tr>
				<td colspan="2">Login</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:actionerror />
				</td>
			</tr>
			<s:textfield name="username" label="%{getText('label.login.name')}"/>
			<s:password name="password" label="%{getText('label.login.password')}"/>
			<s:select name="type" list="{getText('label.login.alumno'), getText('label.login.profesor')}" label="%{getText('label.login.select')}" />
			<s:submit value="%{getText('label.login.button')}" align="center"/>
		</s:form>
	</body>

</html>

