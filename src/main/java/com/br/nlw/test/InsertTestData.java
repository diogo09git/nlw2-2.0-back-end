package com.br.nlw.test;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.lesson.LessonRepository;
import com.br.nlw.domain.user.AppUser;
import com.br.nlw.domain.user.AppUserRepository;

@Component
public class InsertTestData {

	private final LessonRepository lessonRepository;
	
	private final AppUserRepository appUserRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public InsertTestData(LessonRepository lessonRepository, AppUserRepository appUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.lessonRepository = lessonRepository;
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		AppUser user = new AppUser("diogo", "silva", "diogo@", bCryptPasswordEncoder.encode("cba"));
		appUserRepository.save(user);
		AppUser user2 = new AppUser("diogo2", "djfksdjf", "@diogo", bCryptPasswordEncoder.encode("abc"));
		appUserRepository.save(user2);
		
		LocalTime horas = LocalTime.now();
		
		BigDecimal b1 = new BigDecimal(10.00);
		
		Lesson lesson = new Lesson("991723937", "JAVA", "programacao", b1, "2", horas, horas);
		lesson.setAppUser(user);
		lessonRepository.save(lesson);
		
		Lesson lesson1 = new Lesson("45488", "PHP", "Biologia", b1, "0", horas, horas);
		lesson1.setAppUser(user2);
		lessonRepository.save(lesson1);
		
		Lesson lesson2 = new Lesson("38838", "PYTHON", "math", b1, "0", horas, horas);
		lesson2.setAppUser(user2);
		lessonRepository.save(lesson2);
		
	}
}







