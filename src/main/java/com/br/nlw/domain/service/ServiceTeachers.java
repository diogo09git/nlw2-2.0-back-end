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

	public List<Lesson> findByWeekDay(String theme, String day) throws UserException {
		
		List<Lesson> teacherBD = lessonRepository.findByThemeAndWeekDay(theme, day);
		
		if(teacherBD.isEmpty()) {
			throw new UserException("Nenhuma aula cadastrada com essa descrição");
		}
		
		return teacherBD;
	}
}
