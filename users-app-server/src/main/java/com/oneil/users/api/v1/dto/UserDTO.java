package com.oneil.users.api.v1.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{

	
	protected Long id;
	private String email;
	private String username;
	private String password;
	private boolean enabled;

	private boolean accountNonLocked;


	private boolean accountNonExpired;

	
	private boolean credentialsNonExpired;

	private Set<RoleDTO> roles=new HashSet<RoleDTO>();

	public boolean isEnabled() {
		return enabled;
	}

	
	public boolean isAccountNonExpired() {
		return !accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return !credentialsNonExpired;
	}


	public boolean isAccountNonLocked() {
		return !accountNonLocked;
	}

	
}
