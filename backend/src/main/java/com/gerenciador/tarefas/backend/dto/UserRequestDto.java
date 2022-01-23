package com.gerenciador.tarefas.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @ApiModelProperty(example = "usuario",value = "Username do usuário")
	@NotNull(message="O username não pode ser null")
	@NotBlank(message= "O username não pode ser vazio")
	private String username;
	
    @ApiModelProperty(example = "123",value = "O atributo de senha. A senha do usuário")
	@NotNull(message="O password não pode ser null")
	@NotBlank(message= "O password não pode ser vazio")
	private String password;
    
    @ApiModelProperty(example = "joão",value = "O nome do usuário")
	@NotNull(message="O nome não pode ser null")
	@NotBlank(message= "O nome não pode ser vazio")
    private String name;
}
