package com.br.nlw.domain.lesson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.nlw.domain.schedule.Schedule;
import com.br.nlw.domain.user.AppUser;

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
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;
	
	@ElementCollection
	@CollectionTable(
			name = "SCHEDULE",
			joinColumns = @JoinColumn(name = "OWNER_ID"))
	private List<Schedule> schedule = new ArrayList<>(0);
	
	public Lesson() {
		// TODO Auto-generated constructor stub
	}
	
	public Lesson(String whatsApp, String bio, @NotEmpty String theme, @NotNull BigDecimal value, List<Schedule> schedule) {
		this.whatsApp = whatsApp;
		this.bio = bio;
		this.theme = theme;
		this.value = value;
		this.schedule = schedule;
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
	
	public List<Schedule> getSchedule() {
		return schedule;
	}
	
	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}
	
}
