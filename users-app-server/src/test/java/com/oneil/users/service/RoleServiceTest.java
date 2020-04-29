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

import com.oneil.users.api.v1.dto.RoleDTO;
import com.oneil.users.api.v1.mapper.RoleMapper;
import com.oneil.users.entity.Role;
import com.oneil.users.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
	private RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
	@Mock
	RoleRepository repository;

	@InjectMocks
	RoleServiceImpl service;

	Role returnRole;
	
	 @BeforeEach
	    void setUp() {
		 returnRole =new Role("Admin",null);
	    }
	 
	@DisplayName("Test Find All")
	@Test
	void findAll() {
		Role role = new Role();

		List<Role> roles = new ArrayList<>();
		roles.add(role);

		when(repository.findAll()).thenReturn(roles);

		List<RoleDTO> foundRoles = repository.findAll()
                .stream()
                .map(mapper::roleToRoleDTO)
                .collect(Collectors.toList());
;

		verify(repository).findAll();

		assertThat(foundRoles).hasSize(1);

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
		

		when(repository.findById(anyLong())).thenReturn(Optional.of(returnRole));

		RoleDTO foundRole = service.findById(1L);

		verify(repository).findById(anyLong());

		assertThat(foundRole).isNotNull();
	}

	@Test
	void save() {
		Role role = new Role();

		when(repository.save(any(Role.class))).thenReturn(role);

		RoleDTO savedRole = service.save(new RoleDTO());

		verify(repository).save(any(Role.class));

		assertThat(savedRole).isNotNull();
	}
	
	@Test
    void testDeleteByObject() {
        RoleDTO role = new RoleDTO();

        service.delete(role);

        verify(repository).delete(any(Role.class));
    }

    @Test
    void findByIdTest() {
    

        when(repository.findById(1L)).thenReturn(Optional.of(returnRole));

        RoleDTO foundRole = service.findById(1L);

        assertThat(foundRole).isNotNull();

        verify(repository).findById(anyLong());
        assertEquals("Admin",foundRole.getName());
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
        service.delete(new RoleDTO());
        verify(repository, atLeastOnce()).delete(new Role());
    }
    
   
}
   

