package com.gerenciador.tarefas.backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

@AllArgsConstructor
public enum Prioridade {
    	@JsonProperty("Alta")
	ALTA("Alta"),
	@JsonProperty("Média")
	MEDIA("Média"),
	@JsonProperty("Baixa")
	BAIXA("Baixa");
	
	@Getter
	private String descricao;
}
