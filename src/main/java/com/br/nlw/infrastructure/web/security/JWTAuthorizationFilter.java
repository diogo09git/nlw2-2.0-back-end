package com.br.nlw.infrastructure.web.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
		
		if(token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX) && validateToken(token)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		String user = Jwts.parser()
			.setSigningKey(SecurityConstants.THE_SECRET_KEY)
			.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "").trim())
			.getBody().getSubject();
		
		if(user != null) {
			return new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.NO_AUTHORITIES);
		}
		
		return null;
	}
	
	private boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(SecurityConstants.THE_SECRET_KEY)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "").trim());
			
			if(claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
			
		} catch (JwtException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Expired or invalid JWT token");
		}
	}

}


