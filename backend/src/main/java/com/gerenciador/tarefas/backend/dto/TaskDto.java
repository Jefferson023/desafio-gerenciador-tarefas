package com.gerenciador.tarefas.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.gerenciador.tarefas.backend.entity.User;
import com.gerenciador.tarefas.backend.entity.enums.Prioridade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    @ApiModelProperty(example = "1",value = "O número/id da tarefa")
    private Long numero;
	
    @ApiModelProperty(example = "exemplo",value = "O titulo da tarefa")
	@NotNull(message="O titulo da tarefa não pode ser null")
	@NotBlank(message= "O titulo da tarefa não pode ser vazio")
	private String titulo;
	
    @ApiModelProperty(example = "exemplo",value = "A descrição da tarefa")
	private String descricao;
	
    @ApiModelProperty(example = "ALTA",value = "A prioridade da tarefa")
	@NotNull(message="A prioridade da tarefa não pode ser null")
	private Prioridade prioridade;
	
    @ApiModelProperty(example = "yyyy-mm-dd",value = "A data de deadline da tarefa")
	@NotNull(message="O deadline da tarefa não pode ser null")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate deadline;
	
    @ApiModelProperty(example = "user",value = "O usuário responsável pela tarefa")
	@NotNull(message="O responsável da tarefa não pode ser null")
	private User responsavel;
	
    @ApiModelProperty(example = "true",value = "Se a tarefa foi concluída ou não")
	private boolean concluida;
}
