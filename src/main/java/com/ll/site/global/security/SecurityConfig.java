package com.ll.site.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers(HttpMethod.POST, "/api/*/members", "/api/*/members/login")
					.permitAll()
					.requestMatchers(HttpMethod.DELETE, "/api/*/members/logout")
					.permitAll()
					.requestMatchers(HttpMethod.GET, "/api/*/posts", "/api/*/posts/{id}")
					.permitAll()
					.requestMatchers("/h2-console/**")
					.permitAll()
					.requestMatchers("/actuator/**")
					.permitAll()
					.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
					.permitAll()
					.requestMatchers(HttpMethod.GET, "/")
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.headers(
				headers ->
					headers.frameOptions(
						frameOptions ->
							frameOptions.sameOrigin()
					)
			)
			.csrf(
				csrf ->
					csrf.disable()
			)
			.formLogin(
				formLogin ->
					formLogin
						.permitAll()
			);
		return http.build();
	}
}