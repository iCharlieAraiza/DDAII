package com.example.webservice.service;

import com.example.webservice.model.Persona;

import java.util.List;

public interface PersonaService  {
    List<Persona> getAllPersonas();
    Persona getPersonById(int id);

    void addNewPersona(Persona persona);

    void deletePersona(Integer id);
}
