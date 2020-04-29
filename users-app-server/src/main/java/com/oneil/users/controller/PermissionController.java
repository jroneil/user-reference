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

import com.oneil.users.api.v1.dto.PermissionDTO;
import com.oneil.users.exception.PermissionNotFoundException;
import com.oneil.users.service.PermissionService;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {
	private final PermissionService permissionService;
	
	@Autowired
	public PermissionController(PermissionService permissionService) {
		super();
		this.permissionService = permissionService;
	}


	@GetMapping("/")
	public ResponseEntity<List<PermissionDTO>> allPermissions() {
		 return Optional.ofNullable(permissionService.findAll())
				 .map(permissions->new ResponseEntity<List<PermissionDTO>>( permissions,new HttpHeaders(), HttpStatus.OK))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<PermissionDTO> createOrUpdatePermission(PermissionDTO permission) throws PermissionNotFoundException {
			 return Optional.ofNullable(permissionService.save(permission))
				 .map(updated->new ResponseEntity<PermissionDTO>( updated,new HttpHeaders(), HttpStatus.CREATED))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count() {
		 return Optional.ofNullable(permissionService.count())
				 .map(cnt->new ResponseEntity<Long>(cnt, new HttpHeaders(), HttpStatus.OK))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id)throws PermissionNotFoundException  {

		Long roleId = Long.parseLong(id);
		permissionService.deleteById(roleId);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<PermissionDTO> findByid(@PathVariable String id)throws PermissionNotFoundException  {

		 return Optional.ofNullable(permissionService.findById(Long.parseLong(id)))
				 .map(permission->new ResponseEntity<PermissionDTO>(permission, new HttpHeaders(), HttpStatus.OK))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
						 
		
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deletePermission(PermissionDTO role) throws PermissionNotFoundException {
		permissionService.delete(role);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
