package com.gerenciador.tarefas.backend.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.backend.dto.AuthRequestDto;
import com.gerenciador.tarefas.backend.dto.AuthResponseDto;
import com.gerenciador.tarefas.backend.service.TokenService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

	private final TokenService tokenService;
	
	private final AuthenticationManager authenticationManager;

	@ApiOperation(value = "Cria um token JWT de acesso")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um token de acesso"),
			@ApiResponse(code = 404, message = "O login ou senha estão incorretos"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")})
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping
	public AuthResponseDto login(@Valid @RequestBody AuthRequestDto authReqDto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(authReqDto.getLogin(), authReqDto.getPassword());
		System.out.println(usernamePasswordAuthenticationToken);
		System.out.println("entrou");
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		System.out.println(authentication);
	
		return tokenService.createResponseAuthToken(authentication);
	}
}
