package com.mokasoft.personas.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<PersonaEntity> personasList = new ArrayList<>();
        result.forEach(personasList ::add);
        return personasList;
    }
    
    @PostMapping("/persona")
	public void agregarPersona(@RequestBody PersonaEntity nueva) {
    	personaRepository.save(nueva);
	}

	@GetMapping("/persona/{id}") //Consultar Persona
    public ResponseEntity <PersonaEntity> getPersonaByID(@PathVariable("id") Integer id){
        PersonaEntity personaEncontrada = personaRepository.findById(id).get();
        return new ResponseEntity(personaEncontrada, HttpStatus.OK);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity <Void> borrarPersona(@PathVariable("id") Integer id){
        personaRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/personas/{genero}/{fecha}")
    public ResponseEntity <List<PersonaEntity>> getPersonaByGeneroAndFecNac(@PathVariable("genero") String genero, @PathVariable("fecha") @DateTimeFormat(pattern="ddMMyyyy") Date fecha_Nacimiento){
        Iterable<PersonaEntity> result = personaRepository.findPersonaEntitiesByGeneroAndFechaNacimiento(genero, fecha_Nacimiento);
        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
