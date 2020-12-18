package com.br.nlw.infrastructure.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	private final UserDetalisServiceImpl userDetailsService;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurityConfig(UserDetalisServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors()
		.and()
			.headers().frameOptions().disable()
		.and()
			.httpBasic()
		.and()
			.authorizeRequests()
				.antMatchers("/api/lesson/**/appUser").denyAll()
				.antMatchers(HttpMethod.GET, "/api/user").denyAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers(HttpMethod.POST, "/user/register").permitAll()
				.anyRequest().authenticated()
		.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		logger.info("...'4' nlw Security setup running ...");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/login")
			.allowedOrigins("*")
			.allowedMethods("POST")
			.exposedHeaders(SecurityConstants.AUTHORIZATION_HEADER);
		
		logger.info("...'5' nlw CORS setup running ...");
	}
}




