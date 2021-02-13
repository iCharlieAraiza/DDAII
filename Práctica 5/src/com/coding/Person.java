package com.coding;

import java.util.ArrayList;
import java.util.List;

public class Person extends Serialize{
    public String name;
    public int age;
    public String gender;
    public Boolean status;

    public Person(){}

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        status = true;
    }

    public Person(String name, int age, String gender, Boolean status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
    }

    public String toString(){
        return "Nombre: " + name + " Edad: " + age + " Sexo: " + gender + " Status: " + status;
    }

    @Override
    public String toXML() {

        List<String> tags = new ArrayList<String>();

        tags.add( createTag("nombre", name) );
        tags.add( createTag("edad", Integer.toString(age)) );
        tags.add( createTag("genero", gender) );
        tags.add( createTag("status", status.toString()) );

        return super.createTag("Persona", tags);
    }

    @Override
    public void fromXML(String xml) {

        int startIndex, endIndex;

        if(xml.contains("nombre")){
            startIndex = xml.indexOf("<nombre>")+("<nombre>").length();
            endIndex = xml.indexOf("</nombre>");
            name = xml.substring(startIndex,endIndex);
        }

        if(xml.contains("edad")){
            startIndex = xml.indexOf("<edad>")+("<edad>").length();
            endIndex = xml.indexOf("</edad>");
            age = Integer.parseInt(xml.substring(startIndex,endIndex));
        }

        if(xml.contains("genero")){
            startIndex = xml.indexOf("<genero>")+("<genero>").length();
            endIndex = xml.indexOf("</genero>");
            gender = xml.substring(startIndex, endIndex);
        }

        if(xml.contains("status")){
            startIndex = xml.indexOf("<status>")+("<status>").length();
            endIndex = xml.indexOf("</status>");
            status = Boolean.parseBoolean(xml.substring(startIndex, endIndex));
        }


        System.out.println(this.toString());

    }

    @Override
    public String toJson() {

        String cadenaJson = "{";
        cadenaJson += "\"nombre\":\""+name+"\",";
        cadenaJson += "\"edad\":"+age+"\",";
        cadenaJson += "\"genero\":\""+gender+"\",";
        cadenaJson += "\"nombre\":"+status;

        cadenaJson += "}";
        return cadenaJson;
    }

    @Override
    public void fromJson(String json) {
        json = json.replace("\"", "");
        json = json.replace("{", "");
        json = json.replace("}", "");

        String[] valores = json.split(",");
        int inicio = 0, fin = 0;
        String dato = "", campo = "";

        for(String contenido : valores){
            inicio = contenido.indexOf(":")+1;
            fin = contenido.indexOf(":");

            dato = contenido.substring(inicio);
            campo = contenido.substring(0,fin);

            switch (campo){
                case "nombre":
                    name = dato;
                    break;
                case "edad":
                    age = Integer.parseInt(dato);
                    break;
                case "genero":
                    gender = dato;
                    break;
                case "estatus":
                    status = Boolean.parseBoolean(dato);
                    break;
            }


        }

    }
}
