package com.akhil.airportinfo.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akhil.airportinfo.model.AirportInfo;
import com.akhil.airportinfo.repository.AirportRepository;
/**
 * 
 * @author akhil
 *
 */
@Component
public class DBWriter implements ItemWriter<AirportInfo>{

	@Autowired
	private AirportRepository repo;
	
	private static final Logger log = LoggerFactory.getLogger(AirportRepository.class);
	
	@Override
	public void write(List<? extends AirportInfo> airports) throws Exception {
		log.info("Creating new Airport: {}" , airports);
		
		repo.saveAll(airports);
	}

}
