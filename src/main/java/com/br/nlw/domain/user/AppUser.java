package com.br.nlw.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "app_user")
public class AppUser {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	public AppUser() {
		// TODO Auto-generated constructor stub
	}

	public AppUser(String name, String lastName, String email, String password) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
