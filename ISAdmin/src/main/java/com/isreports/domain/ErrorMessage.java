package com.isreports.domain;

/**
 * Model class representing a error message
 * 
 * @author LsnSoft
 */
public class ErrorMessage {

	/**
	 * Used to display a status code
	 */
	private int code;

	/**
	 * Represents a error message
	 */
	private String errorMessage;

	public ErrorMessage(int code, String errorMessage) {
		super();
		this.code = code;
		this.errorMessage = errorMessage;
	}

	public ErrorMessage() {

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", errorMessage=" + errorMessage
				+ "]";
	}

}
