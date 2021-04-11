<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Struts 2 Login Application!</title>

	    <link href="<s:url value="/css/main.css"/>" rel="stylesheet"
          type="text/css"/>
		  
	</head>
    <body>


    <s:form action="doLoginClientSideValidation" method="POST" validate="true">
			
 <tr>
       <td colspan="2">
           Login
       </td>

   </tr>

			<s:actionerror />
			<s:fielderror />

			<s:textfield name="username" label="Login name"/>
			<s:password name="password" label="Password"/>
    		<s:submit value="Login" align="center"/>
				
	</s:form>

    </body>

</html>

