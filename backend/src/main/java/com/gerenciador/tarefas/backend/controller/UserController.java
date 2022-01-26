package com.gerenciador.tarefas.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.backend.dto.UserRequestDto;
import com.gerenciador.tarefas.backend.dto.UserResponseDto;
import com.gerenciador.tarefas.backend.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
	
	private final UserService userService;
	
	@PreAuthorize("!isAuthenticated()")
	@ApiOperation(value = "Insere um novo usuário no banco de dados")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o usuário inserido"),
			@ApiResponse(code = 409, message = "O usuario já existe")})
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		userService.insertUser(userRequestDto);
	}
	
	@ApiOperation(value = "Retorna uma lista com todos os usuários cadastrados")
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<UserResponseDto> getAllUsers(){
		return userService.getAll();
	}
	
}
