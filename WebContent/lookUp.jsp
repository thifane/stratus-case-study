<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/slimpicker.css">
<script src="js/slimpicker.js"></script>
<script src="js/windowopener.js"></script>
<title>Look Up</title>
</head>
<body>
	<s:set name="Framework" value="frmName"/>
	<s:form>
		<s:textfield name = "empFirstName" key = "label.firstname"/>
		<s:textfield name = "empLastName" key = "label.lastname"/>	
		<s:textfield name = "email" key = "label.emailaddress"/>
		<s:submit action="search" cssStyle="text-align;center;"/>	
		<s:hidden name = "frmName" value = "%{frmName}"/>	
	<table>
			  <tr>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Select</th>
			   </tr>
			   <s:iterator value = "empVOLst" var="empVo" >
			   		<tr>
			   			<td><s:property value = "#empVo.firstName" /></td>
			   			<td><s:property value = "#empVo.lastName" /></td>
			   			<td><s:property value = "#empVo.emailID" /></td>
			   			<s:url action="lookup" var="urlTag" >
						    <s:param name="employeeid"><s:property value = "#empVo.employeeId" /></s:param>
						</s:url>					
			   			<td>				   					
			   					<s:if test="frmName=='registrationfrm'">			   					
									<s:a href="%{urlTag}" name = "%{urlTag}" onclick = "changeparentFrmRegistration(this);">Select</s:a>
								</s:if>
								
								<s:elseif test="frmName=='enterRegistationFrm'">
								    <s:a href="%{urlTag}" name = "%{urlTag}" onclick = "changeparentFrmAchvment(this);">Select</s:a>								    
								</s:elseif>			   								   						
			   			</td>
			   		</tr>
			   </s:iterator>
		</table>
		</s:form>
</body>
</html>
