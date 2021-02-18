package com.coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Socket myClient = null;
        String host = "127.0.0.1";
        int port = 8000;
        String name, gender;
        int age;

        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribir la edad: ");
        age = Integer.parseInt( br.readLine() );

        System.out.println("Escribe el sexo: ");
        gender = br.readLine();

        Persona persona = new Persona(name, age, gender);

        try {
            System.out.println("Conectando al servidor");
            myClient = new Socket(host, port);

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);

            socketWriter.println(persona.toJson());

        }catch (Exception ex){
            System.out.println(ex);
        }


    }

    public static void server() {
        Socket myCLient = null;
        String host = "127.0.0.1";
        int port = 8000;

        try {
            System.out.println("Conectado al servidor");
            myCLient = new Socket(host, port);
            System.out.println("Me he conectado");

            InputStreamReader streamSocket = new InputStreamReader(myCLient.getInputStream());
            BufferedReader socketReader = new BufferedReader(streamSocket);
            PrintWriter socketWriter =new PrintWriter( myCLient.getOutputStream(), true);
            String receivedMessage;

            socketWriter.println("Te has conectado al servidor.");
            receivedMessage = socketReader.readLine();
            System.out.println("Servidor: " + receivedMessage);

        }catch (Exception ex){
            System.out.println(ex);
        }

    }
}


