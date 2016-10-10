package org.ibm.achievementTracker.Action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.EmployeeManagementPortProxy;
import com.ibm.achievement.service.EmployeeVO;
import com.opensymphony.xwork2.ActionSupport;

public class LookUpAction extends ActionSupport{

	private String employeeId;
	private String empFirstName;
	private String empLastName;
	private String email;
	private String frmName;
	
	private List<EmployeeVO> empVOLst;
	private boolean userRegistrationVisible;
	private boolean enterAchvmntVisible;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<EmployeeVO> getEmpVOLst() {
		return empVOLst;
	}
	public void setEmpVOLst(List<EmployeeVO> empVOLst) {
		this.empVOLst = empVOLst;
	}
	public boolean isUserRegistrationVisible() {
		return userRegistrationVisible;
	}
	public void setUserRegistrationVisible(boolean userRegistrationVisible) {
		this.userRegistrationVisible = userRegistrationVisible;
	}
	public boolean isEnterAchvmntVisible() {
		return enterAchvmntVisible;
	}
	public void setEnterAchvmntVisible(boolean enterAchvmntVisible) {
		this.enterAchvmntVisible = enterAchvmntVisible;
	}	
	
	public String execute() {
		HttpServletRequest origRequest = ServletActionContext.getRequest();
		frmName = origRequest.getParameter("frmname");
		String name1 = origRequest.getParameter("frmname");
		System.out.println("name1 ==" +name1);
		return SUCCESS;
	}
	
	
	
	public String getFrmName() {
		return frmName;
	}
	public void setFrmName(String frmName) {
		this.frmName = frmName;
	}
	
	public String init() {
		HttpServletRequest origRequest = ServletActionContext.getRequest();
		frmName = origRequest.getParameter("frmname");
		String name1 = origRequest.getParameter("frmname");
		System.out.println("name1 ==" +name1);
		return SUCCESS;
	}
	public String search() {
		EmployeeManagementPortProxy empProxy = new EmployeeManagementPortProxy();
		empProxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_EMPLOYEEMANAGEMENT);
		String mgrflag = "Y";
		//HttpServletRequest origRequest = (HttpServletRequest) FacesContext
		//		.getCurrentInstance().getExternalContext().getRequest();
		//HttpSession session = origRequest.getSession();
		//String mgrflag= (String)session.getAttribute("managerFlag");
		/*if(null!=mgrflag && !mgrflag.isEmpty()){
			session.removeAttribute("managerFlag");
		}else{
			mgrflag="Y";
		}*/
		
		try {
			empVOLst = empProxy.findEmployees(email, empFirstName, empLastName,mgrflag);
		} catch (AchievementTrackerException_Exception e) {
			//TODO: log exception
		}
		return SUCCESS;
		
	}
}
