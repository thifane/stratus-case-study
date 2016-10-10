<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script>
		function showText(fullText){
			alert(fullText);
		}
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<title>UC11 - List Achievement (My Employees)</title>
		<style type="text/css">
			.mybutton{
				width: 100px;
			}
			.mycheckbox{
				width: 20px;
			}
		</style>
	</head>
	<body>
	<center>
	<h2 align="left">UC11 - List Achievement (My Employees)</h2>
		<s:form name="listachievements" action="listachievements.action" method="post">
			<table>
				<tr>
					<th style="width: 50px;">Sl. No.</th>
					<th style="width: 240px;">Employee Name</th>
					<th style="width: 100px;">Employee Id</th>
					<th style="width: 100px;">Email</th>
					<th style="width: 240px;">Achievement</th>
					<th style="width: 50px;">Form</th>
					<th style="width: 50px;">To</th>
					<th style="width: 50px;">Status</th>
				</tr>
			   <s:iterator value="achievementBeanList">
				<tr>
					<td><s:property value="serialNumber"/></td>
					<td><s:property value="employeeName"/></td>
					<td><s:property value="employeeId"/></td>
					<td><s:property value="email"/></td>
					<td><s:property value="achievement"/><s:a href="javascript:showText('%{achievementText}')"> ...more</s:a></td>
					<td><s:property value="form"/></td>
					<td><s:property value="to"/></td>
					<td>
						<s:if test="%{status == \"Y\"}">
							Approved
						</s:if>
						<s:else>
							<s:url action="showAchievement.action" var="urlTag" >
						    	<s:param name="achievementId"><s:property value="achievementId"/></s:param>
							</s:url>
							<s:a href="%{urlTag}">pending</s:a>
						</s:else>
					</td>
				</tr>
				</s:iterator>
			</table>
		</s:form>
		</center>
	</body>
</html>