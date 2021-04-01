package com.br.nlw.domain.schedule;

import javax.persistence.Embeddable;

@Embeddable
public class Schedule {
	
	private String weekDay;
	
	private String startHour;
	
	private String finishHour;
	
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
	
}
