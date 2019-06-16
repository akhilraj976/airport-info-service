package com.akhil.airportinfo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.akhil.airportinfo.model.AirportInfo;
import com.akhil.airportinfo.repository.AirportRepository;

/**
 * 
 * @author akhil
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class AirportsApplicationTests {

	@MockBean
	private AirportRepository airRepo;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAirportsByNameTestSuccess() throws Exception {
		List<AirportInfo> airportNames = new ArrayList<>();
		AirportInfo info = new AirportInfo();
		info.setName("Berlin");
		info.setMunicipality("Berlin");
		info.setIso_country("DE");
		info.setIata_code("SXF");
		info.setLatitude_deg(1);
		info.setLongitude_deg(1);
		info.setIdent("");
		airportNames.add(info);
		
		Mockito.when(airRepo.findByName(Mockito.anyString())).thenReturn(airportNames);

		mockMvc.perform(get("/airport/v1/name/berlin")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getAirportsByNameTestFailure() throws Exception {
		Mockito.when(airRepo.findByName(Mockito.anyString())).thenReturn(null);

		mockMvc.perform(get("/airport/v1/name/afdaf")).andExpect(status().isNotFound()).andDo(print());
	}
}
