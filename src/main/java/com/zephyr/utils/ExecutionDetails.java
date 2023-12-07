package com.zephyr.utils;

import java.io.File;

import com.zephyr.apiUpdates.TestStatus;

public class ExecutionDetails {

	private TestStatus status;

	private Integer orderNumber;

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public ExecutionDetails setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}

	public TestStatus getStatus() {
		return status;
	}

	public ExecutionDetails setStatus(TestStatus status) {
		this.status = status;
		return this;
	}

	private String comment;

	private File attachment;

	public String getComment() {
		return comment;
	}

	public ExecutionDetails setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public File getAttachment() {
		return attachment;
	}

	public ExecutionDetails setAttachment(File attachment) {
		this.attachment = attachment;
		return this;
	}

}
