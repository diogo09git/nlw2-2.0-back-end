package com.br.nlw.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.AppUserRepository;
import com.br.nlw.domain.user.DuplicateUserException;

@Service
public class ServiceUser {

	private final AppUserRepository appUserRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public ServiceUser(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void validateEmail(AppUser appUser) throws DuplicateUserException {
		
		AppUser userDB = appUserRepository.findByEmail(appUser.getEmail());
		
		if(userDB != null) {
			if(userDB.getEmail().equals(appUser.getEmail())) {
				throw new DuplicateUserException("Este e-mail já foi cadastrado, tente novamente !");
			}
		}
		
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		appUserRepository.save(appUser);
	}
}
