package com.akhil.airportinfo.service;

import java.util.List;

import com.akhil.airportinfo.model.Airport;
/**
 * 
 * @author akhil
 *
 */
public interface AirportService {

	Airport getAirportByIata(String airportId);
	
	List<Airport> getAirportByName(String airportName);
	
}
