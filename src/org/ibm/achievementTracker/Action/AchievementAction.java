package org.ibm.achievementTracker.Action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.ibm.achievementTracker.Util.AchievementTrackerWebUtils;
import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.Form.EmployeeReviewAchievement;
import com.ibm.achievement.service.AchievementCatgryVO;
import com.ibm.achievement.service.AchievementCountVO;
import com.ibm.achievement.service.AchievementDocVO;
import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.AchievementTrackerPortProxy;
import com.ibm.achievement.service.AchievementTypesVO;
import com.ibm.achievement.service.AchievementVO;
import com.ibm.achievement.service.EmployeeManagementPortProxy;
import com.ibm.achievement.service.EmployeeVO;
import com.ibm.achievement.service.ProjectManagementPortProxy;
import com.ibm.achievement.service.ProjectVO;
import com.opensymphony.xwork2.ActionSupport;


public class AchievementAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeReviewAchievement empReviewAchievement;
	private Map<String, String> achievementCategories;
	private String achievementId;
	private String buttonname;
	
	
	public EmployeeReviewAchievement getEmpReviewAchievement() {
		return empReviewAchievement;
	}

	public void setEmpReviewAchievement(
			EmployeeReviewAchievement empReviewAchievement) {
		this.empReviewAchievement = empReviewAchievement;
	}

	public Map<String, String> getAchievementCategories() {
		return achievementCategories;
	}

	public void setAchievementCategories(Map<String, String> achievementCategories) {
		this.achievementCategories = achievementCategories;
	}

	public String getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(String achievementId) {
		this.achievementId = achievementId;
	}

	public String getButtonname() {
		return buttonname;
	}

	public void setButtonname(String buttonname) {
		this.buttonname = buttonname;
	}

	/**
	 * Initializes the page i.e. populates the data while the page is loaded.
	 */
	
	public String findAchievement(){
		
		//achievementId = "1";//(String) session.getAttribute("achievementId");
		empReviewAchievement = new EmployeeReviewAchievement();
		AchievementTrackerPortProxy achievementProxy = new AchievementTrackerPortProxy();
		achievementProxy._getDescriptor().setEndpoint(IAchievementTrackerWebConstants.ENDPOINT_ACHIEVEMENT);
		
		AchievementVO achievementVO = null;
		try {
			achievementVO = achievementProxy.findAchievementById(Integer.parseInt(achievementId));
		} catch (NumberFormatException e1) {
			return IAchievementTrackerWebConstants.ERROR;
		} catch (AchievementTrackerException_Exception e1) {
			return IAchievementTrackerWebConstants.ERROR;
		}

		List<AchievementDocVO> achievementDocVOLst = achievementVO.getAchievementDoc();
		AchievementDocVO achievementDocVO1 = null;
		AchievementDocVO achievementDocVO2 = null;
		AchievementDocVO achievementDocVO3 = null;
		AchievementDocVO achievementDocVO4 = null;
		
		if (achievementDocVOLst != null && achievementDocVOLst.size() > 0) {
			
			int listSize = achievementDocVOLst.size();
			if(listSize > 0 && achievementDocVOLst.get(0) != null){
				achievementDocVO1 = achievementDocVOLst.get(0);
				empReviewAchievement.setByteArr1(achievementDocVO1.getDocumentContent());
			}
			if(listSize >1 && achievementDocVOLst.get(1) != null){
				achievementDocVO2 = achievementDocVOLst.get(1);
				empReviewAchievement.setByteArr2(achievementDocVO2.getDocumentContent());
			}
		}
		
		if (achievementDocVO1 != null) {
			empReviewAchievement.setDownloadFile1(achievementDocVO1.getDocumentName());
		}
		if (achievementDocVO2 != null) {
			empReviewAchievement.setDownloadFile2(achievementDocVO2.getDocumentName());
		}

		empReviewAchievement.setEmployeeId(achievementVO.getEmployeeId());
		EmployeeManagementPortProxy empProxy = new EmployeeManagementPortProxy();
		empProxy._getDescriptor().setEndpoint(IAchievementTrackerWebConstants.ENDPOINT_EMPLOYEEMANAGEMENT);
		EmployeeVO empVO = null;
		EmployeeVO empVO2 = null;
		
		try {
			empVO = empProxy.findEmployeesByID(achievementVO.getEmployeeId());
			
		} catch (AchievementTrackerException_Exception e) {
			return IAchievementTrackerWebConstants.ERROR;
		}
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(empVO.getFirstName()).append(" ").append(empVO.getLastName());
		empReviewAchievement.setEmployeeName(nameBuilder.toString());
		
		try {
			empVO2 = empProxy.findEmployeesByID(achievementVO.getApproverId());
			
		} catch (AchievementTrackerException_Exception e) {
			return IAchievementTrackerWebConstants.ERROR;
		}
		List<AchievementCatgryVO> categoriesLst = null;
		AchievementCountVO countVO = null;
		try {
			categoriesLst = achievementProxy.findAchievementCategories();
			countVO= achievementProxy.findAchievementSummery(empVO.getEmployeeId());
			
		} catch (AchievementTrackerException_Exception e) {
			return IAchievementTrackerWebConstants.ERROR;
		}
		achievementCategories = new HashMap<String, String>();
		
		for (AchievementCatgryVO achievementCatgryVO : categoriesLst) {	
			achievementCategories.put(achievementCatgryVO.getCategoryId(),achievementCatgryVO.getDescription());
		}

		AchievementTypesVO achievementTypesVO = null;
		try {
			achievementTypesVO = achievementProxy.findAchievementType(achievementVO.getAchievementTypeId());
			
		} catch (AchievementTrackerException_Exception e) {
			return IAchievementTrackerWebConstants.ERROR;
		}

		empReviewAchievement.setApprover(empVO2.getFirstName() + " "+ empVO2.getLastName());
		empReviewAchievement.setAchievementType(achievementTypesVO.getDescription());
		String projectId = achievementVO.getProjectId();
		ProjectManagementPortProxy prjProxy = new ProjectManagementPortProxy();
		prjProxy._getDescriptor().setEndpoint(IAchievementTrackerWebConstants.ENDPOINT_PROJECTANAGEMENT);
		
		ProjectVO prjVO = null;
		try {
			prjVO = prjProxy.findProjectById(projectId);
		} catch (AchievementTrackerException_Exception e) {
			return IAchievementTrackerWebConstants.ERROR;
		}
		empReviewAchievement.setProject(prjVO.getProjectName() + "(" + prjVO.getProjectID() + ")");
		empReviewAchievement.setFromDate(AchievementTrackerWebUtils.convertXMLGregorianToString(achievementVO.getStartDate()));
		empReviewAchievement.setToDate(AchievementTrackerWebUtils.convertXMLGregorianToString(achievementVO.getEndDate()));
		empReviewAchievement.setAchievementTxt(achievementVO.getAchievementText());
		
		StringBuilder summary = new StringBuilder();
		summary.append("This month: ").append(countVO.getCurrentMonthCount()).append(",").append("This year: ").append(countVO.getCurrentYearCount());
		
		empReviewAchievement.setExistingAcievement(summary.toString());

		return "success";
	}
	
	public String reviewAchievement(){
		String statusFlg="";
		if("approve".equalsIgnoreCase(buttonname)){
			statusFlg = "Y";
		}
		if("reject".equalsIgnoreCase(buttonname)){
			statusFlg = "R";
		}
		String forword = approveAchievement(statusFlg);
		
		return forword;
	}
	/**
	 * Approves the achievement.
	 * @return
	 */
	public String approveAchievement(String statusFlg) {

		String outcome = "success";
		AchievementTrackerPortProxy achievementProxy = new AchievementTrackerPortProxy();
		achievementProxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_ACHIEVEMENT);

		Integer pointVal = Integer.parseInt(empReviewAchievement
				.getPointValue());

		try {
			achievementProxy.approveAchievement(achievementId, statusFlg,
					empReviewAchievement.getCategory(), pointVal.intValue(),
					empReviewAchievement.getComments());
		} catch (AchievementTrackerException_Exception e) {
			outcome = "error";
		}
		return outcome;

	}
	
	/**
	 * Download the first file.
	 * @throws IOException
	 */
	public void downloadFile1() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=instructions.txt");
		response.getOutputStream().write(empReviewAchievement.getByteArr1());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		FacesContext.getCurrentInstance().responseComplete();

	}
	/**
	 * Downloads the second file.
	 * @throws IOException
	 */
	public void downloadFile2() throws IOException {
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=instructions.txt");
		response.getOutputStream().write(empReviewAchievement.getByteArr2());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		FacesContext.getCurrentInstance().responseComplete();

	}	
}
