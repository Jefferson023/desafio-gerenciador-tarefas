package com.gerenciador.tarefas.backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Situacao {
	EM_ANDAMENTO("Em andamento"),
	CONCLUIDA("Concluída");
	
	@Getter
	private String descricao;
}