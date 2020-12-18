package com.br.nlw.infrastructure.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.AppUserRepository;

@Service
public class UserDetalisServiceImpl implements UserDetailsService {

	private final AppUserRepository appUserRepository;
	
	public UserDetalisServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		AppUser appUser = appUserRepository.findByEmail(email);
		
		if(appUser == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserDetailsImpl(appUser);
	}

}
