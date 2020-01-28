package com.mokasoft.personas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mokasoft.personas.entities.PersonaEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer>{

    @Query("SELECT a FROM PersonaEntity a WHERE a.genero=:genero AND a.fechaNacimiento=:fecha_nacimiento")
    List<PersonaEntity> findPersonaEntitiesByGeneroAndFechaNacimiento(@Param("genero") String genero, @Param("fecha_nacimiento") Date fecha_nacimiento);

}
