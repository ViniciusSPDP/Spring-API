package com.fatec.comercio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta é uma classe de configuração do Spring
public class CorsConfig implements WebMvcConfigurer {
 @Override
 public void addCorsMappings(CorsRegistry registry) {
 registry.addMapping("/**") // Aplica a configuração CORS a todos os caminhos (endpoints) da API
 .allowedOrigins("http://localhost:4200") // Permite requisições originadas do frontend Angular (rodando em localhost:4200)
 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Especifica os métodos HTTP permitidos
 .allowedHeaders("*") // Permite todos os tipos de cabeçalhos na requisição
 .allowCredentials(true); // Permite o envio de credenciais (como cookies ou tokens de autorização) nas requisições
 }
}