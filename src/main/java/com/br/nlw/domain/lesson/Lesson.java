package com.br.nlw.domain.lesson;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.nlw.domain.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(LessonListener.class)
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo vazio")
	private String whatsApp;
	
	@NotEmpty(message = "Campo vazio")
	private String bio;
	
	@NotEmpty
	private String theme;
	
	@NotNull
	private BigDecimal value;
	
	@NotNull
	private Integer weekDay;
	
	@NotNull
	private LocalTime startHour;
	
	@NotNull
	private LocalTime finishHour;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;
	
	public Lesson() {
		// TODO Auto-generated constructor stub
	}
	
	public Lesson(String whatsApp, String bio, String theme, BigDecimal value, Integer weekDay, LocalTime startHour, LocalTime finishHour) {
		this.whatsApp = whatsApp;
		this.bio = bio;
		this.theme = theme;
		this.value = value;
		this.weekDay = weekDay;
		this.startHour = startHour;
		this.finishHour = finishHour;
	}
	
	public Integer getId() {
		return id;
	}

	public String getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	public Integer getWeekDay() {
		return weekDay;
	}
	
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}
	
	public LocalTime getStartHour() {
		return startHour;
	}

	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	public LocalTime getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(LocalTime finishHour) {
		this.finishHour = finishHour;
	}

}
