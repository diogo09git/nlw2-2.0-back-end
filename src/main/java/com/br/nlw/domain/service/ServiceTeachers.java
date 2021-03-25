package com.br.nlw.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.lesson.LessonRepository;
import com.br.nlw.domain.user.UserException;

@Service
public class ServiceTeachers {
	
	private final LessonRepository lessonRepository;
	
	public ServiceTeachers(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	public List<Lesson> findByTheme(String theme) throws UserException {
		
		List<Lesson> lessonDB = lessonRepository.findByTheme(theme);
		
		if(lessonDB.isEmpty()) {
			throw new UserException("Nenhuma aula encontrada !");
		}
		
		return lessonDB;
	}
	
	
}
