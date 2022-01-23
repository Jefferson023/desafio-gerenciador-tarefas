package com.gerenciador.tarefas.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
	
    @ApiModelProperty(example = "usuario",value = "O atributo de login. Username do usuário")
	@NotNull(message="O login não pode ser null")
	@NotBlank(message= "O login não pode ser vazio")
	private String login;
	
    @ApiModelProperty(example = "123",value = "O atributo de senha. A senha do usuário")
	@NotNull(message="O password não pode ser null")
	@NotBlank(message= "O password não pode ser vazio")
	private String password;
}
