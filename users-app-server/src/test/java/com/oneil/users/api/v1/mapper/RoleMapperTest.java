package com.oneil.users.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oneil.users.api.v1.dto.RoleDTO;
import com.oneil.users.entity.Role;
@ExtendWith(MockitoExtension.class)
class RoleMapperTest {
	private RoleMapper mapper = Mappers.getMapper(RoleMapper.class);
	 @Test
	    public void roleDTOToRole() {
	    	RoleDTO dto = new RoleDTO();
	        dto.setId(1l);
	        dto.setName("Admin");
	     
	        Role entity = mapper.roleDTOToRole(dto);
	     
	        assertEquals(dto.getId(), entity.getId());
	        assertEquals(dto.getName(), entity.getName());
	    }
	    @Test
	    public void roleToRoleDTO() {
	    	Role entity = new Role();
	    	entity.setId(1l);
	    	entity.setName("read");
	     
	        RoleDTO dto = mapper.roleToRoleDTO(entity);
	     
	        assertEquals(entity.getId(), dto.getId());
	        assertEquals(entity.getName(), dto.getName());
	    }
}
