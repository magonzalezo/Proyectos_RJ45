package com.mokasoft.personas.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mokasoft.personas.entities.PersonaEntity;
import com.mokasoft.personas.repositories.PersonaRepository;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private PersonaRepository personaRepository;
	
    @GetMapping("/test")
    public String testPersonas(){
        return "Hola! Te respondo desde personas";
    }
    
    @GetMapping("/")
    public List<PersonaEntity> obtenerPersonas(){
    	Iterable<PersonaEntity> result = personaRepository.findAll();
        List<PersonaEntity> personasList = new ArrayList<>();
        result.forEach(personasList ::add);
        return personasList;
    }
    
    @PostMapping("/")
	public void agregarPersona(@RequestBody PersonaEntity nueva) {

        personaRepository.save(nueva);
	}

	@GetMapping("/{id}")
    public ResponseEntity <?> obtenerPersonaPorID(@PathVariable("id") Integer id){
        PersonaEntity personaEncontrada = null;
        Map<String, Object> response = new HashMap<>();

        try {
            personaEncontrada = personaRepository.findById(id).orElse(null);

            if (personaEncontrada == null) {
                response.put("mensaje","No se pudo encontrar a la persona.");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(personaEncontrada, HttpStatus.OK);

        } catch (DataAccessException dae){
            response.put("mensaje","Error al consultar en base de datos.");
            response.put("error", dae.getMessage() + " ### " + dae.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> borrarPersona(@PathVariable("id") Integer id){
        personaRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{genero}/{fecha}")
    public ResponseEntity <List<PersonaEntity>> obtenerPersonaPorGeneroYFecNac(@PathVariable("genero") String genero, @PathVariable("fecha") @DateTimeFormat(pattern="ddMMyyyy") Date fecha_Nacimiento){
        Iterable<PersonaEntity> result = personaRepository.findPersonaEntitiesByGeneroAndFechaNacimiento(genero, fecha_Nacimiento);
        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
