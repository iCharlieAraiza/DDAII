package com.coding;

import com.google.gson.Gson;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

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

    public void toXml() throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("persona.xml")));
        encoder.writeObject(this);
        encoder.close();
        System.out.println("Desde toXML: Se ha creado/actualizado el archivo\n");
    }

    public void fromXml(String url){
        try{
            FileInputStream file = new FileInputStream(new File(url));
            XMLDecoder decoder = new XMLDecoder(file);
            Persona persona = (Persona)decoder.readObject();
            decoder.close();

            System.out.println("Desde fromXML: \n-Imprimiendo desde el archivo XML: " + persona.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fromJson(String json){
        Gson gson = new Gson();
        Persona persona = gson.fromJson(json, Persona.class);
        System.out.println("Desde fromJSON: "+ persona.toString()+"\n");
    }

    public void toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        System.out.println("Desde toJSON: "+ json+"\n");
    }

}
