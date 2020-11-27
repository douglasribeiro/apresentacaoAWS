package com.apresentacaoaws.dto;

import com.apresentacaoaws.domain.enuns.Role;

public class UserUpdateRoleDto {

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
