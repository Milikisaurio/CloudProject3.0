/**
 * CloudProject3.0
 * com.learningjava.rest.spring.front
 * Created by Emilio Martínez García
 */

// paquete onde se situa esta clase
package com.learningjava.rest.spring.front.controller;

// imports de las clases readdb y restaurant, frameworks de spring y utils de java
import com.learningjava.rest.spring.core.ReadDB;
import com.learningjava.rest.spring.core.Restaurant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

// clase pública
@Controller
public class HomeController {
    @RequestMapping("/")
   // lectura de la base de datos con los restaurantes y listado de estos
    public String index(Model model) {
        ReadDB readDB = new ReadDB();
        List<Restaurant> restaurants = readDB.readRestaurant();
        model.addAttribute("restaurants", restaurants);
        return "index";
    }
}