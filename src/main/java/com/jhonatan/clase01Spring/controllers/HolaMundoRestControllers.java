package com.jhonatan.clase01Spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestControllers {
    @GetMapping({"/holaMundo", "/hm", "/hola"})
    public String holaMundo(){
        System.out.println("solicitud ejecutada...."); //para ver que si se esta haciendo la solitud pendiente
        return "Hola Mundo";
    }
}
