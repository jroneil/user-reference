package com.oneil.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneil.users.api.v1.dto.RoleDTO;
import com.oneil.users.api.v1.mapper.RoleMapper;
import com.oneil.users.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository repository;
	@Autowired
	private RoleMapper mapper;

	@Autowired
	public RoleServiceImpl() {
		super();
			this.mapper = Mappers.getMapper(RoleMapper.class);
	}

	@Override
	public Long count() {

		return repository.count();
	}

	@Override
	public List<RoleDTO> findAll() {
		return repository.findAll().stream().map(mapper::roleToRoleDTO).collect(Collectors.toList());
	}

	@Override
	public RoleDTO findById(Long aLong) {
		return mapper.roleToRoleDTO(repository.findById(aLong).orElse(null));
	}

	@Override
	public RoleDTO save(RoleDTO object) {
		return mapper.roleToRoleDTO(repository.save(mapper.roleDTOToRole(object)));
	}

	@Override
	public void delete(RoleDTO object) {
		repository.delete(mapper.roleDTOToRole(object));
	}

	@Override
	public void deleteById(Long aLong) {
		repository.deleteById(aLong);
	}
}
