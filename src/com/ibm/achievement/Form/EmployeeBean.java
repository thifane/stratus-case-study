package com.ibm.achievement.Form;

import java.io.Serializable;

public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer serialNumber;
	private String employeeId;
	private String employeeName;
	private String email;
	private String employeePeopleManager;
	private String projects;
	private boolean approvedStatus;

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public EmployeeBean() {

	}

	public EmployeeBean(String employeeId, String employeeName, String email,
			String employeePeopleManager, String projects,
			boolean approvedStatus) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.employeePeopleManager = employeePeopleManager;
		this.projects = projects;
		this.approvedStatus = approvedStatus;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * 
	 * @param employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * 
	 * @param employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return
	 */
	public String getProjects() {
		return projects;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmployeePeopleManager() {
		return employeePeopleManager;
	}

	/**
	 * 
	 * @param employeePeopleManager
	 */
	public void setEmployeePeopleManager(String employeePeopleManager) {
		this.employeePeopleManager = employeePeopleManager;
	}

	/**
	 * 
	 * @param projects
	 */
	public void setProjects(String projects) {
		this.projects = projects;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isApprovedStatus() {
		return approvedStatus;
	}

	/**
	 * 
	 * @param approvedStatus
	 */
	public void setApprovedStatus(boolean approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
}
