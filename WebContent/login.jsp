<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<title>Login</title>
	</head>
	<body>
		<center>
		<h2>UC01 - Login</h2>
		<s:actionerror />
			<s:form action="login.action" method="post">
				<s:textfield name="emailAddress" key="label.username" size="40" />
				<s:password name="password" key="label.password" size="40" />
				<s:submit method="execute" key="label.login" align="center" style="width: 100px;"/>
			</s:form>
			<s:a href="register"><font size="3">sign up/register</font></s:a>&nbsp;&nbsp;&nbsp;&nbsp;
			<s:a href="your action"><font size="3">Forget Password</font></s:a>
		</center>
	</body>
</html>