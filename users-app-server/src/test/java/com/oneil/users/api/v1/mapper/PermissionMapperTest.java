package com.oneil.users.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oneil.users.api.v1.dto.PermissionDTO;
import com.oneil.users.entity.Permission;
@ExtendWith(MockitoExtension.class)
class PermissionMapperTest {
	private PermissionMapper mapper = Mappers.getMapper(PermissionMapper.class);
	
	
	 @Test
	    public void permissionDTOToPermission() {
	    	PermissionDTO dto = new PermissionDTO();
	        dto.setId(1l);
	        dto.setName("Admin");
	     
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
}
