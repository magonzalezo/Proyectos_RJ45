package com.example.demo.principal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Inicio {

	private List<Persona> personas  = new ArrayList<>();
	
	@GetMapping("/personas")
	public List<Persona> getPersonas(){
		return personas;
	}
	
	@PostMapping("/persona")
	public void agregarPersona(@RequestBody Persona nueva) {
		System.out.println(nueva);
		personas.add(nueva);
	}
	
	
	@RequestMapping("/")
	public String getHelloWorld() {
		return "Hello word";
	}
}
