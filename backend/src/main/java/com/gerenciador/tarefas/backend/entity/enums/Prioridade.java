package com.gerenciador.tarefas.backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Prioridade {
	ALTA("Alta"),
	MEDIA("Média"),
	BAIXA("Baixa");
	
	@Getter
	private String descricao;
}
