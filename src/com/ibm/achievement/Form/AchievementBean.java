package com.ibm.achievement.Form;

public class AchievementBean {

	private static final long serialVersionUID = 1L;
	private int achievementId;
	private Integer serialNumber;
	private String employeeId;
	private String employeeName;
	private String email;
	private String achievementText;
	private String achievement;
	private String form;
	private String to;
	private String status;

	
	public String getAchievement() {
		if(achievementText != null && achievementText.length() > 20){
			return achievementText.substring(0, 20);
		}
		return achievementText;
	}

	
	public int getAchievementId() {
		return achievementId;
	}


	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}


	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAchievementText() {
		return achievementText;
	}

	public void setAchievementText(String achievementText) {
		this.achievementText = achievementText;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
