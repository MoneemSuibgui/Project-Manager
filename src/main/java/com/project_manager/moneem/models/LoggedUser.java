package com.project_manager.moneem.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoggedUser {

	// member variables

	@NotEmpty(message = "* Email is required")
	@Email(message = "Email is not right !!!")
	private String email;

	@NotEmpty(message = "* Password is required")
	@Size(min = 8, message = "* Password must at least 8 characters")
	private String password;

	// beans constructor
	public LoggedUser() {
	}

	// getters & setters
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
