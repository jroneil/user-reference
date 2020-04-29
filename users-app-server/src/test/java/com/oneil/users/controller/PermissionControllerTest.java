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
import com.oneil.users.api.v1.dto.PermissionDTO;
import com.oneil.users.service.PermissionService;

@WebMvcTest(PermissionController.class)
class PermissionControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	PermissionService permissionService;
	 @Autowired
	 ObjectMapper objectMapper;
	PermissionDTO newPerm1=new  PermissionDTO(1l,"read");
	String permJson="";
	@BeforeEach
	void setUp() throws JsonProcessingException {
		permJson = objectMapper.writeValueAsString(newPerm1);	
		
	}

	@Test
	void allUsers() throws Exception {
		PermissionDTO[] permList = Stream.of(
				new  PermissionDTO(1l,"read"),
			  	new  PermissionDTO(2l,"write"),
				new  PermissionDTO(3l,"execute"),
				new  PermissionDTO(4l,"print"))
				.toArray(	PermissionDTO[]::new);
			String arrayToJson = objectMapper.writeValueAsString(permList);
			 given(permissionService.findById(any())).willReturn(newPerm1);
			 	mockMvc.perform(get("/api/v1/permissions/" ).accept(MediaType.APPLICATION_JSON)
		        .content(arrayToJson))
	         .andExpect(status().isOk());
		

	}
	
	@Test
	void count() throws Exception {
		 given(permissionService.count()).willReturn(1l);
		 	mockMvc.perform(get("/api/v1/permissions/count").accept(MediaType.APPLICATION_JSON)
	        .content("1"))
      .andExpect(status().isOk());
	}
	
	@Test
	void save() throws Exception  {
		given(permissionService.save(any())).willReturn(newPerm1);

        mockMvc.perform(post("/api/v1/permissions/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(permJson))
                .andExpect(status().isCreated());
	
	}
	
	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/v1/permissions/{id}", "11")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isForbidden());
	}
	
	@Test
	void findbyId() throws Exception {
		 given(permissionService.findById(any())).willReturn(newPerm1);
		 	mockMvc.perform(get("/api/v1/permissions/{id}" , "1").accept(MediaType.APPLICATION_JSON)
	        .content(permJson))
      .andExpect(status().isOk());
	}
	
	@Test
	void DeleteObj() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/v1/permissions/delete")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
				.content(permJson))
	            .andExpect(status().isForbidden());
	}

}
