package com.zephyr.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;

import io.restassured.path.json.JsonPath;

public class ZExecution {

	private final static Logger LOG = LoggerFactory.getLogger(ZExecution.class);

	ExecutionParams params;
	ZephyrUtils utils = new ZephyrUtils();
	volatile HashMap<String, ExecPojo> executionsFromCycle;
	
	public ZExecution(String zephyrURL, String accessKey, String secretKey, String accountId, String longetivity,
			String projectId, String versionId, String cycleId) throws Exception {
		initializeLogger();
		params = new ExecutionParams();
		params.setExecutionParams(zephyrURL, accessKey, secretKey, accountId, longetivity, projectId, versionId,
				cycleId);
		JsonObject obj=new JsonObject();
		executionsFromCycle = new HashMap<String, ExecPojo>();
	}

	public void initializeLogger() throws Exception {
		Properties properties = new Properties();
		InputStream configStream = this.getClass().getResourceAsStream("/log4j2.properties");
		properties.load(configStream);
		configStream.close();
	}

	public boolean initializeTest() throws Exception {
		synchronized (this) {
			LOG.info("Initializing the test - Read test cases from Cycle");
			executionsFromCycle = utils.getExecutionsFromCycle(params, executionsFromCycle);
			if (executionsFromCycle == null) {
				LOG.error("Exception occurred in reading the test cases from cycle");
				return false;
			} else {
				return true;
			}
		}

	}

	public void postTestResults(ExecutionResults execResults) throws Exception {
		synchronized (this) {
			executionsFromCycle = utils.getStepsFromExecution(params, execResults.getTestCaseId(), executionsFromCycle);
			if (executionsFromCycle == null) {
				LOG.error("Get Steps from execution failed..");
				return;
			}
			executionsFromCycle = utils.getStepResultId(params, execResults.getTestCaseId(), executionsFromCycle);
			if (executionsFromCycle == null) {
				LOG.error("Get Steps Results from execution failed..");
				return;
			}
			executionsFromCycle = utils.postTestCaseResult(executionsFromCycle, params, execResults);
			if (executionsFromCycle == null) {
				LOG.error("Post test case result failed..");
				return;
			}
			String postTestCaseAttachment = utils.postAttachmentComment(params, execResults.getTestCaseId(),
					executionsFromCycle, execResults, null);
			if (postTestCaseAttachment == null) {
				LOG.error("Post attachment failed..");
				return;
			}
			ExecutorService service = Executors.newFixedThreadPool(10);
			if (execResults.getOrderIdMaintained() == true) {
				for (int i = 1; i <= execResults.getExecDetails().size(); i++) {
					execResults.getExecDetails().get(i - 1).setOrderNumber(i);
				}
			} else {
				System.out.println("Indexes are already set");
			}
			List<ExecutionDetails> stepExecutions = execResults.getExecDetails();
			List<CompletableFuture<String>> list = new ArrayList<CompletableFuture<String>>();
			long startTime = System.nanoTime();
			/*
			 * stepExecutions.forEach( step -> utils.postTestStepResult(executionsFromCycle,
			 * params, execResults.getTestCaseId(), step));
			 */
			try {
				stepExecutions
						.forEach(step -> list.add(CompletableFuture
								.supplyAsync(() -> utils.postTestStepResult(executionsFromCycle, params,
										execResults.getTestCaseId(), step), service)
								.thenApplyAsync(stepRes -> utils.postAttachmentComment(params,
										execResults.getTestCaseId(), executionsFromCycle, execResults, step),
										service)));

			} catch (Exception ex) {
				LOG.error("Exception occurred in posting the test step results");
				return;
			}

			/*
			 * stepExecutions.forEach(step -> utils.postAttachmentComment(params,
			 * execResults.getTestCaseId(), executionsFromCycle, execResults, step));
			 */

			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("Total Time: " + totalTime);
			long seconds = TimeUnit.NANOSECONDS.toSeconds(totalTime);
			System.out.println("Total time in seconds: " + seconds);
			System.out.println("Execution Results are: "
					+ list.stream().map(CompletableFuture::join).collect(Collectors.toList()));
		}
	}
}
