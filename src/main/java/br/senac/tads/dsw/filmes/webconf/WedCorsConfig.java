package br.senac.tads.dsw.filmes.webconf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WedCorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("GET", "POST", "DELETE")
					.allowedHeaders("*")
					.maxAge(3600);
	}
}
