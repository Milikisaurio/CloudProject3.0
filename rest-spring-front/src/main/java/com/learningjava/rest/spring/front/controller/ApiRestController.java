/**
 * CloudProject3.0
 * com.learningjava.rest.spring.front
 * Created by Emilio Martínez García
 */

// paquete de donde pertenece esta clase
package com.learningjava.rest.spring.front.controller;

// imports de ReadDB y Restaurants, framworks de spring y utils de java
import com.learningjava.rest.spring.core.ReadDB;
import com.learningjava.rest.spring.core.Restaurant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/rest/api/v1") // ruta de acceso (solo admin tiene acceso)

// clase pública del controlador de api rest
public class ApiRestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();



    @RequestMapping(path = "/restaurants",method = RequestMethod.GET) // ruta de acceso a los restaurantes

    // lista de restaurantes leidas desde la base de datos
    public List<Restaurant> list() {
        ReadDB readDB = new ReadDB();
        List<Restaurant> arrData = readDB.readRestaurant();
        return arrData;
    }

}
