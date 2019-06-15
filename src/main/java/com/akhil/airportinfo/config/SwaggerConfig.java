package com.akhil.airportinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author akhil
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket apiDocket() {

		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(getApiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.akhil.airportinfo.controller"))
					.paths(PathSelectors.any())
					.build();
	}

	private ApiInfo getApiInfo() {

		return new ApiInfoBuilder()
					.title("Airport Info API Doc")
					.description("This is the documentation of Airport Info APIs")
					.version("1.0.0")
					.contact(new Contact("AkhilPuram", "", "akhilrajpuram@gmail.com"))
					.build();
	}
}
