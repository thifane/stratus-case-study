<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/windowopener.js"></script>

<style type="text/css">
.errors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:412px;
	margin-bottom:8px;
}

.errors li{ 
	list-style: none; 
}
</style>

<title>Register User</title>
</head>
<body>
	 <center>
	<h2>UC02 - Enter Registration Details</h2>
	
		<s:if test="hasActionErrors()">
			<div  class = "errors">
				 <s:actionerror/>
			</div>
		</s:if>
		
	<s:form id="registrationfrm">
		<s:textfield key = "label.emailaddress" name = "emailAddress"/>
		<s:password key = "label.password" name = "password"/>
		<s:password key = "label.confirmpassword" name = "confirmPassword"/>
		<s:textfield key = "label.firstname" name = "firstName"/>
		<s:textfield key = "label.lastname" name = "lastName"/>
		<s:textfield key = "label.employeeId" name = "employeeId"/>
	
		<tr><td>People Manager:</td><td  colspan = "2"><s:textfield key = "label.peoplemanager" name = "peopleManager" theme = "simple"/><input type = "button" value = "LookUp" onclick = "openwindow('registrationfrm')"/></td></tr>
		<s:select list="projects" multiple = "true" key = "label.projects" name = "selectedProjects" size="5" cssStyle="width: 300px;"></s:select>		
		<tr>
		    <td colspan="2" align = "center">
		      <s:reset value="Reset" theme="simple"/>
		      <s:submit value="Register"  action = "registerUser" theme="simple"/>
		    </td>
		  </tr>
		  <s:hidden name="submitted" value = "true"/>
	</s:form>
	</center>
</body>
</html>