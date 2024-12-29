package com.jhonatan.clase01Spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoRestController {

    @GetMapping({"/saludo/{nombre}", "/hola/{nombre}"}) //estamos recibiendo un parametro dinamico de la url
    public String saludo(@PathVariable String nombre) {//indicamos que el parametro va a venir de la url
        return "Hola " + nombre;
    }
}
