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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oneil.users.api.v1.dto.PermissionDTO;
import com.oneil.users.api.v1.mapper.PermissionMapper;
import com.oneil.users.api.v1.mapper.UserMapper;
import com.oneil.users.entity.Permission;
import com.oneil.users.repository.PermissionRepository;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {
	private PermissionMapper mapper = Mappers.getMapper(PermissionMapper.class);
	
	
	@Mock
	PermissionRepository repository;

	@InjectMocks
	PermissionServiceImpl service;

	
	Permission returnPemission;
	
	 @BeforeEach
	    void setUp() {
		 returnPemission =new Permission("read");
	    }

	@DisplayName("Test Find All")
	@Test
	void findAll() {
		Permission permission = new Permission();

		List<Permission> permissions = new ArrayList<>();
		permissions.add(permission);

		when(repository.findAll()).thenReturn(permissions);

		List<PermissionDTO> foundPermissions = repository.findAll()
                .stream()
                .map(mapper::permissionToPermissionDTO)
                .collect(Collectors.toList());

		verify(repository).findAll();

		assertThat(foundPermissions).hasSize(1);

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
		when(repository.findById(anyLong())).thenReturn(Optional.of(returnPemission));
		PermissionDTO foundPermission = service.findById(1L);

		verify(repository).findById(anyLong());

		assertThat(foundPermission).isNotNull();
		assertEquals("read",foundPermission.getName());
	}

	@Test
	void save() {
		when(repository.save(any(Permission.class))).thenReturn(returnPemission);
		PermissionDTO savedPermissionDTO = service.save(new PermissionDTO());

		verify(repository).save(any(Permission.class));

		assertThat(savedPermissionDTO).isNotNull();
	}
		
	@Test
	void delete() {
		PermissionDTO permission = new PermissionDTO();

		service.delete(permission);

		verify(repository).delete(any(Permission.class));

	}

	
	
	@Test
    void testDeleteByObject() {
        PermissionDTO permission = new PermissionDTO();

        service.delete(permission);

        verify(repository).delete(any(Permission.class));
    }

    @Test
    void findByIdTest() {
        Permission permission = new Permission();

        when(repository.findById(1L)).thenReturn(Optional.of(permission));

        PermissionDTO foundPermission = service.findById(1L);

        assertThat(foundPermission).isNotNull();

        verify(repository).findById(anyLong());

    }
    
    @Test
    public void permissionDTOToPermission() {
    	PermissionDTO dto = new PermissionDTO();
        dto.setId(1l);
        dto.setName("read");
     
        Permission entity = mapper.permissionDTOToPermission(dto);
     
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
    }
    @Test
    public void permissionToPermissionDTO() {
    	Permission entity = new Permission();
    	entity.setId(1l);
    	entity.setName("read");
     
        PermissionDTO dto = mapper.permissionToPermissionDTO(entity);
     
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
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
        service.delete(new PermissionDTO());
    }
}
   

