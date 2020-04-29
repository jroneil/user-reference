package com.oneil.users.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oneil.users.api.v1.dto.UserDTO;
import com.oneil.users.api.v1.mapper.UserMapper;
import com.oneil.users.entity.User;
import com.oneil.users.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	@Mock
	UserRepository repository;

	@InjectMocks
	UserServiceImpl service;

	User returnUser;

	@BeforeEach
	void setUp() {
		returnUser = new User("j@gmail.com", "j@gmal.com", "xxxx", true, false, false, false, null);
		;
	}

	@DisplayName("Test Find All")
	@Test
	void findAll() {
		User user = new User();

		List<User> users = new ArrayList<>();
		users.add(user);

		when(repository.findAll()).thenReturn(users);

		List<UserDTO> foundUsers = repository.findAll().stream().map(mapper::userToUserDTO)
				.collect(Collectors.toList());

		verify(repository).findAll();

		assertThat(foundUsers).hasSize(1);

	}

	@DisplayName("Test Count")
	@Test
	void count() {
		when(repository.count()).thenReturn(1L);
		Long cnt = service.count();
		assertEquals(1, cnt);
		verify(repository).count();
	}

	@Test
	void findById() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(returnUser));

		UserDTO foundUser = service.findById(1L);

		verify(repository).findById(anyLong());

		assertThat(foundUser).isNotNull();
		assertEquals("j@gmal.com", foundUser.getUsername());
	}

	@Test
	void save() {
		when(repository.save(any(User.class))).thenReturn(returnUser);

		UserDTO savedUser = service.save(new UserDTO());

		verify(repository).save(any(User.class));

		assertThat(savedUser).isNotNull();
	}

	@Test
	void testDeleteByObject() {
		UserDTO user = new UserDTO();

		service.delete(user);

		verify(repository).delete(any(User.class));
	}

	@Test
	void deleteById() {

		service.deleteById(1L);

		verify(repository).deleteById(anyLong());
	}

	@Test
	void deleteById2x() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(repository, times(2)).deleteById(1l);
	}

	@Test
	void deleteByIdAtLeast() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(repository, atLeastOnce()).deleteById(1l);
	}

	@Test
	void deleteByIdAtMost() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(repository, atMost(5)).deleteById(1l);
	}

	@Test
	void deleteByIdNever() {
		service.deleteById(1l);
		service.deleteById(1l);

		verify(repository, atLeastOnce()).deleteById(1l);

		verify(repository, never()).deleteById(5L);
	}

	@Test
	void testDelete() {
		service.delete(new UserDTO());
	}

	
}
