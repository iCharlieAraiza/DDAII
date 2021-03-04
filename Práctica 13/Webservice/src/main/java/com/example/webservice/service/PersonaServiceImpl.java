package com.example.webservice.service;

import com.example.webservice.model.Persona;
import com.example.webservice.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Persona getPersonById(int id) {
        Optional<Persona> optional = personaRepository.findById(id);

        Persona persona = null;

        if(optional.isPresent()){
            persona = optional.get();
        }else{
            throw new RuntimeException("No se ha encontrado la persona");
        }
        return persona;
    }

    @Override
    public void addNewPersona(Persona persona) {
        //System.out.println( persona.toString() );
        personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Integer id) {
        boolean exist = personaRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Person with id " + id + " doesn't exist");
        }
        personaRepository.deleteById(id);

    }


}
