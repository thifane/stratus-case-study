package org.ibm.achievementTracker.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.Form.EmployeeBean;
import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.EmployeeVO;
import com.ibm.achievement.service.ProjectVO;
import com.ibm.achievement.service.UserManagementPortProxy;
import com.opensymphony.xwork2.ActionSupport;



public class UserApproval extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private List<EmployeeBean> employeeBeanLst;
	private String[] selectedRecords ;

	public String[] getSelectedRecords() {
		return selectedRecords;
	}
	public void setSelectedRecords(String[] selectedRecords) {
		this.selectedRecords = selectedRecords;
	}
	public List<EmployeeBean> getEmployeeBeanLst() {
		return employeeBeanLst;
	}
	public void setEmployeeBeanLst(List<EmployeeBean> employeeBeanLst) {
		this.employeeBeanLst = employeeBeanLst;
	}
	
	public String init() {

		UserManagementPortProxy proxy = new UserManagementPortProxy();
		proxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_USERMANAGEMENT);
		List<EmployeeVO> empVOLst = null;
		try {
			empVOLst = proxy.findUserByActiveFlag("N");
		} catch (AchievementTrackerException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeBeanLst = new ArrayList<EmployeeBean>();
		int index = 0;
		for (EmployeeVO empVO : empVOLst) {
			EmployeeBean empBean = new EmployeeBean();
			empBean.setSerialNumber(index + 1);
			String firstName = empVO.getFirstName();
			String lastName = empVO.getLastName();
			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append(firstName);
			nameBuilder.append("  ");
			nameBuilder.append(lastName);
			empBean.setEmployeeName(nameBuilder.toString());
			empBean.setEmployeeId(empVO.getEmployeeId());
			empBean.setEmail(empVO.getEmailID());
			empBean.setEmployeePeopleManager(empVO.getManagerId());
			StringBuilder prjBuilder = new StringBuilder();
			List<ProjectVO> prjList = empVO.getProjects();
			int k = 1;
			for (ProjectVO prjVo : prjList) {
				String prjName = prjVo.getProjectName();
				prjBuilder.append(prjName);
				if (k < prjList.size()) {
					prjBuilder.append(",");
				}
				k++;
			}
			empBean.setProjects((prjBuilder.toString()));
			employeeBeanLst.add(empBean);

		}
		return "success";
	}
	
	/**
	 * Approves the user while the user clicks 'Approve' button.
	 * 
	 * @return
	 */
	public String approve() {
		String outcome = "success";
		Set<String> keys = new HashSet<String>(Arrays.asList(selectedRecords));
		UserManagementPortProxy proxy = new UserManagementPortProxy();
		proxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_USERMANAGEMENT);
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String keyVal = it.next();
			try {
				proxy.updateUserActiveFlag("Y", keyVal);
			} catch (AchievementTrackerException_Exception e) {
				outcome = "error";
			} catch (Exception ex) {
				outcome = "error";
			}
		}
		init();
		return outcome;
	}
}
