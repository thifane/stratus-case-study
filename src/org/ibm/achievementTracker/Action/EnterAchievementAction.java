package org.ibm.achievementTracker.Action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.ibm.achievementTracker.Util.AchievementTrackerWebUtils;
import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.service.AchievementCountVO;
import com.ibm.achievement.service.AchievementDocVO;
import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.AchievementTrackerPortProxy;
import com.ibm.achievement.service.AchievementTypesVO;
import com.ibm.achievement.service.AchievementVO;
import com.ibm.achievement.service.EmployeeManagementPortProxy;
import com.ibm.achievement.service.EmployeeVO;
import com.ibm.achievement.service.ProjectVO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class EnterAchievementAction extends ActionSupport implements ServletRequestAware,SessionAware {

	private List<String> achiveTypes;
	private List<String> projects;
	private String employeeName;
	private String employeeId;
	private String existingAchievement;
	private String peopleManager;
	private String achievementType;
	private String fromDate;
	private String toDate;
	private String project;
	private String achievementText;
	private String approverEmployee;
	
	private File file1;
	private File file2;
	
	private String file1ContentType;
	private String file2ContentType;
	
	private String file1FileName;
	private String file2FileName;
	
	private Map<String,String>achievementTypes;
	Map<String,String>projectsMap;
	private Map<String,String>projectsForMgr;
	private HttpServletRequest servletRequest;
	private Map sessionMap;
	
	public List<String> getAchiveTypes() {
		return achiveTypes;
	}

	public void setAchiveTypes(List<String> achiveTypes) {
		this.achiveTypes = achiveTypes;
	} 
	
	
	public String getAchievementType() {
		return achievementType;
	}

	public void setAchievementType(String achievementType) {
		this.achievementType = achievementType;
	}

	
	
	public String getExistingAchievement() {
		return existingAchievement;
	}

	public void setExistingAchievement(String existingAchievement) {
		this.existingAchievement = existingAchievement;
	}

	public String getPeopleManager() {
		return peopleManager;
	}

	public void setPeopleManager(String peopleManager) {
		this.peopleManager = peopleManager;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String init() {
		loadEmployeeAchivement();
		loadAchieveTypes();
		loadProjects();
		//setEmployeeId("4567898");
		//setEmployeeName("test struts2");
		return SUCCESS;
	}
	
	
	public String getApproverEmployee() {
		return approverEmployee;
	}

	public void setApproverEmployee(String approverEmployee) {
		this.approverEmployee = approverEmployee;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public void loadEmployeeAchivement() {
			
			//	if (employeeAchievement == null) {
			//HttpServletRequest origRequest = (HttpServletRequest) FacesContext
			//		.getCurrentInstance().getExternalContext().getRequest();
		
			HttpServletRequest origRequest = ServletActionContext.getRequest();
			HttpSession session = origRequest.getSession();
			EmployeeVO empVO = (EmployeeVO) session.getAttribute("empObject");
			String employeeId = (String)session.getAttribute("employeeid");
			//employeeAchievement = new EmployeeAchievement();
			EmployeeManagementPortProxy proxy = new EmployeeManagementPortProxy();
			proxy._getDescriptor()
					.setEndpoint(
							IAchievementTrackerWebConstants.ENDPOINT_EMPLOYEEMANAGEMENT);
			EmployeeVO managerVO = null;
			try {
				managerVO = proxy.findEmployeesByID(empVO.getManagerId());
				//managerVO = proxy.findEmployeesByID(employeeId);
			} catch (AchievementTrackerException_Exception e1) {
				//TO DO: log the exception
				return;
			}

			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append(empVO.getFirstName());
			nameBuilder.append("  ");
			nameBuilder.append(empVO.getLastName());

			setEmployeeName(nameBuilder.toString());
			setEmployeeId(empVO.getEmployeeId());
			setPeopleManager(empVO.getManagerId()
					+ "(" + managerVO.getFirstName() + " "
					+ managerVO.getLastName() + ")");

			AchievementTrackerPortProxy achievementProxy = new AchievementTrackerPortProxy();
			List<AchievementTypesVO> achievementTypeLst = null;
			AchievementCountVO countVO = null;
			achievementProxy._getDescriptor().setEndpoint(
					IAchievementTrackerWebConstants.ENDPOINT_ACHIEVEMENT);
			
			try {
				achievementTypeLst = achievementProxy.findAchievementTypes();
				//Retrieve the Achievement summary.
				countVO= achievementProxy.findAchievementSummery(empVO.getEmployeeId());
			} catch (AchievementTrackerException_Exception e) {
				//TO DO: log the exception
				return;
			}
			achievementTypes = new HashMap<String, String>();
			for (AchievementTypesVO achievementTypesVO : achievementTypeLst) {
				achievementTypes.put(achievementTypesVO.getDescription(),
						achievementTypesVO.getTypeId());
			}
			List<ProjectVO> prjLst = empVO.getProjects();
			projectsMap = new HashMap<String, String>();
			projectsForMgr = new HashMap<String, String>();
			for (ProjectVO projectVO : prjLst) {
				projectsMap.put(projectVO.getProjectName(),
						projectVO.getProjectID());
				projectsForMgr.put(projectVO.getProjectID(),
						projectVO.getManagerID());
			}
			//Setting achievement summary.
			StringBuilder summary = new StringBuilder();
			summary.append("This month: ").append(countVO.getCurrentMonthCount()).append(",")
							.append("This year: ").append(countVO.getCurrentYearCount());
			
			setExistingAchievement(summary.toString());
			
			sessionMap.put("projectsMap", projectsMap);
			sessionMap.put("achievementTypes", achievementTypes);
		//}
		
		// setting the manager flag value in the httpsession object
		//HttpServletRequest origRequest = (HttpServletRequest) FacesContext
		//		.getCurrentInstance().getExternalContext().getRequest();
		session = origRequest.getSession();
		session.setAttribute("managerFlag", "N");
	}
	
	
	
	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public void loadAchieveTypes() {
		achiveTypes =  new ArrayList<String>();
		
		for(String achName : achievementTypes.keySet()) {
			achiveTypes.add(achName);
		}
		/*achiveTypes.add("Project Achievement");
		achiveTypes.add("Organization Give Back");
		achiveTypes.add("Helping Other Employee");
		achiveTypes.add("Sales/Business");*/
		
	}
	
	
	public String saveAchievement() {
		System.out.println("saveAchievement................");
		//loadAchieveTypes();
		//String 
		try {
			
			/*byte[] byteArr = IOUtils.toByteArray(FileUtils.openInputStream(file1));
			System.out.println(file1ContentType + " " + file1FileName);
			System.out.println(byteArr);*/
			//projectsMap = (Map)ActionContext.getContext().getValueStack().findValue("key1");
			
			HttpServletRequest origRequest = ServletActionContext.getRequest();
			HttpSession session = origRequest.getSession();
			
			if(projectsMap==null) 
				projectsMap = (Map)session.getAttribute("projectsMap");
			if(achievementTypes==null) 
				achievementTypes = (Map)session.getAttribute("achievementTypes");
			
			AchievementVO achievementVO = new AchievementVO();
			achievementVO.setEmployeeId(getEmployeeId());
			achievementVO.setAchievementTypeId(achievementTypes.get(getAchievementType()));
			achievementVO.setProjectId(projectsMap.get(getProject()));
			achievementVO.setAchievementText(getAchievementText());

			String approverId = getApproverEmployee();
			achievementVO.setApproverId(approverId);

			String frmdate = getFromDate();

			Date startDate = null;
			if (frmdate != null && !frmdate.isEmpty()) {
				startDate = AchievementTrackerWebUtils.convertStringToDate(frmdate);
			}

			XMLGregorianCalendar gregStartDate = AchievementTrackerWebUtils
					.convertDateToXMLGregorian(startDate);
			achievementVO.setStartDate(gregStartDate);

			String todt = getToDate();

			Date endDate = null;
			
			if (todt != null && !todt.isEmpty()) {
				endDate = AchievementTrackerWebUtils.convertStringToDate(todt);
			}
			
			if (endDate != null) {
				XMLGregorianCalendar gregEndDate = AchievementTrackerWebUtils
						.convertDateToXMLGregorian(endDate);
				achievementVO.setEndDate(gregEndDate);
			}

			AchievementTrackerPortProxy achievementProxy = new AchievementTrackerPortProxy();
			achievementProxy._getDescriptor().setEndpoint(
					IAchievementTrackerWebConstants.ENDPOINT_ACHIEVEMENT);

				processFileUpload(achievementVO);

			try {
				achievementProxy.saveAchievement(achievementVO);
			} catch (AchievementTrackerException_Exception e) {
				//outcome = "error";
			}

			//return outcome;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void loadProjects() {
		projects =  new ArrayList<String>();
		
		for(String prjName : projectsMap.keySet()) {
			projects.add(prjName);
		}
		/*projects.add("Project 1 (IN-234562)");
		projects.add("Project 2 (IN-234562)");
		projects.add("Project 3 (IN-234562)");
		projects.add("Project 4 (IN-234562)");*/
	}

	
	
	public void processFileUpload(AchievementVO achievementVO) throws IOException {
		byte[] byteArr1 =null;
		byte[] byteArr2 =null;
		
		AchievementDocVO achievementDocVO = new AchievementDocVO();
		AchievementDocVO achievementDocVO1 = new AchievementDocVO();
		
		
		
				if(file1!=null) {
					byteArr1 = IOUtils.toByteArray(FileUtils.openInputStream(file1));
					achievementDocVO.setDocumentContent(byteArr1);
					achievementDocVO.setDocumentName(file1FileName);
					achievementVO.getAchievementDoc().add(achievementDocVO);
				}
				
				if(file2!=null) {
					byteArr2 = IOUtils.toByteArray(FileUtils.openInputStream(file2));
					achievementDocVO1.setDocumentContent(byteArr2);
					achievementDocVO1.setDocumentName(file2FileName);
					achievementVO.getAchievementDoc().add(achievementDocVO1);					
				}
		
		System.out.println(file1ContentType + " " + file1FileName);
		System.out.println(byteArr1);
	}
	
	
	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public String getAchievementText() {
		return achievementText;
	}

	public void setAchievementText(String achievementText) {
		this.achievementText = achievementText;
	}

	public String getFile1ContentType() {
		return file1ContentType;
	}

	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}

	public String getFile2ContentType() {
		return file2ContentType;
	}

	public void setFile2ContentType(String file2ContentType) {
		this.file2ContentType = file2ContentType;
	}

	public String getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}

	public String getFile2FileName() {
		return file2FileName;
	}

	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}
}
