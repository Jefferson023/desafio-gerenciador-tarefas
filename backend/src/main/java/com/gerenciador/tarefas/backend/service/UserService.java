package com.gerenciador.tarefas.backend.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gerenciador.tarefas.backend.dto.UserRequestDto;
import com.gerenciador.tarefas.backend.dto.UserResponseDto;
import com.gerenciador.tarefas.backend.entity.User;
import com.gerenciador.tarefas.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public void insertUser(UserRequestDto userRequestDto) {
		var user = modelMapper.map(userRequestDto, User.class);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		if (!userRepository.findByUsername(user.getUsername()).isPresent()) {
			userRepository.save(user);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
		}
	}
	
	public List<UserResponseDto> getAll(){
		return StreamSupport.stream(userRepository.findAll().spliterator(),
				false).map(u -> modelMapper.map(u, UserResponseDto.class))
				.collect(Collectors.toList());
	}
}
