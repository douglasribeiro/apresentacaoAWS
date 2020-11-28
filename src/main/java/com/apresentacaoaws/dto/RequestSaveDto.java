package com.apresentacaoaws.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.User;
import com.apresentacaoaws.domain.enuns.RequestState;

public class RequestSaveDto {
 
	@NotBlank
	private String subject;
	
	private String description;
	
	private List<RequestState> stages = new ArrayList<RequestState>(); 
	
	@NotNull
	private User owner;
	
	public RequestSaveDto() {}

	public RequestSaveDto(@NotBlank String subject, String description, List<RequestState> stages,
			@NotNull User owner) {
		super();
		this.subject = subject;
		this.description = description;
		this.stages = stages;
		this.owner = owner;
	}



	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<RequestState> getStages() {
		return stages;
	}

	public void setStages(List<RequestState> stages) {
		this.stages = stages;
	}
	
	public Request transformToRequest() {
		Request request = new Request(null, this.subject, this.description, null, null, this.owner, null);
		return request;
	}
	
}
