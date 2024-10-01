package com.ll.site.global.webMvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ll.site.global.app.AppConfig;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			// 서버단 cors 허용
			.allowedOrigins(
				AppConfig.getSiteFrontUrl()
			)
			.allowedMethods("*")
			.allowedHeaders("*")
			.allowCredentials(true);
	}
}