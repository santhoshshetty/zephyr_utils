package com.zephyr.utils;

import static io.restassured.RestAssured.given;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zephyr.apiUpdates.Authorization;
import com.zephyr.apiUpdates.ZAPIs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ZephyrPost {

	/**HashMap<String, ExecPojo> executionsFromCycle = new HashMap<String, ExecPojo>();
	String baseProjectPath = System.getProperty("user.dir");

	public void postTestCaseResult() throws Exception {
		Authorization zauth = new Authorization();
		String token = zauth.generateToken("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39",
				"/public/rest/api/1.0/execution/c416fc49-8410-4862-856c-0c9263c4c92a", ZAPIs.PUT);
		System.out.println("Token value is: " + token);
		String baseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		String endPoint = "/public/rest/api/1.0/execution/c416fc49-8410-4862-856c-0c9263c4c92a";
		String api = baseUrl.concat(endPoint);
		String payLoad1 = new String(
				Files.readAllBytes(Paths.get(baseProjectPath.concat("/src/main/resources/testcaseresult.json"))));
		TestCasePayload payLoad = new TestCasePayload();
		payLoad.setIssueId(392790);
		payLoad.setProjectId(24380);
		payLoad.setVersionId(33665);
		payLoad.setCycleId("12b75872-6dd7-4a44-80cc-c43be64f4686");
		Status status = new Status();
		status.setId(1);
		payLoad.setStatus(status);
		payLoad.setComment("Test Comment");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", token);
		headers.put("zapiAccessKey",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F");
		Response response = given().headers(headers).accept(ContentType.JSON).body(payLoad).when().put(api);
		System.out.println(response.statusCode());
		if (response.statusCode() == 200) {
			System.out.println("Success");
		}
	}

	public void postTestStepResult() throws Exception {
		Authorization zauth = new Authorization();
		String token = zauth.generateToken("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39",
				"/public/rest/api/1.0/stepresult/66010758-e664-40bc-8e6d-b4283fb01316", ZAPIs.PUT);
		System.out.println("Token value is: " + token);
		String baseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		String endPoint = "/public/rest/api/1.0/stepresult/66010758-e664-40bc-8e6d-b4283fb01316";
		String api = baseUrl.concat(endPoint);
		String payLoad = new String(
				Files.readAllBytes(Paths.get(baseProjectPath.concat("/src/main/resources/teststepresult2.json"))));
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", token);
		headers.put("zapiAccessKey",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F");
		Response response = given().headers(headers).body(payLoad).when().put(api);
		if (response.statusCode() == 200) {
			System.out.println("Success");
		}
	}

	public void postTestStepResult1() throws Exception {
		Authorization zauth = new Authorization();
		String token = zauth.generateToken("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39",
				"/public/rest/api/1.0/stepresult/68b8fd54-2071-4269-8a49-0ba417ab1708", ZAPIs.PUT);
		System.out.println("Token value is: " + token);
		String baseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		String endPoint = "/public/rest/api/1.0/stepresult/68b8fd54-2071-4269-8a49-0ba417ab1708";
		String api = baseUrl.concat(endPoint);
		String payLoad = new String(
				Files.readAllBytes(Paths.get(baseProjectPath.concat("/src/main/resources/teststepresult.json"))));
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", token);
		headers.put("zapiAccessKey",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F");
		Response response = given().headers(headers).body(payLoad).when().put(api);
		if (response.statusCode() == 200) {
			System.out.println("Success");
		}
	}

	public void getStepResultId() throws Exception {
		Authorization zauth = new Authorization();
		String token = zauth.generateToken("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39",
				"/public/rest/api/1.0/stepresult/search?executionId=c416fc49-8410-4862-856c-0c9263c4c92a&issueId=392790",
				ZAPIs.PUT);
		System.out.println("Token value is: " + token);
		String baseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		String endPoint = "/public/rest/api/1.0/stepresult/search?executionId=c416fc49-8410-4862-856c-0c9263c4c92a&issueId=392790";
		String api = baseUrl.concat(endPoint);
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", token);
		headers.put("zapiAccessKey",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F");
		Response response = given().headers(headers).get(api);
		if (response.statusCode() == 200) {
			System.out.println("Success");

		}

	}

	public void getStepsFromExecution() throws Exception {
		Authorization zauth = new Authorization();
		String token = zauth.generateToken("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39",
				"/public/rest/api/1.0/teststep/392790?projectId=24380", ZAPIs.GET);
		System.out.println("Token value is: " + token);
		String baseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		String endPoint = "/public/rest/api/1.0/teststep/392790?projectId=24380";
		String api = baseUrl.concat(endPoint);
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", token);
		headers.put("zapiAccessKey",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F");
		Response response = given().headers(headers).get(api);
		if (response.statusCode() == 200) {
			System.out.println("Success");
		}
	}

	public void getExecutionsFromCycle() throws Exception {
		Authorization zauth = new Authorization();
		String token = zauth.generateToken("https://prod-api.zephyr4jiracloud.com/connect",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F",
				"zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg", "5f2434dd6c9d820022c25b39",
				"/public/rest/api/1.0/executions/search/cycle/12b75872-6dd7-4a44-80cc-c43be64f4686?versionId=33665&projectId=24380",
				ZAPIs.GET);
		System.out.println("Token value is: " + token);
		String baseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		String endPoint = "/public/rest/api/1.0/executions/search/cycle/12b75872-6dd7-4a44-80cc-c43be64f4686?versionId=33665&projectId=24380";
		String api = baseUrl.concat(endPoint);
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", token);
		headers.put("zapiAccessKey",
				"amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F");
		HashMap<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("versionId", "33665");
		queryParams.put("projectId", "24380");
		Response response = given().headers(headers).get(api);
		if (response.statusCode() == 200) {
			System.out.println("Success");
			System.out.println(response.getBody().asString());
			JsonObject obj = JsonParser.parseString(response.asString()).getAsJsonObject();
			JsonArray arr = obj.get("searchObjectList").getAsJsonArray();
			System.out.println(arr.size());
			for (JsonElement ob : arr) {
				ExecPojo exec = new ExecPojo();
				JsonObject sub = ob.getAsJsonObject().get("execution").getAsJsonObject();
				exec.setExecutionId(sub.get("id").toString());
				System.out.println("Exec Id is: " + sub.get("id"));
				exec.setIssueId(sub.get("issueId").toString());
				System.out.println("Issue id is: " + sub.get("issueId"));
				executionsFromCycle.put(ob.getAsJsonObject().get("issueKey").toString(), exec);
				System.out.println("TC Id is: " + ob.getAsJsonObject().get("issueKey"));
			}
		}
	}

	// **************Cleaned up code*****************

	public String trimURL(String url, String term) {
		String execTerm = ZAPIs.OPEN_FLOWER_BRACES.concat(term).concat(ZAPIs.CLOSE_FLOWER_BRACES);
		return ZAPIs.GET_TEST_CASE_FROM_CYCLE.replace(execTerm, term);
	}

	public HashMap<String, String> getHeaders(ExecutionParams params, String token) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", token);
		headers.put("zapiAccessKey", params.accessKey);
		return headers;
	}
*/
}
