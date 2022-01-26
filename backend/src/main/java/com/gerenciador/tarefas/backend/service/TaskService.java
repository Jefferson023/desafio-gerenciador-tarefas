package com.gerenciador.tarefas.backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gerenciador.tarefas.backend.dto.TaskDto;
import com.gerenciador.tarefas.backend.entity.Task;
import com.gerenciador.tarefas.backend.entity.enums.Situacao;
import com.gerenciador.tarefas.backend.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository taskRepository;
	
	private final ModelMapper modelMapper;
	
	public Task insertTask(TaskDto taskDto) {
		var task = modelMapper.map(taskDto, Task.class);
		task.setSituacao(Situacao.EM_ANDAMENTO);
		return taskRepository.save(task);
	}
	
	public Task updateTask(TaskDto taskDto) {
		taskRepository.findById(taskDto.getNumero())
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		
		return taskRepository.save(modelMapper.map(taskDto, Task.class));
	}
	
	public void removeTask(Long taskNumero) {
		try {
			taskRepository.deleteById(taskNumero);
		} catch (Exception removeException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	public Page<Task> searchTask(Long numero, String tituloDescricao, String responsavel, 
			Situacao situacao, int page, int size) {
		var pageable = PageRequest.of(page, size, Sort.by("numero").ascending());
		tituloDescricao = "%" + tituloDescricao + "%";
		return taskRepository.paginateSearch(numero, tituloDescricao, responsavel, situacao, pageable);
	}

}
