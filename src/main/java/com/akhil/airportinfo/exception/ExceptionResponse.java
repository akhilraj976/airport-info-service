package com.akhil.airportinfo.exception;

/**
 * 
 * @author akhil
 *
 */
public class ExceptionResponse {

	private String errorMsg;
	private String httpStatus;

	public ExceptionResponse(String errorMsg, String httpStatus) {
		super();
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

}
