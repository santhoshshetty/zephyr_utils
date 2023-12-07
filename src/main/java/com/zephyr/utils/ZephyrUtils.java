package com.zephyr.utils;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zephyr.apiUpdates.Authorization;
import com.zephyr.apiUpdates.ZAPIs;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// TODO: Auto-generated Javadoc
/**
 * The Class ZephyrUtils.
 * 
 * @author skumargoureshetty
 */
public class ZephyrUtils implements Zephyr {

	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(ZephyrUtils.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zephyr.utils.Zephyr#getExecutionsFromCycle(com.zephyr.utils.
	 * ExecutionParams, java.util.HashMap)
	 */
	public HashMap<String, ExecPojo> getExecutionsFromCycle(ExecutionParams params,
			HashMap<String, ExecPojo> executionsFromCycle) {
		try {
			Authorization zauth = new Authorization();
			String url = filterURL(ZAPIs.GET_TEST_CASE_FROM_CYCLE, ZAPIs.CYCLEID, params.cycleId);
			String queryParam = filterURL(ZAPIs.GET_TEST_CASE_FROM_CYCLE_QUERYPARAMS, ZAPIs.VERSIONID,
					params.versionId);
			queryParam = filterURL(queryParam, ZAPIs.PROJECTID, params.projectId);
			String finalURL = url.concat(queryParam);
			String token = zauth.generateToken(params.zephyrURL, params.accessKey, params.secretKey, params.accountId,
					finalURL, ZAPIs.GET);
			System.out.println("Token value is: " + token);
			String baseUrl = params.zephyrURL;
			String api = baseUrl.concat(finalURL);
			Response response = given().headers(getHeaders(params, token)).relaxedHTTPSValidation().get(api);
			if (response.statusCode() == ZAPIs.SUCCESSSTATUSCODE) {
				System.out.println("Success");
				System.out.println(response.getBody().asString());
				JsonObject obj = JsonParser.parseString(response.asString()).getAsJsonObject();
				JsonArray arr = obj.get(ZAPIs.TESTCASESCYCLE_ARRAYFILTER).getAsJsonArray();
				System.out.println("Number of test cases in cycle " + arr.size());
				for (JsonElement ob : arr) {
					ExecPojo exec = new ExecPojo();
					JsonObject sub = ob.getAsJsonObject().get(ZAPIs.TESTCASESCYCLE_EXEC).getAsJsonObject();
					exec.setExecutionId(sub.get(ZAPIs.TESTCASES_EXECID).getAsString());
					System.out.println("Exec Id is: " + sub.get(ZAPIs.TESTCASES_EXECID));
					exec.setIssueId(sub.get(ZAPIs.TESTCASES_ISSUEID).toString());
					System.out.println("Issue id is: " + sub.get(ZAPIs.TESTCASES_ISSUEID));
					executionsFromCycle.put(ob.getAsJsonObject().get(ZAPIs.TESTCASES_ISSUEKEY).getAsString(), exec);
					System.out.println("TC Id is: " + ob.getAsJsonObject().get(ZAPIs.TESTCASES_ISSUEKEY));
				}
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage());
			return null;
		}
		return executionsFromCycle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zephyr.utils.Zephyr#postTestCaseResult(java.util.HashMap,
	 * com.zephyr.utils.ExecutionParams, com.zephyr.utils.ExecutionResults)
	 */
	public HashMap<String, ExecPojo> postTestCaseResult(HashMap<String, ExecPojo> executions, ExecutionParams params,
			ExecutionResults execResults) {
		try {
			Authorization zauth = new Authorization();
			String url = filterURL(ZAPIs.POST_TEST_CASE_RESULT, ZAPIs.TESTCASES_EXECID,
					executions.get(execResults.getTestCaseId()).getExecutionId());
			String finalURL = url;
			String token = zauth.generateToken(params.zephyrURL, params.accessKey, params.secretKey, params.accountId,
					finalURL, ZAPIs.PUT);
			System.out.println("Token value is: " + token);
			String baseUrl = params.zephyrURL;
			String api = baseUrl.concat(finalURL);
			TestCasePayload payLoad = new TestCasePayload();
			payLoad.setIssueId(Integer.valueOf(executions.get(execResults.getTestCaseId()).getIssueId()));
			payLoad.setProjectId((Integer.valueOf(params.projectId)));
			payLoad.setVersionId(Integer.valueOf(params.versionId));
			payLoad.setCycleId(params.cycleId);
			Status status = new Status();
			status.setId(execResults.getTcStatus().getValue());
			payLoad.setStatus(status);
			payLoad.setComment(execResults.getComment());
			HashMap<String, String> headers = getHeaders(params, token);
			headers.put("Content-Type", "application/json");
			Response response = given().headers(headers).accept(ContentType.JSON).body(payLoad).when().relaxedHTTPSValidation().put(api);
			System.out.println("Test case result status: " + response.statusCode());
			if (response.statusCode() == 200) {
				System.out.println("Success");
			}
		} catch (Exception ex) {
			LOG.error("Exception occurred in posting the test case result");
			return null;
		}
		return executions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zephyr.utils.Zephyr#postTestStepResult(java.util.HashMap,
	 * com.zephyr.utils.ExecutionParams, java.lang.String,
	 * com.zephyr.utils.ExecutionDetails)
	 */
	public String postTestStepResult(HashMap<String, ExecPojo> executions, ExecutionParams params, String tcId,
			ExecutionDetails testStepExecDetails) {
		try {
			String stepId = executions.get(tcId).getStepId().get(testStepExecDetails.getOrderNumber() - 1)
					.getTestStepId();
			String stepResultId = executions.get(tcId).getStepId().get(testStepExecDetails.getOrderNumber() - 1)
					.getTestStepResultId();
			Authorization zauth = new Authorization();
			String url = filterURL(ZAPIs.POST_STEP_RESULT, ZAPIs.TESTSTEP_RESULTID, stepResultId);
			String finalURL = url;
			String baseUrl = params.zephyrURL;
			String api = baseUrl.concat(finalURL);
			String token = zauth.generateToken(params.zephyrURL, params.accessKey, params.secretKey, params.accountId,
					finalURL, ZAPIs.PUT);
			System.out.println("Token value is: " + token);
			HashMap<String, String> headers = getHeaders(params, token);
			headers.put("Content-Type", "application/json");
			TestStepPayload payLoad = new TestStepPayload();
			Status status = new Status();
			status.setId(testStepExecDetails.getStatus().getValue());
			payLoad.setExecutionId(executions.get(tcId).getExecutionId());
			payLoad.setIssueId(Integer.valueOf(executions.get(tcId).getIssueId()));
			payLoad.setStepId(stepId);
			payLoad.setStatus(status);
			Response response = given().headers(headers).body(payLoad).when().relaxedHTTPSValidation().put(api);
			if (response.statusCode() == 200) {
				System.out.println("Step Result Success");
			}
		} catch (Exception ex) {
			LOG.error("Exception occurred in posting the test step result");
			return null;
		}
		return "Success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zephyr.utils.Zephyr#getStepResultId(com.zephyr.utils.ExecutionParams,
	 * java.lang.String, java.util.HashMap)
	 */
	public HashMap<String, ExecPojo> getStepResultId(ExecutionParams params, String tcId,
			HashMap<String, ExecPojo> executionsFromCycle) {
		try {
			Authorization zauth = new Authorization();
			String url = ZAPIs.GET_TEST_STEP_RESULTS;
			String queryParam = filterURL(ZAPIs.GET_TEST_STEP_RESULTS_QUERYPARAMS, ZAPIs.TESTCASES_EXECID,
					executionsFromCycle.get(tcId).getExecutionId());
			queryParam = filterURL(queryParam, ZAPIs.TESTCASES_ISSUEID, executionsFromCycle.get(tcId).getIssueId());
			String finalURL = url.concat(queryParam);
			String token = zauth.generateToken(params.zephyrURL, params.accessKey, params.secretKey, params.accountId,
					finalURL, ZAPIs.GET);
			System.out.println("Token value is: " + token);
			String baseUrl = params.zephyrURL;
			String api = baseUrl.concat(finalURL);
			Response response = given().headers(getHeaders(params, token)).when().relaxedHTTPSValidation().get(api);
			if (response.statusCode() == 200) {
				JsonObject obj = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
				JsonArray arr = obj.get(ZAPIs.TESTSTEPS_RESULTID_LIST).getAsJsonArray();
				for (JsonElement elem : arr) {
					String stepExecId = elem.getAsJsonObject().get(ZAPIs.TESTSTEPS_STEPID).getAsString();
					String stepResultId = elem.getAsJsonObject().get(ZAPIs.TESTSTEPS_STEPRESULTID).getAsString();
					try {
						executionsFromCycle.get(tcId).getStepId().stream()
								.filter(id -> id.getTestStepId().equals(stepExecId)).findAny().get()
								.setTestStepResultId(stepResultId);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					System.out.println("Step ExecId: " + stepExecId);
					System.out.println("Step ResultId: " + stepResultId);
				}

			}
		} catch (Exception ex) {
			LOG.error("Exception occurred fetching step results");
			return null;
		}
		return executionsFromCycle;

	}

	public boolean verifyIfRecordExists(String value) {
		if (value.equals(null) || value == "")
			return false;
		return true;
	}

	public String postAttachmentComment(ExecutionParams params, String tcId, HashMap<String, ExecPojo> executions,
			ExecutionResults results, ExecutionDetails testStepExecDetails) {
		try {
			boolean executionAtTestCase = testStepExecDetails == null ? true : false;
			String testCaseExecutionId = null;
			String stepResultId = null;
			if (executionAtTestCase) {
				testCaseExecutionId = executions.get(tcId).getExecutionId();
			} else {
				stepResultId = executions.get(tcId).getStepId().get(testStepExecDetails.getOrderNumber() - 1)
						.getTestStepResultId();
			}
			Authorization zauth = new Authorization();
			String url = ZAPIs.POST_ATTACHMENT;
			String queryParam = filterURL(ZAPIs.POST_ATTACHMENT_QUERYPARAMS, ZAPIs.PROJECTID, params.projectId);
			queryParam = filterURL(queryParam, ZAPIs.VERSIONID, params.versionId);
			queryParam = filterURL(queryParam, ZAPIs.CYCLEID, params.cycleId);
			queryParam = filterURL(queryParam, ZAPIs.TESTCASES_ISSUEID, executions.get(tcId).getIssueId());

			queryParam = executionAtTestCase == true ? filterURL(queryParam, ZAPIs.ENTITYNAME, ZAPIs.CASEENTITYNAME)
					: filterURL(queryParam, ZAPIs.ENTITYNAME, ZAPIs.STEPENTITYNAME);
			queryParam = executionAtTestCase == true ? filterURL(queryParam, ZAPIs.ENTITYID, testCaseExecutionId)
					: filterURL(queryParam, ZAPIs.ENTITYID, stepResultId);

			if (executionAtTestCase) {
				queryParam = verifyIfRecordExists(results.getComment())
						? filterURL(queryParam, ZAPIs.COMMENT, results.getComment().replace(" ", "")).trim()
						: filterURL(queryParam, ZAPIs.COMMENT, "");
			} else {
				queryParam = verifyIfRecordExists(testStepExecDetails.getComment())
						? filterURL(queryParam, ZAPIs.COMMENT, testStepExecDetails.getComment().replace(" ", "")).trim()
						: filterURL(queryParam, ZAPIs.COMMENT, "");
			}
			String finalURL = url.concat(queryParam);
			String token = zauth.generateToken(params.zephyrURL, params.accessKey, params.secretKey, params.accountId,
					finalURL, ZAPIs.POST);
			System.out.println("Token value is: " + token);
			String baseUrl = params.zephyrURL;
			String api = baseUrl.concat(finalURL);
			File file = executionAtTestCase == true ? results.getAttachment() : testStepExecDetails.getAttachment();
			RequestSpecification request = given().headers(getHeaders(params, token)).multiPart(file.getName(), file,
					"multipart/form-data");
			Response response = request.relaxedHTTPSValidation().post(api);
			if (response.statusCode() == ZAPIs.SUCCESSSTATUSCODE) {
				System.out.println("Success");
			}

		} catch (Exception ex) {
			LOG.error("Exception occurred in posting attachment");
			return null;
		}

		return "Success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zephyr.utils.Zephyr#getStepsFromExecution(com.zephyr.utils.
	 * ExecutionParams, java.lang.String, java.util.HashMap)
	 */
	public HashMap<String, ExecPojo> getStepsFromExecution(ExecutionParams params, String tcId,
			HashMap<String, ExecPojo> executionsFromCycle) {
		try {
			Authorization zauth = new Authorization();
			String url = filterURL(ZAPIs.GET_TEST_STEPS_FOR_TESTCASE, ZAPIs.TESTCASES_ISSUEID,
					executionsFromCycle.get(tcId).getIssueId());
			String queryParam = filterURL(ZAPIs.GET_TEST_STEPS_FOR_TESTCASE_QUERYPARAMS, ZAPIs.PROJECTID,
					params.projectId);
			String finalURL = url.concat(queryParam);
			String token = zauth.generateToken(params.zephyrURL, params.accessKey, params.secretKey, params.accountId,
					finalURL, ZAPIs.GET);
			System.out.println("Token value is: " + token);
			String baseUrl = params.zephyrURL;
			String api = baseUrl.concat(finalURL);
			Response response = given().headers(getHeaders(params, token)).when().relaxedHTTPSValidation().get(api);
			if (response.statusCode() == ZAPIs.SUCCESSSTATUSCODE) {
				JsonArray arr = JsonParser.parseString(response.getBody().asString()).getAsJsonArray();
				List<StepIds> stepsList = new ArrayList<StepIds>();
				for (JsonElement elem : arr) {
					StepIds steps = new StepIds();
					String id = elem.getAsJsonObject().get(ZAPIs.TESTCASE_STEPID).toString();
					id = id.replace("\"", "");
					System.out.println("Id: " + id);
					String orderId = elem.getAsJsonObject().get(ZAPIs.TESTCASE_STEPORDERID).toString();
					steps.setOrderId(Integer.valueOf(orderId));
					steps.setTestStepId(id);
					System.out.println("Order Id: " + orderId);
					stepsList.add(steps);
				}
				executionsFromCycle.get(tcId).setStepId(stepsList);
			}
		} catch (Exception ex) {
			LOG.error("Exception occurred in reading test steps from the test case: " + ex.getMessage());
			return null;
		}
		return executionsFromCycle;
	}

	/**
	 * Filter URL.
	 *
	 * @param url
	 *            the url
	 * @param term
	 *            the term
	 * @param value
	 *            the value
	 * @return the string
	 */
	public String filterURL(String url, String term, String value) {
		String termToReplace = ZAPIs.OPEN_FLOWER_BRACES.concat(term).concat(ZAPIs.CLOSE_FLOWER_BRACES);
		return url.replace(termToReplace, value);
	}

	/**
	 * Gets the headers.
	 *
	 * @param params
	 *            the params
	 * @param token
	 *            the token
	 * @return the headers
	 */
	public HashMap<String, String> getHeaders(ExecutionParams params, String token) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", token);
		headers.put("zapiAccessKey", params.accessKey);
		return headers;
	}

}
