package com.oneil.users.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.oneil.users.api.v1.dto.UserDTO;
import com.oneil.users.entity.User;

@Mapper(componentModel="spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


	   UserDTO userToUserDTO(User user);
	   User userDTOToUser(UserDTO userDto);
	}