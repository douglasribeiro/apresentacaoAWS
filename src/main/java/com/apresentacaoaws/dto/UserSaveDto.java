package com.apresentacaoaws.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.RequestStage;
import com.apresentacaoaws.domain.User;
import com.apresentacaoaws.domain.enuns.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserSaveDto {
	
	@NotBlank(message = "Nome nao pode estar em branco")
	@NotNull(message = "Nome Obrigatorio...")
	private String name;
	
	@Email(message = "e-mail invalido")
	private String email;
	
	@Size(min = 7, max = 99, message = "password valido entre 7 e 99 caracteres.")
	private String password;
	
	@NotNull
	private Role role;
	
	@OneToMany(mappedBy = "owner")
	private List<Request> requests = new ArrayList<Request>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "owner")
	private List<RequestStage> stages = new ArrayList<RequestStage>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<RequestStage> getStages() {
		return stages;
	}

	public void setStages(List<RequestStage> stages) {
		this.stages = stages;
	}
	
	public User transformToUser() {
		User user = new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
		return user;
	}

}
