package com.ll.site.global.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Configuration  // Configuration 읽으면서 의존성 주입(Autowired 넣기, @Value 주입)도 함
public class AppConfig {
	@Getter
	public static ObjectMapper objectMapper;
	private static Environment environment;
	@Getter
	private static String jwtSecretKey;
	@Getter
	private static long accessTokenExpirationSec;
	@Getter
	private static String siteFrontUrl;
	@Getter
	private static String siteBackUrl;
	@Getter
	private static String siteCookieDomain;
	public static boolean isProd() {
		return environment.matchesProfiles("prod");
	}
	public static boolean isDev() {
		return environment.matchesProfiles("dev");
	}
	public static boolean isTest() {
		return environment.matchesProfiles("test");
	}
	public static boolean isNotProd() {
		return !isProd();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	@Value("${custom.secret.jwt.secretKey}")
	public void setJwtSecretKey(String jwtSecretKey) {
		this.jwtSecretKey = jwtSecretKey;
	}
	@Value("${custom.accessToken.expirationSec}")
	public void setJwtSecretKey(long accessTokenExpirationSec) {
		this.accessTokenExpirationSec = accessTokenExpirationSec;
	}
	@Value("${custom.site.frontUrl}")
	public void setSiteFrontUrl(String siteFrontUrl) {
		this.siteFrontUrl = siteFrontUrl;
	}
	@Value("${custom.site.backUrl}")
	public void setSiteBackUrl(String siteBackUrl) {
		this.siteBackUrl = siteBackUrl;
	}
	@Value("${custom.site.cookieDomain}")
	public void setSiteCookieDomain(String siteCookieDomain) {
		this.siteCookieDomain = siteCookieDomain;
	}
}