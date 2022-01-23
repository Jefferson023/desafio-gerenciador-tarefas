package com.gerenciador.tarefas.backend.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.backend.dto.TaskDto;
import com.gerenciador.tarefas.backend.entity.Task;
import com.gerenciador.tarefas.backend.service.TaskService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
@PreAuthorize("isAuthenticated()")
public class TaskController {
	private final TaskService taskService;
	
	@ApiOperation(value = "Cria uma nova tarefa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a tarefa inserida no banco")})
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Task createTask(@Valid @RequestBody TaskDto taskDto) {
		return taskService.insertTask(taskDto);
	}
	
	@ApiOperation(value = "Atualiza uma tarefa previamente cadastrada")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a tarefa atualizada"),
			@ApiResponse(code = 400, message = "A tarefa não existe no banco de dados")})
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Task updateTask(@Valid @RequestBody TaskDto taskDto) {
		return taskService.updateTask(taskDto);
	}
	
	@ApiOperation(value = "Busca tarefas no banco de dados de acordo com parametros de pesquisa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de tarefas encontradas "
			+ "por página"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")})
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Page<Task> searchTask(@RequestParam(required = false) Long numero, 
			@RequestParam(required = false) String tituloDescricao, 
			@RequestParam(required = false) String responsavel, 
			@RequestParam(required = false) boolean concluida, 
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		
		return taskService.searchTask(numero, tituloDescricao, responsavel, concluida, page, size);
	}
	
	@ApiOperation(value = "Remove uma tarefa cadastrada no banco")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "A tarefa foi excluída"),
					@ApiResponse(code = 400, message = "A tarefa não existe no banco"),
					@ApiResponse(code = 500, message = "Foi gerada uma exceção")})
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{taskNumero}")
	public void removeTask(@PathVariable Long taskNumero) {
		taskService.removeTask(taskNumero);
	}
}
