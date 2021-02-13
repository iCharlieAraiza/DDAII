package com.coding;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        Persona persona = new Persona();
        //Persona persona = new Persona("Juan", 20, "hombre" );
        //persona.toXml();

        persona.fromXml("./persona.xml");

        persona.toJson();

        persona.fromJson("{'nombre':'María','edad':30,'genero':'hombre'}");

    }
}
