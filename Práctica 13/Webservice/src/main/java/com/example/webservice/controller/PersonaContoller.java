package com.example.webservice.controller;

import com.example.webservice.model.Persona;
import com.example.webservice.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class PersonaContoller {

    @Autowired
    PersonaService personaService;

    @GetMapping("/api")
    public String showAll(){
        String json = "";
        List<Persona> personas = personaService.getAllPersonas();

        for (Persona persona : personas){
            json += persona.toString();
        }

        return json;
    }

}
