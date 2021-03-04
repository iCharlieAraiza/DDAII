package com.example.webservice.controller;

import com.example.webservice.model.Persona;
import com.example.webservice.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonaContoller {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<Persona> showAll(){
        String json = "";
        List<Persona> personas = personaService.getAllPersonas();

        for (Persona persona : personas){
            json += persona.toString();
        }

        return personas;
    }

    @GetMapping("/{id}")
    public Persona showPerson(@PathVariable("id") Integer id){
        return personaService.getPersonById(id);
    }


    @PutMapping("/update")
    public void savePersona(@RequestBody Persona persona){
        personaService.addNewPersona(persona);
    }

    @PostMapping
    public void registerNewPersona(@RequestBody Persona persona){
        personaService.addNewPersona(persona);
    }

    @DeleteMapping("{id}")
    public void delitePersona(@PathVariable("id") Integer id){
        personaService.deletePersona(id);
    }

}
