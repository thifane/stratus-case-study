package org.ibm.achievementTracker.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.EmployeeVO;
import com.ibm.achievement.service.ProjectManagementPortProxy;
import com.ibm.achievement.service.ProjectVO;
import com.ibm.achievement.service.UserManagementPortProxy;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class RegisterAction extends ActionSupport implements Preparable{

	private List<String> projects;
	private List<String> selectedProjects;
	Map allProjects;
	private String emailAddress;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String employeeId;
	private String peopleManager;
	private String submitted = "false";
	
	public RegisterAction() {
		loadProjects();
		// TODO Auto-generated constructor stub
	}
	@Override
    public String execute(){
		//loadProjects();
		System.out.println(emailAddress + " " + password + " " + confirmPassword + " " + firstName + " " + lastName + " " + employeeId + " " + peopleManager + " " + submitted );
        return SUCCESS;
    }
     
	
	public String loadRegisterForm() {
		System.out.println(emailAddress + " " + password + " " + confirmPassword + " " + firstName + " " + lastName + " " + employeeId + " " + peopleManager + " " +submitted);
		return SUCCESS;
		//return "registerForm";
	}
	
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




	public String getConfirmPassword() {
		return confirmPassword;
	}




	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmployeeId() {
		return employeeId;
	}




	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}




	public String getPeopleManager() {
		return peopleManager;
	}




	public void setPeopleManager(String peopleManager) {
		this.peopleManager = peopleManager;
	}




	public List<String> getProjects() {
		return projects;
	}



	public void setProjects(List<String> projects) {
		this.projects = projects;
	}



	public List<String> getSelectedProjects() {
		return selectedProjects;
	}



	public void setSelectedProjects(List<String> selectedProjects) {
		//System.out.println("selectedProjects === "+selectedProjects.size());
		this.selectedProjects = selectedProjects;
	}

	public String doRegister() {
		String outcome = SUCCESS;
		EmployeeVO empVO = createEmployeeVO();
		UserManagementPortProxy proxy = new UserManagementPortProxy();
		proxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_USERMANAGEMENT);
		try {
			proxy.registerUser(empVO, password);
		} catch (AchievementTrackerException_Exception e) {
			outcome = INPUT;
			addActionError("User Registration failed due to -" +e.getMessage());
			//System.out.println("In AchievementTrackerException_Exception block");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			outcome = INPUT;
			//System.out.println("In Exception block");
			addActionError("User Registration failed due to -" +ex.getMessage());			
		}
		//loadProjects();
		return outcome;
	}
	
	/**
	 * Builds EmployeeVO object.
	 * @return
	 */
	private EmployeeVO createEmployeeVO() {
		EmployeeVO empVO = new EmployeeVO();
		empVO.setActiveFlag("N");
		empVO.setManagerFlag("N");
		empVO.setEmailID(emailAddress);
		empVO.setEmployeeId(employeeId);
		empVO.setFirstName(firstName);
		empVO.setLastName(lastName);
		empVO.setManagerId(peopleManager);
		empVO.setUserRoll("EMPLOYEE");
		List<ProjectVO> prjList = new ArrayList<ProjectVO>();
		for (String prj : selectedProjects) {
			ProjectVO prjVO = new ProjectVO();
			prjVO.setProjectID((String)allProjects.get(prj));
			prjList.add(prjVO);
		}
		empVO.setProjects(prjList);

		return empVO;
	}
	
	
	public void validate() {
		System.out.println("Validate..............");
		if(submitted.equalsIgnoreCase("false")) return;
		//loadProjects();
		if (emailAddress == null ||emailAddress.equals("")) {
			addActionError("The emailAddress is required.");
	    }
		if (password == null ||password.equals("")) {
			addActionError("The password is required.");
	    }
		
		if (confirmPassword== null ||confirmPassword.equals("")) {
			addActionError("The confirmPassword is required.");
	    }else if(!confirmPassword.equalsIgnoreCase(password)){
	    	addActionError("The password and confirmPassword aren't matching.");
	    }
		if (firstName == null ||firstName.equals("")) {
			addActionError("The firstName is required.");
	    }
		if (lastName == null || lastName.equals("")) {
			addActionError("The lastName is required.");
	    }
		if (employeeId == null || employeeId.equals("")) {
			addActionError("The employeeId is required.");
	    }
		if (peopleManager == null || peopleManager.equals("")) {
			addActionError("The peopleManager is required.");
	    }
	}

	
	
	public void loadProjects() {
		System.out.println();
		/*projects = new ArrayList<String>();
		projects.add("Project 1 (IN-234562)");
		projects.add("Project 2 (IN-234562)");
		projects.add("Project 3 (IN-234562)");*/
		
		
		if (projects==null) {
			ProjectManagementPortProxy projectProxy = new ProjectManagementPortProxy();
			projectProxy._getDescriptor().setEndpoint(
					IAchievementTrackerWebConstants.ENDPOINT_PROJECTANAGEMENT);
			List<ProjectVO> prjList = null;
			projects = new ArrayList<String>();
			try {				
				prjList = projectProxy.findProjects();
				for(ProjectVO prjVo : prjList) {
					projects.add(prjVo.getProjectName());
				}
			} catch (AchievementTrackerException_Exception acte) {
				acte.printStackTrace();
				//TO DO: log the exception
				return;

			}
			if (prjList != null && prjList.size() > 0) {
				allProjects = new HashMap<String, String>();
				for (ProjectVO prjVO : prjList) {
					String prjName = prjVO.getProjectName();
					String id = prjVO.getProjectID();
					//StringBuilder prjBuilder = new StringBuilder();
					//prjBuilder.append(prjName).append("(").append(id)
					//		.append(")");
					allProjects.put(prjName, id);

				}
			}
		}
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
}
