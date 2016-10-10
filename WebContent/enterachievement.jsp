<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" href="css/slimpicker.css" media="screen, projection" />
		<script src="js/mootools-1.2.4-core-yc.js"></script>
		<script src="js/mootools-1.2.4.4-more-yc.js"></script>
		<script src="js/slimpicker.js"></script>
		<script src="js/windowopener.js"></script>
		<title>Enter Achievement</title>
	</head>
	
	<body>
		<Center>
		<h2>UC06/UC07 - Enter Achievement</h2>
		<s:actionerror />
		<s:form  method="post" enctype="multipart/form-data">
			<s:label key = "enterAchivement.employee"  name = "employeeName"/>
			<s:label key = "enterAchivement.employeeid"  name = "employeeId" />
			<s:hidden name= "employeeId" value = "%{employeeId}" />
			<s:label key = "enterAchivement.existingachievement" name = "existingAchievement"/>
			<s:label key = "enterAchivement.peoplemanager" name = "peopleManager"/>
			<s:select key = "enterAchivement.achievementType" list="achiveTypes" name = "achievementType" cssStyle="width: 500px;" headerKey="-1" headerValue="Select" />	
			<s:select key = "enterAchivement.projects" list="projects" name = "project" cssStyle="width: 500px;" headerKey="-1" headerValue="Select" />
			<tr><td>Employee (Peer):</td><td  colspan = "2"><s:textfield key = "enterAchivement.employeepeer" name = "approverEmployee"  theme = "simple"/><input type = "button" value = "LookUp" onclick = "openwindow('enterRegistationFrm')"/></td></tr>
			<s:textfield key = "enterAchivement.from" name = "fromDate" cssClass="slimpicker" id = "achvFrom" />
			<s:textfield key = "enterAchivement.to" name = "toDate" cssClass="slimpicker" id = "achvTo" />
			<s:textarea key = "enterAchivement.achievement" name = "achievementText" cssStyle="width: 500px; height: 200px;" />
			
			<tr>
			    <td>
			      <s:label key = "enterAchivement.documents" value = "Documents:" theme="simple"/>
			    </td>
			    <td colspan = "2">
			      <s:file theme="simple" key  = "enterAchivement.documents" name="file1" /></br>
			      <s:file theme="simple" name = "file2" />
			    </td>
		  	</tr>
			
			<tr>
			    <td colspan="2" align = "center">
			      <s:reset value="Reset" theme="simple"/>
			      <s:submit value="Register"  action = "submitAchievement" theme="simple"/>
			    </td>
		  	</tr>
		  	
		</s:form>
	</Center>
<script>
	$$('input.slimpicker').each( function(el){
		var picker = new SlimPicker(el);
	});
</script>
	</body>
</html>