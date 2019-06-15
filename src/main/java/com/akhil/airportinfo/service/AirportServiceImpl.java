package com.akhil.airportinfo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.akhil.airportinfo.exception.AirportException;
import com.akhil.airportinfo.model.Airport;
import com.akhil.airportinfo.model.AirportInfo;
import com.akhil.airportinfo.repository.AirportDao;

/**
 * 
 * @author akhil
 *
 */
@Service
public class AirportServiceImpl implements AirportService {

	private static final Logger log = LoggerFactory.getLogger(AirportServiceImpl.class);

	@Autowired
	private AirportDao dao;

	@Override
	public Airport getAirportByIata(String airportId) {
		log.info("Deriving airport info for a given airport code");
		AirportInfo info = dao.getAirportByIata(airportId);
		if(info==null){
			throw new AirportException("No Airport found with the IATA Code", HttpStatus.NOT_FOUND);
		}
		return createAirportResponse(info);
	}
	
	@Override
	public List<Airport> getAirportByName(String airportName) {
		log.info("Deriving list of airports for a given name");
		List<AirportInfo> airportNames = dao.getAirportByName(airportName);
		List<Airport> airports = new ArrayList<>();
		for (AirportInfo info : airportNames) {
			Airport airport = createAirportResponse(info);
			airports.add(airport);
		}
		if(airports.isEmpty()){
			throw new AirportException("No Matches found with the given name", HttpStatus.NOT_FOUND);
		}
		return airports;
	}


	private Airport createAirportResponse(AirportInfo info) {
		Airport airport = new Airport();
		airport.setName(info.getName());
		airport.setCity(info.getMunicipality());
		airport.setCountry(info.getIso_country());
		airport.setIata(info.getIata_code());
		airport.setLatitude(info.getLatitude_deg());
		airport.setLongitude(info.getLongitude_deg());
		airport.setIcao(info.getIdent());
		return airport;
	}


}
