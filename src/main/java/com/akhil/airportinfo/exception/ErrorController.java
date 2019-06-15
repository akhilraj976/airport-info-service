package com.akhil.airportinfo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author akhil
 *
 */

@Controller
public class ErrorController extends BasicErrorController implements Ordered {

	private static final String MSG = "message";

	public ErrorController(ServerProperties serverProperties) {
		super(new DefaultErrorAttributes(), serverProperties.getError());
	}

	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest req) {
		HttpStatus status = getStatus(req);
		if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			return ResponseEntity.status(status)
					.body(prepareResponseMap(MSG, "Request execution failed due to Internal Server Error"));
		} else if (status.equals(HttpStatus.BAD_REQUEST)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "Request resource invalid"));
		} else if (status.equals(HttpStatus.METHOD_NOT_ALLOWED)) {
			return ResponseEntity.status(status)
					.body(prepareResponseMap(MSG, "Request method " + req.getMethod() + " not supported"));
		} else if (status.equals(HttpStatus.UNAUTHORIZED)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "Unauthorized"));
		} else if (status.equals(HttpStatus.FORBIDDEN)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "Access denied for requested resource"));
		} else if (status.equals(HttpStatus.NOT_FOUND)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "Request resource not available"));
		} else if (status.equals(HttpStatus.UNSUPPORTED_MEDIA_TYPE)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "MediaType not supported"));
		} else if (status.equals(HttpStatus.NOT_ACCEPTABLE)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "Request mediaType not available"));
		} else if (status.equals(HttpStatus.SERVICE_UNAVAILABLE)) {
			return ResponseEntity.status(status).body(prepareResponseMap(MSG, "Service Unavailable"));
		}
		return super.error(req);

	}

	private Map<String, Object> prepareResponseMap(String key, String value) {
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		return map;
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
