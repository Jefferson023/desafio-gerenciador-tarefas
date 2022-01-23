package com.gerenciador.tarefas.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    @ApiModelProperty(example = "usuario",value = "O atributo de login. Username do usuário")
	@NotNull(message="O login não pode ser null")
	@NotBlank(message= "O login não pode ser vazio")
	private String username;
	
    @ApiModelProperty(example = "joão",value = "O nome do usuário")
	@NotNull(message="O nome não pode ser null")
	@NotBlank(message= "O nome não pode ser vazio")
    private String name;
}
