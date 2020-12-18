package com.br.nlw.domain.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.nlw.domain.service.ServiceUser;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final ServiceUser serviceUser;
	
	public UserController(ServiceUser serviceUser) {	
		this.serviceUser = serviceUser;
}

	@PostMapping("/register")
	public void register(@RequestBody AppUser appUser) throws DuplicateUserException {
		
		serviceUser.validateEmail(appUser);
	}
}
