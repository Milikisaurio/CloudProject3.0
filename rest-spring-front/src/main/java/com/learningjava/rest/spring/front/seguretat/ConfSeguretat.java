/**
 * CloudProject3.0
 * com.learningjava.rest.spring.front
 * Created by Emilio Martínez García
 */
// paquete de donde pertenece esta clase
package com.learningjava.rest.spring.front.seguretat;

// imports de frameworks de spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// clase de configuración de seguridad
@EnableWebSecurity
@Configuration
public class ConfSeguretat extends WebSecurityConfigurerAdapter{
    @Autowired
    // autentificación de usuarios
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }
// configuración de seguridad donde el único que tiene acceso al api rest es el administrador
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/public/**").permitAll()
                .antMatchers("/rest/api/v1/restaurants").hasRole("ADMIN")
                .and().formLogin()
                .and().httpBasic()
                .and().logout()
                .permitAll()
        ;
    }


}


