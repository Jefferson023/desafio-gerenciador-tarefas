package com.gerenciador.tarefas.backend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gerenciador.tarefas.backend.service.AuthenticationService;
import com.gerenciador.tarefas.backend.service.TokenService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private AuthenticationService authService;
	
	private final TokenService tokenService;
	
	/**
	 * Lista de páginas do swagger que vão ser permitidas sem autenticação
	 */
	private static final String[] SWAGGER_PAGES = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/webjars/**"
    };

	/** lista de páginas da API que poderão ser acessadas sem auteticação **/
    private static final String[] AUTH_WHITELIST = {
            "/auth", "/user"
    };
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//swagger
        http.authorizeRequests().antMatchers(SWAGGER_PAGES).permitAll();
        
	    http.authorizeRequests()
	        .antMatchers(AUTH_WHITELIST).permitAll()
	        .antMatchers("/**").authenticated().and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .addFilterBefore(new TokenAuthenticationFilter(tokenService),
					UsernamePasswordAuthenticationFilter.class)
	        .csrf().disable();
	}	
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
