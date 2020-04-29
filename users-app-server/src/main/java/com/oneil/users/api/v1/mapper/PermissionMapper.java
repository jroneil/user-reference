package com.oneil.users.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.oneil.users.api.v1.dto.PermissionDTO;
import com.oneil.users.entity.Permission;

@Mapper(componentModel="spring")
public interface PermissionMapper {
	
	PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);


	   PermissionDTO permissionToPermissionDTO(Permission permission);
	   Permission permissionDTOToPermission(PermissionDTO permissionDto);
	}