package com.mokasoft.personas.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mokasoft.personas.entities.PersonaEntity;
import com.mokasoft.personas.repositories.PersonaRepository;

@RestController
public class PersonaController {

	@Autowired
	private PersonaRepository personaRepository;
	
    @GetMapping("/")
    public String getPersona(){
        return "Hola";
    }
    
    @GetMapping("/personas")
    public List<PersonaEntity> getPersonas(){
    	Iterable<PersonaEntity> result = personaRepository.findAll();
        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return personasList;
    }
    
    @PostMapping("/persona")
	public void agregarPersona(@RequestBody PersonaEntity nueva) {
    	personaRepository.save(nueva);
	}    
}
