<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<title>Review Achievement</title>
		<style type="text/css">
			.textarea{
				width: 98%;
				height: 200px;
			}
		</style>
	</head>
	<body>
		<h2>UC10 - Review Achievement</h2>
		<%@ taglib prefix="s" uri="/struts-tags"%>
		<s:form action="reviewAchievement.action" method="post">
		<s:hidden name="achievementId"></s:hidden>
			<table>
					<tr>
						<td style="width: 100px;"><label>Employee:</label></td>
						<td style="width: 200px;"><s:property value="empReviewAchievement.employeeName"/></td>
						<td style="width: 100px;"><label>Employee Id:</label></td>
						<td style="width: 200px;"><s:property value="empReviewAchievement.employeeId"/></td>
					</tr>
					<tr>
						<td><label>Existing Achievements:</label></td>
						<td colspan="3"><s:property value="empReviewAchievement.existingAcievement"/></td>
					</tr>
					<tr>
						<td><label>People Manager:</label></td>
						<td colspan="3"><s:property value="empReviewAchievement.approver"/></td>
					</tr>
					<tr>
						<td><label>Achievement Type: </label>
						</td>
						<td><s:property value="empReviewAchievement.achievementType"/></td>
						<td><label>Project: </label>
						</td>
						<td><s:property value="empReviewAchievement.project"/></td>
					</tr>
					<tr>
						<td><label>From: </label>
						</td>
						<td><s:property value="empReviewAchievement.fromDate"/></td>
						<td><label>To: </label>
						</td>
						<td><s:property value="empReviewAchievement.toDate"/></td>
					</tr>
					<tr>
						<td><label>Achievement</label>
						</td>
						<td colspan="3"><div style="width: 500px; height: 100px;">
						<s:property value="empReviewAchievement.achievementTxt"/></div>
						</td>
					</tr>
					<tr>
						<td><label>Document(s)</label>
						</td>
						<td colspan="3"><a href="<s:url value="download"/>">document 1</a><br />
						<a href="<s:url value="download"/>">document 2</a>
						</td>
					</tr>
					<tr>
						<td colspan="4"><label>Approval Information</label></td>
					</tr>
					<tr>
						<td><label>Category:</label></td>
						<td>
							<s:select list="achievementCategories" headerKey="-1" headerValue="Select Category" 
								name="empReviewAchievement.category" theme="simple"></s:select>
						</td>
						<td><label>Point Value: </label></td>
						<td><s:textfield name="empReviewAchievement.pointValue" key="label.pointval" theme="simple"/></td>
						
					</tr>
					<tr>
						<td><label>Comments</label>
						</td>
						<td colspan="3">
							<span class="hint">
								<s:textarea name="empReviewAchievement.comments" theme="simple" cssClass="textarea"/>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<s:submit key="label.reject" name="buttonname" theme="simple"></s:submit>
							<s:submit key="label.approve" name="buttonname" theme="simple"></s:submit>
						</td>
					</tr>
				</table>		
		</s:form>
	</body>
</html>