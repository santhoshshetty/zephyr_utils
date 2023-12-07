package com.zephyr.utils;

public class ExecutionParams {

	String zephyrURL;
	String accessKey;
	String secretKey;
	String accountId;
	String longetivity;
	String projectId;
	String versionId;
	String cycleId;

	public void setExecutionParams(String zephyrURL, String accessKey, String secretKey, String accountId,
			String longetivity, String projectId, String versionId, String cycleId) {
		this.zephyrURL = zephyrURL;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.accountId = accountId;
		this.longetivity = longetivity;
		this.projectId = projectId;
		this.versionId = versionId;
		this.cycleId = cycleId;
	}

}
