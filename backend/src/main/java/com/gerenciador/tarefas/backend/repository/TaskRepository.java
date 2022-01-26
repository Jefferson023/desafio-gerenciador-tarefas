package com.gerenciador.tarefas.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gerenciador.tarefas.backend.entity.Task;
import com.gerenciador.tarefas.backend.entity.enums.Situacao;

public interface TaskRepository extends CrudRepository<Task, Long>{
	final String QUERY_STRING = "SELECT t FROM Task t WHERE "
			+ "(:numero IS NULL OR t.numero = :numero)"
			+ " AND UPPER(concat(t.titulo, ' ', t.descricao)) LIKE UPPER(:tituloDescricao)"
			+ " AND (:responsavel IS NULL OR t.responsavel.username = :responsavel)"
			+ " AND (:situacao IS NULL OR t.situacao = :situacao)";
	@Query(value = QUERY_STRING)
	Page<Task> paginateSearch(Long numero, String tituloDescricao, String responsavel, 
			String situacao, Pageable pageable);
}
