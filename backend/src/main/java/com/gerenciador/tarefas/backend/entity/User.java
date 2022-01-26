package com.gerenciador.tarefas.backend.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name="users")
public class User implements Serializable, UserDetails{

	private static final long serialVersionUID = 2502071422354917312L;

	@Id
	private String username;
	
	private String password;
	
	private String name;

	/**
	 * Lista vazia de permissões já que não serão utilizadas. 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority> emptyList();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
