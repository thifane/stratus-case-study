<%@ taglib prefix="s" uri="/struts-tags"%>
<div align="center" style="font-weight:bold">Achievement Tracker Home</div><br>

<tr>
	<td><div align="left">Welcome ::<s:property value="#session.username" /></div></td>
	<td>
		<div align="right">
			<s:url action="logoff" var="urlTag"></s:url>
			<a href="<s:property value="#urlTag" />" >log out</a>
		</div>
	</td>
</tr>