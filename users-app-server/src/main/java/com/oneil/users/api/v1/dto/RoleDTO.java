package com.oneil.users.api.v1.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	protected Long id;
	private String name;

	private Set<PermissionDTO> permissions=new HashSet<PermissionDTO>();

}
