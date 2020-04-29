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
import com.oneil.users.api.v1.dto.UserDTO;
import com.oneil.users.service.PermissionService;
import com.oneil.users.service.RoleService;
import com.oneil.users.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserService userService;
	@MockBean
	RoleService roleService;
	@MockBean
	PermissionService permissionService;

	UserDTO newUser1= new UserDTO(1l, "j1@gmail.com", "j1@gmal.com", "xxxx", true, false, false, false, null);
	String user1Json="";
	
	 @Autowired
	    ObjectMapper objectMapper;
	 @BeforeEach
	 void setUp() throws JsonProcessingException {
		 user1Json = objectMapper.writeValueAsString(newUser1); 
	 }
	@Test
	void allUsers() throws Exception {
		UserDTO[] userList = Stream.of(
			new UserDTO(1l, "j1@gmail.com", "j1@gmal.com", "xxxx", true, false, false, false, null),
		  	new UserDTO(2l, "j2@gmail.com", "j2@gmal.com", "xxxx", true, false, false, false, null),
			new UserDTO(4l, "j3@gmail.com", "j3@gmal.com", "xxxx", true, false, false, false, null),
			new UserDTO(4l, "j4@gmail.com", "j4@gmal.com", "xxxx", true, false, false, false, null))
			.toArray(UserDTO[]::new);
		String arrayToJson = objectMapper.writeValueAsString(userList);
		 given(userService.findById(any())).willReturn(newUser1);
		 	mockMvc.perform(get("/api/v1/users/" ).accept(MediaType.APPLICATION_JSON)
	        .content(arrayToJson))
         .andExpect(status().isOk());

	}

	@Test
	void count() throws Exception {
		 given(userService.count()).willReturn(1l);
		 	mockMvc.perform(get("/api/v1/users/count").accept(MediaType.APPLICATION_JSON)
	        .content("1"))
            .andExpect(status().isOk());
	}

	@Test
	void save() throws Exception {
		 given(userService.save(any())).willReturn(newUser1);

	        mockMvc.perform(post("/api/v1/users/save")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(user1Json))
	                .andExpect(status().isCreated());
	}

	@Test
	void delete() throws Exception {
				mockMvc.perform(MockMvcRequestBuilders
			            .delete("/api/v1/users/{id}", "11")
			            .contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.APPLICATION_JSON))
			            .andExpect(status().isForbidden());
		
	}
	

	@Test
	void findbyId() throws Exception {
		 given(userService.findById(any())).willReturn(newUser1);
		 	mockMvc.perform(get("/api/v1/users/{id}" , "1").accept(MediaType.APPLICATION_JSON)
	        .content(user1Json))
            .andExpect(status().isOk());
	}

@Test
	void DeleteObj() throws Exception {
				mockMvc.perform(MockMvcRequestBuilders
			            .delete("/api/v1/users/delete")
			            .contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.APPLICATION_JSON)
						.content(user1Json))
			            .andExpect(status().isForbidden());
	}
}
