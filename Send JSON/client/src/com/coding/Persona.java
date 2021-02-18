package com.coding;

public class Persona {
    public String nombre;
    public int edad;
    public String genero;
    public boolean estatus;

    public Persona(){}

    public Persona(String nombre, int edad, String genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.estatus = true;
    }

    public Persona(String nombre, int edad, String genero, boolean estatus) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.estatus = estatus;
    }

    public String toString(){
        return "Nombre: "+nombre+". Edad: " + edad + ". Género: " + genero + ". Estatus: " + estatus+"\n";
    }

    public String toJson(){
        String cadenaJson = "{";
        cadenaJson += "\"nombre\":\""+nombre+"\",";
        cadenaJson += "\"edad\":"+edad+",";
        cadenaJson += "\"genero\":\""+genero+"\",";
        cadenaJson += "\"estatus\":"+estatus;
        cadenaJson += "}";
        return cadenaJson;
    }


}
