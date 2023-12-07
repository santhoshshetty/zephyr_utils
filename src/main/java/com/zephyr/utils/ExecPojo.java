package com.zephyr.utils;

import java.util.List;

public class ExecPojo {

	String executionId;

	String issueId;

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	
	List<StepIds> stepId;

	public List<StepIds> getStepId() {
		return stepId;
	}

	public void setStepId(List<StepIds> stepId) {
		this.stepId = stepId;
	}

	
}
