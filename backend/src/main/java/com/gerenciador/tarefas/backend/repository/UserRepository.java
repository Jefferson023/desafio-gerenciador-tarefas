package com.gerenciador.tarefas.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.tarefas.backend.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

	public Optional<User> findByUsername(String username);
}
