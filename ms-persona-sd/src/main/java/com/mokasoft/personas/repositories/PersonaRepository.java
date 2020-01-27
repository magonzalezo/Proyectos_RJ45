package com.mokasoft.personas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mokasoft.personas.entities.PersonaEntity;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer>{

}
