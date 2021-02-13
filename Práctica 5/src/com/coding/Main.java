package com.coding;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Person juan = new Person("Juan", 33, "Masculino", true);
        System.out.println("\n"+"Pasar a XML: "+"\n"+juan.toXML());

        juan.fromXML("<persona><nombre>María</nombre><edad>23</edad><genero>Femenino</genero></persona>");
        System.out.println(juan.toJson());


        juan.fromJson("{nombre:\"Carlos\",genero:\"Masculino\"}");
        System.out.println(juan.toString());


    }
}
