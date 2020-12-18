package com.br.nlw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.br.nlw.domain.lesson.Lesson;

@SpringBootApplication
public class NlwBackendApplication implements RepositoryRestConfigurer {

	public static final Logger logger = LoggerFactory.getLogger(NlwBackendApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(NlwBackendApplication.class, args);
		logger.info("...'1' nlw Running ...");
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Lesson.class);
		
		config.getCorsRegistry()
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE");
		
		logger.info("...'2' nlw Repository CORS running ...");
		
//		config.getExposureConfiguration()
//			.forDomainType(AppUser.class)
//			.withItemExposure((metdata, httpMethods) -> httpMethods.disable(HttpMethod.GET))
//			.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(HttpMethod.GET));
	}
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vlr) {
		Validator validator = validator();
		vlr.addValidator("beforeCreate", validator);
		vlr.addValidator("beforeSave", validator);
		
		logger.info("...'3' nlw Configure Validator running ...");
	}
}
