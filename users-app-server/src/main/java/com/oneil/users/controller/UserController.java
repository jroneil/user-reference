package com.oneil.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneil.users.api.v1.dto.UserDTO;
import com.oneil.users.exception.UserNotFoundException;
import com.oneil.users.service.UserService;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> allUsers() {
		return Optional.ofNullable(userService.findAll()).map(users -> new ResponseEntity<>(users, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<UserDTO> createOrUpdateUser(UserDTO user) throws UserNotFoundException {
		 return Optional.ofNullable(userService.save(user))
				 .map(updated->new ResponseEntity<UserDTO>( updated,new HttpHeaders(), HttpStatus.CREATED))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		return Optional.ofNullable(userService.count())
				.map(cnt -> new ResponseEntity<Long>(cnt, new HttpHeaders(), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) throws UserNotFoundException {

		Long userId = Long.parseLong(id);
		userService.deleteById(userId);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findByid(@PathVariable String id) throws UserNotFoundException {
		Long userId = Long.parseLong(id);
		return new ResponseEntity<>(userService.findById(userId), new HttpHeaders(), HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public  ResponseEntity<Object> deleteUser(UserDTO user) throws UserNotFoundException {
		userService.delete(user);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
