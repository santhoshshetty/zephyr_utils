package com.zephyr.apiUpdates;

public class ZAPIs {
	public static final String GET_TEST_STEPS_FOR_TESTCASE = "/public/rest/api/1.0/teststep/{issueId}";
	public static final String GET_TEST_STEPS_FOR_TESTCASE_QUERYPARAMS = "?projectId={projectId}";
	public static final String GET_TEST_CASE_FROM_CYCLE = "/public/rest/api/1.0/executions/search/cycle/{cycleId}";
	public static final String GET_TEST_CASE_FROM_CYCLE_QUERYPARAMS = "?versionId={versionId}&projectId={projectId}";
	public static final String POST_TEST_CASE_RESULT = "/public/rest/api/1.0/execution/{id}";
	public static final String GET_TEST_STEP_RESULTS = "/public/rest/api/1.0/stepresult/search";
	public static final String GET_TEST_STEP_RESULTS_QUERYPARAMS = "?executionId={id}&issueId={issueId}";
	public static final String POST_STEP_RESULT = "/public/rest/api/1.0/stepresult/{id}";
	public static final String CREATE_EXECUTION = "/public/rest/api/1.0/execution";
	public static final String POST_ATTACHMENT = "/public/rest/api/1.0/attachment";
	public static final String POST_ATTACHMENT_QUERYPARAMS = "?issueId={issueId}&projectId={projectId}&versionId={versionId}&cycleId={cycleId}&entityName={entityName}&entityId={entityId}&comment={comment}";

	public static final String STEPLEVELATTACHMENT="STEP";
	public static final String CASELEVELATTACHMENT="CASE";
	
	public static final String CYCLEID = "cycleId";
	public static final String PROJECTID = "projectId";
	public static final String VERSIONID = "versionId";
	public static final String ENTITYID = "entityId";
	public static final String ENTITYNAME = "entityName";
	public static final String COMMENT = "comment";

	public static final String STEPENTITYNAME = "stepResult";
	public static final String CASEENTITYNAME = "execution";

	public static final String OPEN_FLOWER_BRACES = "{";
	public static final String CLOSE_FLOWER_BRACES = "}";

	public static final int SUCCESSSTATUSCODE = 200;
	public static final int SUCCESSSTATUSCODE_CREATE = 201;


	// From Cycle
	public static final String TESTCASESCYCLE_EXEC = "execution";
	public static final String TESTCASESCYCLE_ARRAYFILTER = "searchObjectList";
	public static final String TESTCASES_EXECID = "id";
	public static final String TESTCASES_ISSUEID = "issueId";
	public static final String TESTCASES_ISSUEKEY = "issueKey";

	// Get Test Steps From Test cases
	public static final String TESTCASE_STEPID = "id";
	public static final String TESTCASE_STEPORDERID = "orderId";

	// StepResults
	public static final String TESTSTEPS_RESULTID_LIST = "stepResults";
	public static final String TESTSTEPS_STEPID = "stepId";
	public static final String TESTSTEPS_STEPRESULTID = "id";

	public static final String TESTSTEP_RESULTID = "id";

	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static final String DELETE = "DELETE";
	public static final String PATCH = "PATCH";

}
