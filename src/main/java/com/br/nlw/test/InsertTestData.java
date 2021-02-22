package com.br.nlw.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.lesson.LessonRepository;
import com.br.nlw.domain.schedule.Schedule;
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
		
		BigDecimal b1 = new BigDecimal(10.00);
		
		String bioTest = "Entusiasta das melhores tecnologias de química avançada. "
				+ "Apaixonado por explodir coisas em laboratório e por mudar a vida das pessoas através de experiências. "
				+ "Mais de 200.000 pessoas já passaram por uma das minhas explosões.";
		
		
		Schedule sch = new Schedule("Segunda", "11:00", "12:00");
		Schedule sch1 = new Schedule("Sexta", "13:00", "15:00");
		List<Schedule> sche = new ArrayList<Schedule>();
		sche.add(sch);
		sche.add(sch1);
		
		Lesson lesson = new Lesson("991723937", bioTest, "Biologia", b1);
		lesson.setAppUser(user);
		lesson.setSchedule(sche);
		lessonRepository.save(lesson);
		
		Lesson lesson1 = new Lesson("45488", bioTest, "Biologia", b1);
		lesson1.setAppUser(user2);
//		lesson1.setSchedule(sche);
		lessonRepository.save(lesson1);
		
		
	}
}







