package com.akhil.airportinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akhil.airportinfo.model.AirportInfo;
/**
 * 
 * @author akhil
 *
 */
public interface AirportRepository extends JpaRepository<AirportInfo, Integer>{

	@Query("select a from AirportInfo a where lower(a.name) like lower(concat('%', ?1,'%')) and a.iata_code is not null and a.iata_code!='' ")
	List<AirportInfo> findByName(String airportName);
	
	@Query("select a from AirportInfo a where lower(a.iata_code)= lower(?1)")
	AirportInfo find(String iata);
}
