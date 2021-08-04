package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.ToString;

@ToString
public class ResponseError {
	private String apiName;
	private String version;
	private String messageId;
	private String correlationId;
	private LocalDateTime timestamp;
	private String errorCode;
	private String errorType;
	private String errorSubType;
	private String errorMessage;
	


	public ResponseError() {
		super();
	}

	public ResponseError(String apiName, String version, String messageId, String correlationId, LocalDateTime timestamp,
			String errorCode, String errorType, String errorSubType, String errorMessage) {
		super();
		this.apiName = apiName;
		this.version = version;
		this.messageId = messageId;
		this.correlationId = correlationId;
		this.timestamp = timestamp;
		this.errorCode = errorCode;
		this.errorType = errorType;
		this.errorSubType = errorSubType;
		this.errorMessage = errorMessage;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorSubType() {
		return errorSubType;
	}

	public void setErrorSubType(String errorSubType) {
		this.errorSubType = errorSubType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
