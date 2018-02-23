/**
 * CloudProject3.0
 * com.learningjava.rest.spring.front
 * Created by Emilio Martínez García
 */

// paquete perteneciente al front
package com.learningjava.rest.spring.front;

// imports de framworks de spring
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

// clase pública que se extiende de el servlet de inicio del sistema de arranque de spring
@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}