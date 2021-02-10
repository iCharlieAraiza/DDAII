package com.coding;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Person juan = new Person("Juan", 33, "Masculino", true);
        System.out.println(juan.toXML());

        juan.test("<Persona><nombre>Juan</nombre>\n" +
                "<edad>33</edad>\n" +
                "<genero>Masculino</genero>\n" +
                "<status>true</status>\n" +
                "</Persona>");

    }
}
