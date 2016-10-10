package org.ibm.achievementTracker.Action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.EmployeeVO;
import com.ibm.achievement.service.UserManagementPortProxy;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	
	private String EMPLOYEE="employee";
	private String ADMIN="admin";
	private String SUCCESS="success";
	private String ERROR="error";
	private Map<String, Object> sessionMap;
	private static final long serialVersionUID = 1L;
	
	private String emailAddress;
	private String password;

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String authenticate() {
		
		UserManagementPortProxy proxy = new UserManagementPortProxy();
		proxy._getDescriptor().setEndpoint(IAchievementTrackerWebConstants.ENDPOINT_USERMANAGEMENT);
		EmployeeVO empVO = null;
		
		try {
			empVO = proxy.isValidUser(emailAddress, password);
			sessionMap.put("username", empVO.getFirstName());
			sessionMap.put("employeeid", empVO.getEmployeeId());
			sessionMap.put("empObject",empVO);			
		} catch (AchievementTrackerException_Exception e) {
			empVO = null;
		}
		
		if (("EMPLOYEE").equals(empVO.getUserRoll())) {
			return EMPLOYEE;
		} else if(("ADMIN").equals(empVO.getUserRoll())){
			return ADMIN;
		}else{
			addActionError(getText("error.login"));
			return ERROR;
		}
	}
	
	public String goHome(){
		//do nothing
		return SUCCESS;
	}
	
	public String signOut(){
		
		sessionMap.clear();
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=sessionMap;
		
	}
}
