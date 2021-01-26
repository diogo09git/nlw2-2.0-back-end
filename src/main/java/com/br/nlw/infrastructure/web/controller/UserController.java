package com.br.nlw.infrastructure.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.service.ServiceTeachers;
import com.br.nlw.domain.service.ServiceUser;
import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.UserException;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final ServiceUser serviceUser;
	private final ServiceTeachers serviceTeachers;
	
	public UserController(ServiceUser serviceUser, ServiceTeachers serviceTeachers) {
		this.serviceUser = serviceUser;
		this.serviceTeachers = serviceTeachers;
	}

	@PostMapping("/register")
	public void register(@RequestBody AppUser appUser) throws UserException {
		
		serviceUser.validateEmail(appUser);
	}
	
	@GetMapping("/teachers/{theme}/{day}")
	public ResponseEntity<List<Lesson>> searchTeachers(@PathVariable String theme,
			@PathVariable Integer day) throws UserException {
		
		Optional<List<Lesson>> weekDay = Optional.of(serviceTeachers.findByWeekDay(theme, day));
		
		return weekDay.map(resp -> ResponseEntity.ok().body(resp))
				.orElse(ResponseEntity.badRequest().build());
	}
}




