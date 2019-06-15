package com.akhil.airportinfo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.airportinfo.service.AirportServiceImpl;

/**
 * 
 * @author akhil
 *
 */
@RestController
@RequestMapping("/load")
public class LoadController {

	private static final Logger log = LoggerFactory.getLogger(LoadController.class);
	
	@Autowired
	JobLauncher launcher;
	
	@Autowired
	Job job;
	
	@GetMapping	
	public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		JobParameters params = new JobParameters();
		JobExecution execution = launcher.run(job, params);
		while(execution.isRunning())
			log.info("Still running");
		return execution.getStatus();
	}
	
	
}
