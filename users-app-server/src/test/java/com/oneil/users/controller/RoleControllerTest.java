package com.oneil.users.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneil.users.api.v1.dto.RoleDTO;
import com.oneil.users.service.PermissionService;
import com.oneil.users.service.RoleService;
import com.oneil.users.service.UserService;

@WebMvcTest(RoleController.class)
class RoleControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserService userService;
	@MockBean
	RoleService roleService;
	@MockBean
	PermissionService permissionService;
	 @Autowired
	 ObjectMapper objectMapper;
	 RoleDTO role1 = new RoleDTO(1l,"Admin",null);
	String role1Json="";
	@BeforeEach
	void setUp() throws JsonProcessingException {
		role1Json = objectMapper.writeValueAsString(role1);	
	
		
	}

	@Test
	void allUsers() throws Exception {
		RoleDTO[] rolesList = Stream.of(
				new  RoleDTO(1l,"Admin",null),
			  	new  RoleDTO(2l,"USER",null),
				new  RoleDTO(3l,"Super",null),
				new  RoleDTO(4l,"EXT",null))
				.toArray(	RoleDTO[]::new);
			String arrayToJson = objectMapper.writeValueAsString(rolesList);
			 given(roleService.findById(any())).willReturn(role1);
			 	mockMvc.perform(get("/api/v1/roles/" ).accept(MediaType.APPLICATION_JSON)
		        .content(arrayToJson))
	         .andExpect(status().isOk());
		

	}
	
	@Test
	void count() throws Exception {
		 given(roleService.count()).willReturn(1l);
		 	mockMvc.perform(get("/api/v1/roles/count").accept(MediaType.APPLICATION_JSON)
	        .content("1"))
         .andExpect(status().isOk());
	}
	
	@Test
	void save() throws Exception {
		given(roleService.save(any())).willReturn(role1);

        mockMvc.perform(post("/api/v1/roles/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(role1Json))
                .andExpect(status().isCreated());
	}
	
	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/v1/roles/{id}", "11")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isForbidden());
	}
	@Test
	void findbyId() throws Exception {
		 given(roleService.findById(any())).willReturn(role1);
		 	mockMvc.perform(get("/api/v1/roles/{id}" , "1").accept(MediaType.APPLICATION_JSON)
	        .content(role1Json))
         .andExpect(status().isOk());
	}
	
	@Test
	void DeleteObj() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/v1/roles/delete")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
				.content(role1Json))
	            .andExpect(status().isForbidden());
	}
}
