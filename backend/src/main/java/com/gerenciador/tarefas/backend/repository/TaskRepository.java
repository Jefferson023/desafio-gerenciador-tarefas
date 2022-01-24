package com.gerenciador.tarefas.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gerenciador.tarefas.backend.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	final String QUERY_STRING = "SELECT t FROM Task t WHERE "
			+ "(:numero IS NULL OR t.numero = :numero)"
			+ " AND (:tituloDescricao IS NULL OR (UPPER(t.titulo) LIKE UPPER(:tituloDescricao)"
			+ " OR UPPER(t.descricao) LIKE UPPER(:tituloDescricao)))"
			+ " AND (:responsavel IS NULL OR t.responsavel.username = :responsavel)"
			+ " AND (:concluida IS NULL OR t.concluida = concluida)";
	@Query(value = QUERY_STRING)
	Page<Task> paginateSearch(Long numero, String tituloDescricao, String responsavel, 
			boolean concluida, Pageable pageable);
}