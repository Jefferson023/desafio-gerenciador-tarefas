package com.gerenciador.tarefas.backend.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Situacao {
    @JsonProperty("Em andamento")
	EM_ANDAMENTO("Em andamento"),
	@JsonProperty("Concluida")
	CONCLUIDA("Concluida");
	
	@Getter
	private String descricao;
}