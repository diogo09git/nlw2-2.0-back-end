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
	
//	private final ScheduleRepository scheduleRepository;
	
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
		
		List<Schedule> listSche = new ArrayList<Schedule>();
		List<Schedule> listSche1 = new ArrayList<Schedule>();
		
		
		Schedule sch = new Schedule("Segunda", "11:00", "12:00");
		Schedule sch1 = new Schedule("Sexta", "13:00", "15:00");
		Schedule sch2 = new Schedule("Quarta", "17:00", "18:00");
		
//		listSche.add(sch);
		listSche.add(sch1);
		listSche.add(sch2);
		listSche1.add(sch);
		
		Lesson lesson = new Lesson("034991723937", bioTest, "Biologia", b1, listSche);
		Lesson lesson0 = new Lesson("8888", bioTest, "Artes", b1, listSche1);
		Lesson lesson1 = new Lesson("45488", bioTest, "Biologia", b1.add(b1), listSche1);	
		
		lesson.setAppUser(user);
		lessonRepository.save(lesson);
		
		lesson1.setAppUser(user2);
		lessonRepository.save(lesson1);
		
		lesson0.setAppUser(user2);
		lessonRepository.save(lesson0);
		
//		sch.setLesson(lesson0);
//		scheduleRepository.save(sch);
//		sch1.setLesson(lesson0);
//		scheduleRepository.save(sch1);
//		sch2.setLesson(lesson);
//		scheduleRepository.save(sch2);
	
		
	}
}







