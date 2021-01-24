package com.br.nlw.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.nlw.domain.lesson.Lesson;
import com.br.nlw.domain.lesson.LessonRepository;

@Service
public class ServiceTeachers {

	private final LessonRepository lessonRepository;
	
	public ServiceTeachers(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	public Optional<Lesson> findByWeekDay(Integer id) {
		
		return lessonRepository.findByWeekDay(id);
	}
}
