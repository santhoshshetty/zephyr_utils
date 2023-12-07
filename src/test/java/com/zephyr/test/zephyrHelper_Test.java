package com.zephyr.test;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zephyr.apiUpdates.TestStatus;
import com.zephyr.utils.ExecutionDetails;
import com.zephyr.utils.ExecutionResults;
import com.zephyr.utils.ZExecution;

public class zephyrHelper_Test {
	
	private final static Logger LOG = LoggerFactory.getLogger(zephyrHelper_Test.class);

	static ZExecution exec;
	static ExecutionResults testResults = new ExecutionResults();
	
	public static void main(String[] args) throws Exception {
		
		//before suite
		exec = new ZExecution("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39", "800", "24380", "33665",
				"12b75872-6dd7-4a44-80cc-c43be64f4686");
		exec.initializeTest();
		
		testResults.setTestCaseId("CDM-112");
		
		//test1
		ExecutionDetails details = new ExecutionDetails();
		LOG.info("Execution of Test case1 - start");
		Thread.sleep(2000);
		LOG.info("Execution of Test case1 - complete");
		details.setStatus(TestStatus.PASS).setComment("Test Step 1 comment")
				.setAttachment(new File("C:\\Backup\\ApiAutomationDemo-2020-09-28-10-43-22-872-0.html"));
		testResults.getExecDetails().add(details);
		
		//Test 2
		ExecutionDetails details1 = new ExecutionDetails();
		LOG.info("Execution of Test case2 - start");
		Thread.sleep(2000);
		LOG.info("Execution of Test case2 - complete");
		details1.setStatus(TestStatus.PASS).setComment("Test Step 2 comment")
				.setAttachment(new File("C:\\Backup\\ApiAutomationDemo-2020-09-28-10-43-22-872-0.html"));
		testResults.getExecDetails().add(details1);
		
		//After Test
		testResults.setTcStatus(TestStatus.PASS);
		testResults.setComment("Test case comment");
		testResults.setAttachment(new File("C:\\Backup\\ApiAutomationDemo-2020-09-28-10-43-22-872-0.html"));
		testResults.setOrderIdMaintained(true);
		exec.postTestResults(testResults);
		System.exit(0);
	}
	
}
