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
    public ResponseEntity <?> obtenerPersonas(){

        Iterable<PersonaEntity> result = null;
        List<PersonaEntity> personasList = null;
        Map<String, Object> response = new HashMap<>();

        try {
            result = personaRepository.findAll();

            if (null != result && !((List)result).isEmpty()){

                personasList = new ArrayList<>();
                result.forEach(personasList ::add);

                return new ResponseEntity(personasList, HttpStatus.OK);
            } else {
                response.put("mensaje","No hay personas que consultar.");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

        } catch(DataAccessException dae){
            response.put("mensaje","Error al consultar en base de datos.");
            response.put("error", dae.getMessage() + " ### " + dae.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("mensaje","Error general");
            response.put("error", ex.getMessage() + " ### " + ex.getCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
	public ResponseEntity <?> agregarPersona(@RequestBody PersonaEntity nueva) {

        Map<String, Object> response = new HashMap<>();

        try {
            personaRepository.save(nueva);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DataAccessException dae) {
            response.put("mensaje","Error al guardar en base de datos.");
            response.put("error", dae.getMessage() + " ### " + dae.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("mensaje","Error general");
            response.put("error", ex.getMessage() + " ### " + ex.getCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
        } catch (Exception ex) {
            response.put("mensaje","Error general");
            response.put("error", ex.getMessage() + " ### " + ex.getCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> borrarPersona(@PathVariable("id") Integer id){

        Map<String, Object> response = new HashMap<>();

        try {
            personaRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DataAccessException dae) {
            response.put("mensaje","Error al eliminar en base de datos.");
            response.put("error", dae.getMessage() + " ### " + dae.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("mensaje","Error general");
            response.put("error", ex.getMessage() + " ### " + ex.getCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{genero}/{fecha}")
    public ResponseEntity <?> obtenerPersonaPorGeneroYFecNac(@PathVariable("genero") String genero, @PathVariable("fecha") @DateTimeFormat(pattern="ddMMyyyy") Date fecha_Nacimiento){
        Map<String, Object> response = new HashMap<>();
        Iterable<PersonaEntity> result = null;

        try {
            result = personaRepository.findPersonaEntitiesByGeneroAndFechaNacimiento(genero, fecha_Nacimiento);

            if (null != result && !((List)result).isEmpty()){
                List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
                result.forEach(personasList ::add);
                return new ResponseEntity(result, HttpStatus.OK);
            } else {
                response.put("mensaje","No se pudo encontrar a las personas con ese criterio de busqueda.");
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException dae){
            response.put("mensaje","Error al consultar en base de datos.");
            response.put("error", dae.getMessage() + " ### " + dae.getMostSpecificCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("mensaje","Error general");
            response.put("error", ex.getMessage() + " ### " + ex.getCause().getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
