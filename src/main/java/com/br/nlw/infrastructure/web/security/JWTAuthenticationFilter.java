package com.br.nlw.infrastructure.web.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.br.nlw.domain.user.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			AppUser appUser = mapper.readValue(request.getInputStream(), AppUser.class);
			
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword());
			return authenticationManager.authenticate(upat);
			
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		
		String jwtToken = Jwts.builder()
			.setSubject(userDetails.getEmail())
			.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
			.claim("name", userDetails.getUsername())
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.THE_SECRET_KEY)
			.compact();
		
		response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.TOKEN_PREFIX + jwtToken);
	}
	
}




