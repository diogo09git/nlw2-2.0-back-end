package com.br.nlw.infrastructure.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.lesson.LessonRepository;
import com.br.nlw.domain.service.ServiceTeachers;
import com.br.nlw.domain.service.ServiceUser;
import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.UserException;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private final ServiceUser serviceUser;
	private final ServiceTeachers serviceTeachers;
	private final LessonRepository lessonRepository;

	public UserController(ServiceUser serviceUser, ServiceTeachers serviceTeachers, LessonRepository lessonRepository) {
		this.serviceUser = serviceUser;
		this.serviceTeachers = serviceTeachers;
		this.lessonRepository = lessonRepository;
	}
	
	@GetMapping("/teachers")
	public ResponseEntity<List<Lesson>> searchTeachers(@RequestParam String theme) throws UserException {
		
		Optional<List<Lesson>> themeDB = Optional.of(serviceTeachers.findByTheme(theme));
		return themeDB.map(resp -> ResponseEntity.ok().body(resp))
				.orElse(ResponseEntity.badRequest().build());
	}

	@PostMapping("/lessons")
	public ResponseEntity<Lesson> saveLesson(@RequestBody Lesson lesson) {
		
		lessonRepository.save(lesson);
		return new ResponseEntity<Lesson>(lesson, HttpStatus.CREATED);
	}
	
	@PostMapping("/users")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) throws UserException {
		
		serviceUser.validateEmail(appUser);
		return new ResponseEntity<AppUser>(appUser, HttpStatus.CREATED);
	}
	
}




