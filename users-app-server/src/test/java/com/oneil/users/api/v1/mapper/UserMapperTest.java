package com.oneil.users.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oneil.users.api.v1.dto.UserDTO;
import com.oneil.users.entity.User;
@ExtendWith(MockitoExtension.class)
class UserMapperTest {
	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	@Test
	public void userDTOToUser() {
		UserDTO dto = new UserDTO();
		dto.setId(1l);
		dto.setUsername("joe");

		User entity = mapper.userDTOToUser(dto);

		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getUsername(), entity.getUsername());
	}

	@Test
	public void permissionToPermissionDTO() {
		User entity = new User();
		entity.setId(1l);
		entity.setUsername("joe");

		UserDTO dto = mapper.userToUserDTO(entity);

		assertEquals(entity.getId(), dto.getId());
		assertEquals(dto.getUsername(), entity.getUsername());
	}

}
