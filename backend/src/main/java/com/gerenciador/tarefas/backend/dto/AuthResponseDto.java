package com.gerenciador.tarefas.backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDto {
    
	@ApiModelProperty(value = "O bearer token para autenticação")
	private String token;
}
