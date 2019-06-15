package com.akhil.airportinfo.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.akhil.airportinfo.model.AirportInfo;
/**
 * 
 * @author akhil
 *
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jf, StepBuilderFactory sf, ItemReader<AirportInfo> itemReader,
			ItemProcessor<AirportInfo, AirportInfo> itemProcessor, ItemWriter<AirportInfo> itemWriter) {
		
		Step step = sf.get("ETL-file-load")
				.<AirportInfo, AirportInfo>chunk(500)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
				
		return jf.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();
	}
	
	@Bean
	public FlatFileItemReader<AirportInfo> fileItemReader(@Value("${input}") Resource input){
		FlatFileItemReader<AirportInfo> fit = new FlatFileItemReader<>();
		fit.setResource(input);
		fit.setName("CSV-Reader");
		fit.setLinesToSkip(1);
		fit.setLineMapper(lineMapper());
		return fit;
		
	}

	@Bean
	public LineMapper<AirportInfo> lineMapper() {
		DefaultLineMapper<AirportInfo> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[]{"id", "ident", "air_type", "name", "latitude_deg", "longitude_deg", "elevation_ft", "continent", "iso_country", "iso_region", "municipality", "scheduled_service", "gps_code", "iata_code", "local_code",  "home_link", "wikipedia_link", "keywords"});
		
		BeanWrapperFieldSetMapper<AirportInfo> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(AirportInfo.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
}
