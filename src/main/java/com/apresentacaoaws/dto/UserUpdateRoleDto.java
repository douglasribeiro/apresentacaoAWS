package com.apresentacaoaws.dto;

import javax.validation.constraints.NotNull;

import com.apresentacaoaws.domain.enuns.Role;

public class UserUpdateRoleDto {

	@NotNull(message = "Role invalida....")
	private Role role;
	
	public UserUpdateRoleDto() {}

	public UserUpdateRoleDto(Role role) {
		super();
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
