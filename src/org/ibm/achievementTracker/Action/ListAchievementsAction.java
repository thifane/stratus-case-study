package org.ibm.achievementTracker.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.ibm.achievementTracker.Util.AchievementTrackerWebUtils;
import org.ibm.achievementTracker.Util.IAchievementTrackerWebConstants;

import com.ibm.achievement.Form.AchievementBean;
import com.ibm.achievement.service.AchievementTrackerException_Exception;
import com.ibm.achievement.service.AchievementTrackerPortProxy;
import com.ibm.achievement.service.AchievementVO;
import com.ibm.achievement.service.EmployeeManagementPortProxy;
import com.ibm.achievement.service.EmployeeVO;
import com.opensymphony.xwork2.ActionSupport;

public class ListAchievementsAction extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private List<AchievementBean> achievementBeanList;
	private Logger logger = Logger.getLogger(ListAchievementsAction.class);

	public List<AchievementBean> getAchievementBeanList() {
		return achievementBeanList;
	}

	public void setAchievementBeanList(List<AchievementBean> achievementBeanList) {
		this.achievementBeanList = achievementBeanList;
	}

	public String init() {
		logger.info("START init METHOD");
		AchievementTrackerPortProxy proxy = new AchievementTrackerPortProxy();
		proxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_ACHIEVEMENT);
		EmployeeManagementPortProxy employeeProxy = new EmployeeManagementPortProxy();
		employeeProxy._getDescriptor().setEndpoint(
				IAchievementTrackerWebConstants.ENDPOINT_EMPLOYEEMANAGEMENT);
		List<AchievementVO> achievementVOs = new ArrayList<AchievementVO>();
		HttpSession session=ServletActionContext.getRequest().getSession();
		String employeeid = (String) session.getAttribute("employeeid");
		try {
			achievementVOs = proxy.findAchievementByApproverID(employeeid);
		} catch (AchievementTrackerException_Exception e) {
			logger.error(e);
		}
		int index = 1;
		achievementBeanList = new ArrayList<AchievementBean>();
		for (AchievementVO achievementVO : achievementVOs) {
			AchievementBean bean = new AchievementBean();
			bean.setAchievementText(achievementVO.getAchievementText());
			bean.setEmployeeId(achievementVO.getEmployeeId());
			bean.setForm(AchievementTrackerWebUtils
					.convertXMLGregorianToString(achievementVO.getStartDate()));
			bean.setSerialNumber(index);
			index++;
			bean.setStatus(achievementVO.getStatusCode());
			bean.setTo(AchievementTrackerWebUtils
					.convertXMLGregorianToString(achievementVO.getEndDate()));
			bean.setAchievementId(achievementVO.getAchievementId());
			try {
				EmployeeVO employeeVo = employeeProxy
						.findEmployeesByID(achievementVO.getEmployeeId());
				bean.setEmail(employeeVo.getEmailID());
				bean.setEmployeeName(employeeVo.getFirstName() + " "
						+ employeeVo.getLastName());
			} catch (AchievementTrackerException_Exception e) {
				logger.error(e);
			}

			achievementBeanList.add(bean);
		}
		logger.info("END init METHOD");
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = sessionMap;

	}

}
