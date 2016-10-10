<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<title>Approve User</title>
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
		<s:form name="approveuser" action="approveUser.action" method="post">
			<table align="center" cellspacing="2" cellpadding="2" border="1">
				<tbody>
					<tr>
						<td width="900" height="30" colspan="2">
							<div align="center" style="font-weight:bold">Achievement Tracker Home</div><br>
						</td>
					</tr>
					<tr>
						<td>Welcome Admin</td>
						<td align="right">
							<s:url action="logoff" var="urlTag"></s:url>
							<a href="<s:property value="#urlTag" />" >log out</a>
						</td>
					</tr>
					<tr>
						<td width="200" height="500"></td>
						<td width="700">
							<h2 align="left" >UC04/UC05 - Review User Details</h2>
							<table>
								<tr>
									<th style="width: 50px;">Sl. No.</th>
									<th style="width: 240px;">Employee Name</th>
									<th style="width: 100px;">Employee Id</th>
									<th style="width: 240px;">Email</th>
									<th style="width: 240px;">People Manager</th>
									<th style="width: 100px;">Projects</th>
									<th style="width: 50px;">Approve</th>
								</tr>
								<s:iterator value="employeeBeanLst">
								<tr>
									<td><s:property value="serialNumber"/></td>
									<td><s:property value="employeeName"/></td>
									<td><s:property value="employeeId"/></td>
									<td><s:property value="email"/></td>
									<td><s:property value="employeePeopleManager"/></td>
									<td><s:property value="projects"/></td>
									<td><s:checkbox name="selectedRecords" fieldValue="%{email}" theme="simple"/></td>
								</tr>
								</s:iterator>
							</table>
							<table>
								<s:submit method="execute" key="label.approve" cssClass="mybutton"/>
								<s:reset value="Reset" cssClass="mybutton"/>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</s:form>
		</center>
	</body>
</html>