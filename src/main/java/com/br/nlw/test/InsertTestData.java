package com.br.nlw.test;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.lesson.LessonRepository;
import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.AppUserRepository;

@Component
public class InsertTestData {

	private final LessonRepository lessonRepository;
	
	private final AppUserRepository appUserRepository;

	public InsertTestData(LessonRepository lessonRepository, AppUserRepository appUserRepository) {
		this.lessonRepository = lessonRepository;
		this.appUserRepository = appUserRepository;
	}
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		AppUser user = new AppUser("diogo", "silva", "diogo@", encoder.encode("bca"));
		appUserRepository.save(user);
		AppUser user2 = new AppUser("diogo2", "djfksdjf", "@diogo", encoder.encode("abc"));
		appUserRepository.save(user2);
		
		LocalTime horas = LocalTime.now();
		
		BigDecimal b1 = new BigDecimal(10.00);
		
		Lesson lesson = new Lesson("991723937", "usuario 1", "programacao", b1, horas, horas);
		lesson.setAppUser(user);
		lessonRepository.save(lesson);
		
		Lesson lesson2 = new Lesson("38838", "usuario 2", "math", b1, horas, horas);
		lesson2.setAppUser(user2);
		lessonRepository.save(lesson2);
		
	}
}






