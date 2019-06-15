package com.akhil.airportinfo.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.akhil.airportinfo.model.AirportInfo;
/**
 * 
 * @author akhil
 *
 */
@Component
public class Processor implements ItemProcessor<AirportInfo, AirportInfo>{

	@Override
	public AirportInfo process(AirportInfo airport) throws Exception {
		return airport;
	}

}
