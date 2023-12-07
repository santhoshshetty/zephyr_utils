package com.zephyr.utils;

import java.util.HashMap;

public interface Zephyr {

	public HashMap<String, ExecPojo> getExecutionsFromCycle(ExecutionParams params,
			HashMap<String, ExecPojo> executionsFromCycle);

	public HashMap<String, ExecPojo> postTestCaseResult(HashMap<String, ExecPojo> executions, ExecutionParams params,
			ExecutionResults execResults);

	public String postTestStepResult(HashMap<String, ExecPojo> executions, ExecutionParams params, String tcId,
			ExecutionDetails testStepExecDetails);

	public HashMap<String, ExecPojo> getStepResultId(ExecutionParams params, String tcId,
			HashMap<String, ExecPojo> executionsFromCycle);

	public HashMap<String, ExecPojo> getStepsFromExecution(ExecutionParams params, String tcId,
			HashMap<String, ExecPojo> executionsFromCycle);

}
