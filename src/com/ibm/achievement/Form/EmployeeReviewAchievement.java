package com.ibm.achievement.Form;

import java.io.Serializable;

public class EmployeeReviewAchievement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String employeeName;
	private String employeeId;
	private String existingAcievement;
	private String downloadFile1;
	private String downloadFile2;
	private String approver;
	private String achievementType;
	private String project;
	private String fromDate;
	private String toDate;
	private String achievementTxt;
	private String category;
	private String pointValue;
	private String comments;
	private byte[] byteArr1;
	private byte[] byteArr2;

	public byte[] getByteArr1() {
		return byteArr1;
	}

	public void setByteArr1(byte[] byteArr1) {
		this.byteArr1 = byteArr1;
	}

	public byte[] getByteArr2() {
		return byteArr2;
	}

	public void setByteArr2(byte[] byteArr2) {
		this.byteArr2 = byteArr2;
	}

	public String getDownloadFile1() {
		return downloadFile1;
	}

	public void setDownloadFile1(String downloadFile1) {
		this.downloadFile1 = downloadFile1;
	}

	public String getDownloadFile2() {
		return downloadFile2;
	}

	public void setDownloadFile2(String downloadFile2) {
		this.downloadFile2 = downloadFile2;
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

	public String getExistingAcievement() {
		return existingAcievement;
	}

	public void setExistingAcievement(String existingAcievement) {
		this.existingAcievement = existingAcievement;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getAchievementType() {
		return achievementType;
	}

	public void setAchievementType(String achievementType) {
		this.achievementType = achievementType;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
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

	public String getAchievementTxt() {
		return achievementTxt;
	}

	public void setAchievementTxt(String achievementTxt) {
		this.achievementTxt = achievementTxt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPointValue() {
		return pointValue;
	}

	public void setPointValue(String pointValue) {
		this.pointValue = pointValue;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
