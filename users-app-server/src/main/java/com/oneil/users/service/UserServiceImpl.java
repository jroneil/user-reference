package com.oneil.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneil.users.api.v1.dto.UserDTO;
import com.oneil.users.api.v1.mapper.UserMapper;
import com.oneil.users.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
		@Autowired
	    private  UserRepository userRepository;
		@Autowired
	    private  UserMapper mapper;
	   
	    public UserServiceImpl() {
		super();
		this.mapper = Mappers.getMapper(UserMapper.class);
	}

	    @Override
		public Long count() {

	        return userRepository.count();
	    }

	  
		@Override
	    public List<UserDTO> findAll() {
			return userRepository.findAll()
	                .stream()
	                .map(mapper::userToUserDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public UserDTO findById(Long aLong) {
	    return	mapper.userToUserDTO(userRepository.findById(aLong).orElse(null));
	    }

	    @Override
	    public UserDTO save(UserDTO object) {
	        return mapper.userToUserDTO(userRepository.save(mapper.userDTOToUser(object)));
	    }

	    @Override
	    public void delete(UserDTO object) {
	        userRepository.delete(mapper.userDTOToUser(object));
	    }

	    @Override
	    public void deleteById(Long aLong) {
	        userRepository.deleteById(aLong);
	    }
}
