<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
 
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Welcome Page - Struts 2 - Login Application</title>
</head>
 
<body>
	<h2>Congratulations, <s:property value="#session.username" />!</h2>
	Welcome to Struts 2 world.
</body>
</html>