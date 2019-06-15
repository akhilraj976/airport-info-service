package com.akhil.airportinfo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akhil.airportinfo.model.AirportInfo;
/**
 * 
 * @author akhil
 *
 */
@Repository
public class AirportDao {

	@Autowired
	AirportRepository repo;
	
	public AirportInfo getAirportByIata(String iata){
		return repo.find(iata);
	}
	
	public List<AirportInfo> getAirportByName(String name){
		return repo.findByName(name);
	}
}
