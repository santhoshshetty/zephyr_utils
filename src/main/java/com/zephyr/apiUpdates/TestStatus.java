package com.zephyr.apiUpdates;

public enum TestStatus {
	UNEXECUTED(-1), PASS(1), FAIL(2), WIP(3), BLOCKED(4), OUT_OF_SCOPE(5);

	private final int value;

	private TestStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
