package com.oneil.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneil.users.api.v1.dto.PermissionDTO;
import com.oneil.users.api.v1.mapper.PermissionMapper;
import com.oneil.users.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionRepository repository;
	@Autowired
	private PermissionMapper mapper;

	public PermissionServiceImpl() {
		super();
		this.mapper = Mappers.getMapper(PermissionMapper.class);
	}

	@Override
	public Long count() {

		return repository.count();
	}

	@Override
	public List<PermissionDTO> findAll() {
		return repository.findAll().stream().map(mapper::permissionToPermissionDTO).collect(Collectors.toList());
	}

	@Override
	public PermissionDTO findById(Long aLong) {
		return mapper.permissionToPermissionDTO(repository.findById(aLong).orElse(null));
	}

	@Override
	public PermissionDTO save(PermissionDTO object) {
		return mapper.permissionToPermissionDTO(repository.save(mapper.permissionDTOToPermission(object)));
	}

	@Override
	public void delete(PermissionDTO object) {
		repository.delete(mapper.permissionDTOToPermission(object));
	}

	@Override
	public void deleteById(Long aLong) {
		repository.deleteById(aLong);
	}
}
