package com.akhil.airportinfo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akhil.airportinfo.controller.AirportController;

/**
 * 
 * @author akhil
 *
 */
@RestControllerAdvice(basePackageClasses = AirportController.class)
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Airports Not Found")
	@ExceptionHandler(AirportException.class)
	public ResponseEntity<Object> handleIOException(AirportException ae) {
		logger.error("AirportException handler executed");
		ExceptionResponse resp = new ExceptionResponse(ae.getMessage(), ae.getHttpStatusCode().toString());
		
		return new ResponseEntity<>(resp,
				ae.getHttpStatusCode() != null ? ae.getHttpStatusCode() : HttpStatus.INTERNAL_SERVER_ERROR);
	}
}