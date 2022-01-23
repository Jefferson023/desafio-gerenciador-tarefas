package com.gerenciador.tarefas.backend.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.gerenciador.tarefas.backend.entity.enums.Prioridade;

import lombok.Data;

@Entity
@Data
public class Task implements Serializable{

	private static final long serialVersionUID = -4273607857177618793L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long numero;
	
	private String titulo;
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	private LocalDate deadline;
	
	@ManyToOne
	private User responsavel;
	
	private boolean concluida;
}
