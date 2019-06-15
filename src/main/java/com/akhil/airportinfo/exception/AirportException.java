package com.akhil.airportinfo.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author akhil
 *
 */
public class AirportException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatusCode;

	public AirportException(String message, HttpStatus httpStatusCode) {
		super();
		this.message = message;
		this.httpStatusCode = httpStatusCode;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getMessage() {
		return message;
	}

}
