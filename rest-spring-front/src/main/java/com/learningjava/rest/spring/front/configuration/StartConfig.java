/**
 * CloudProject3.0
 * com.learningjava.rest.spring.front
 * Created by Emilio Martínez García
 */

// paquete de donde pertenece
package com.learningjava.rest.spring.front.configuration;

// imports de frameworks de spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// clase pública
@Configuration
public class StartConfig {

    // configura el modelo mvc con un mappeo
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }
}