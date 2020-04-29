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

import com.oneil.users.api.v1.dto.RoleDTO;
import com.oneil.users.exception.RoleNotFoundException;
import com.oneil.users.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	private final RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}


	@GetMapping("/")
	public ResponseEntity<List<RoleDTO>> allRoles() {
		return Optional.ofNullable(roleService.findAll())
			      .map(roles -> new ResponseEntity<>(roles, HttpStatus.OK))
			      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<RoleDTO> createOrUpdateRole(RoleDTO role) throws RoleNotFoundException {
		return Optional.ofNullable(roleService.save(role))
				 .map(updated->new ResponseEntity<RoleDTO>(updated, new HttpHeaders(), HttpStatus.CREATED))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		return Optional.ofNullable(roleService.count())
		 .map(cnt->new ResponseEntity<Long>(cnt, new HttpHeaders(), HttpStatus.OK))
		 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id)throws RoleNotFoundException  {

		Long roleId = Long.parseLong(id);
		roleService.deleteById(roleId);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> findByid(@PathVariable String id)throws RoleNotFoundException  {
	 return Optional.ofNullable(roleService.findById(Long.parseLong(id)))
				 .map(role->new ResponseEntity<RoleDTO>(role, new HttpHeaders(), HttpStatus.OK))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}
	@DeleteMapping("/delete")
	public  ResponseEntity<Object> deleteRole(RoleDTO role) throws RoleNotFoundException {
		roleService.delete(role);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
