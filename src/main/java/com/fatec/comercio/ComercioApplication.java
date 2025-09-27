package com.fatec.comercio;

import io.github.cdimascio.dotenv.Dotenv; // <-- ADICIONE ESTE IMPORT
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComercioApplication {

    public static void main(String[] args) {
        // Carrega as variáveis do arquivo .env
        Dotenv dotenv = Dotenv.load();

        // Configura as variáveis de ambiente para a aplicação Spring
        System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
        System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
        System.setProperty("DB_NAME", dotenv.get("DB_NAME"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASS", dotenv.get("DB_PASS"));

        SpringApplication.run(ComercioApplication.class, args);
    }

}