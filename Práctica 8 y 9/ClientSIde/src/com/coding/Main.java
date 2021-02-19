package com.coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static private Socket myClient = null;
    static private String host = "127.0.0.1";
    static private int port = 8000;
    static private String name, gender;
    static private int age;

    static private Persona persona;


    public static void main(String[] args) throws IOException {
	// write your code here


        int op = 1;
        switch (op){
            case 1:
                practica8();
                break;
            case 2:
                inputPersona();
                practica9A();
                break;
            case 3:
                inputPersona();
                practica9B();
                break;
            default:
                System.out.println("Ups, has agregado una opción no válida.");
        }



    }


    static void practica8(){

        try {
            System.out.println("Conectado al servidor");
            System.out.println("Esperando por una conexión...");


            System.out.println("Conectando al servidor");
            myClient = new Socket(host, port);

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);
            BufferedReader bufferedReader =new BufferedReader(streamSocket),
                    bufferInput = new BufferedReader( new InputStreamReader(System.in));

            String sendMessage = "", receivedMessage="";

            System.out.println("Esperando mensaje...");

            while(!sendMessage.equals("ADIOS")){
                receivedMessage = bufferedReader.readLine();
                System.out.println("🖥 Servidor: " + receivedMessage );

                System.out.print("🤔 Tú: ");
                sendMessage = bufferInput.readLine();
                socketWriter.println(sendMessage);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    static void inputPersona() throws IOException {
        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribir la edad: ");
        age = Integer.parseInt( br.readLine() );

        System.out.println("Escribe el sexo: ");
        gender = br.readLine();

        persona = new Persona(name, age, gender);
    }


    static void practica9A(){
        try {
            System.out.println("Conectando al servidor");
            myClient = new Socket(host, port);

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);

            System.out.println(persona.toXML());

            socketWriter.println(persona.toXML());

        }catch (Exception ex){
            System.out.println(ex);
        }
    }



    static void practica9B(){
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


}


