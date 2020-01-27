package com.mokasoft.personas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {

    @GetMapping("/")
    public String getPersona(){
        return "Hola";
    }
}
