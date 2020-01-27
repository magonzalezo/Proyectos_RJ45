package com.mokasoft.personas.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="persona", schema="miguel")
public class PersonaEntity {

	@Id
	private Integer id;
    
	@Column(name="primer_nombre")
	private String primerNombre;
	
	@Column(name="segundo_nombre")
	private String segundoNombre;
	
	@Column(name="primer_apellido")
    private String primerApellido;
	
	@Column(name="segundo_apellido")
    private String segundoApellido;
	
	@Column(name="fecha_nacimiento")
    private Date fechaNacimiento;
	
	@Column(name="genero")
    private String genero;
	
}
