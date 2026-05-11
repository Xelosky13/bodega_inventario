package com.proyecto_inventario.proyecto_inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectoInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoInventarioApplication.class, args);
	}

}
