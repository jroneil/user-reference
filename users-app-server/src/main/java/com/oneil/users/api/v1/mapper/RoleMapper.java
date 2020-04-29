package com.oneil.users.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.oneil.users.api.v1.dto.RoleDTO;
import com.oneil.users.entity.Role;



@Mapper(componentModel="spring")
public interface RoleMapper {
	
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);


	   RoleDTO roleToRoleDTO(Role role);
	   Role roleDTOToRole(RoleDTO roleDto);
	}