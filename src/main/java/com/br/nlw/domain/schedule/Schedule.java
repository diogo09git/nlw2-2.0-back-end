package com.br.nlw.domain.schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.br.nlw.domain.lesson.Lesson;

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String weekDay;
	
	private String startHour;
	
	private String finishHour;
	
	@ManyToOne()
	private Lesson lesson;
	
	public Schedule() {
	}

	public Schedule(String weekDay, String startHour, String finishHour) {
		this.weekDay = weekDay;
		this.startHour = startHour;
		this.finishHour = finishHour;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(String finishHour) {
		this.finishHour = finishHour;
	}
	
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
//	public Lesson getLesson() {
//		return lesson;
//	}
}
