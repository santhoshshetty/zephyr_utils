package com.zephyr.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zephyr.apiUpdates.TestStatus;

public class ExecutionResults {

	private String testCaseId;

	private TestStatus tcStatus;

	private String comment;
	
	private File attachment;
	
	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	private Boolean orderIdMaintained;

	public Boolean getOrderIdMaintained() {
		return orderIdMaintained;
	}

	public void setOrderIdMaintained(Boolean orderIdMaintained) {
		this.orderIdMaintained = orderIdMaintained;
	}

	public ExecutionResults() {
		execDetails = new ArrayList<ExecutionDetails>();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public TestStatus getTcStatus() {
		return tcStatus;
	}

	public void setTcStatus(TestStatus tcStatus) {
		this.tcStatus = tcStatus;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public List<ExecutionDetails> getExecDetails() {
		return execDetails;
	}

	public void setExecDetails(List<ExecutionDetails> execDetails) {
		this.execDetails = execDetails;
	}

	private List<ExecutionDetails> execDetails;
}
