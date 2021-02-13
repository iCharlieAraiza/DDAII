package com.coding;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        LectorAchivos archivo = new LectorAchivos("./texto.txt");
        archivo.crear("Hola! Esta es una prueba para comprobar que funciona. Segunda línea");
        System.out.println( "Leer archivo: " +  archivo.leer() );

    }
}
