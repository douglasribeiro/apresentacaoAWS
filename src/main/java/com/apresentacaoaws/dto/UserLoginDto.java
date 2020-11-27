package com.apresentacaoaws.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginDto {

	@Email(message = "Endereço de e-mail invalido")
	private String email;
	
	@NotBlank(message = "password obrigatorio")
	private String password;
	
	public UserLoginDto() {}
	
	public UserLoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
