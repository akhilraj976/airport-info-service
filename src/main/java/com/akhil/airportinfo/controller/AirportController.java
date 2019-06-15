package com.akhil.airportinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.airportinfo.model.Airport;
import com.akhil.airportinfo.service.AirportService;

/**
 * 
 * @author akhil
 *
 */
@RestController
@RequestMapping(produces = "application/json", value = "/airport")
public class AirportController {

	@Autowired
	AirportService airportService;

	@GetMapping("/v1/name/{airportName}")
	public ResponseEntity<List<Airport>> getAirportByName(@PathVariable String airportName) {
		return new ResponseEntity<>(airportService.getAirportByName(airportName), HttpStatus.OK);
	}

	@GetMapping("/v1/iata/{airportId}")
	public ResponseEntity<Airport> getAirportByIATA(@PathVariable String airportId) {
		return new ResponseEntity<>(airportService.getAirportByIata(airportId), HttpStatus.OK);
	}
	
}
