package com.universidad.productosweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Spring Boot.
 * La anotación @SpringBootApplication activa:
 *  - @Configuration: permite definir beans
 *  - @EnableAutoConfiguration: configura Spring automáticamente
 *  - @ComponentScan: detecta componentes en el paquete base
 */
@SpringBootApplication
public class ProductosWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosWebApplication.class, args);
    }
}
