package com.gerenciador.tarefas.backend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gerenciador.tarefas.backend.dto.AuthResponseDto;
import com.gerenciador.tarefas.backend.entity.User;
import com.gerenciador.tarefas.backend.repository.UserRepository;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expirationMs;
	
	public AuthResponseDto createResponseAuthToken(Authentication authentication) {
		final var token = generateToken(authentication);
		return new AuthResponseDto(token);
	}

	private String generateToken(Authentication authentication) {

		var user = (UserDetails) authentication.getPrincipal();
		
		final var dateNow = new Date();
		final var expirationDate = Date.from(dateNow.toInstant().plusMillis(expirationMs));
		return Jwts.builder().setIssuer("Tarefas")
                             .setSubject(user.getUsername())
                             .setIssuedAt(dateNow)
				             .setExpiration(expirationDate)
                             .signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	/**
	 * Verifica a validade de um token JWT.
	 * @param token Token a ser validado.
	 * @return true, se o token é válido. False, caso contrário.
	 */
	private boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * Retira o token JWT de um requisição passada por parâmetro.
	 * 
	 * @param requisicao requisição para extrair o token.
	 * @return String com o token JWT, ou null caso ele não exista na requisição.
	 */
	private String getTokenFromRequest(String bearerToken) {
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
			return bearerToken.substring(7);
		return null;
	}
	
	private String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret)
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public User getUserFromToken(String bearerToken) {
		
		var token = getTokenFromRequest(bearerToken);
		if (isTokenValid(token)) {
			var username = getUserNameFromToken(token); 
			return userRepository.findByUsername(username).orElseGet(() -> null);
		}else {
			return null;
		}
	}
}
