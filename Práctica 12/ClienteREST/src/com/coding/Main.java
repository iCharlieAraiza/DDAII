package com.coding;

import com.coding.Model.Persona;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static private Socket myClient = null;
    static private String host = "127.0.0.1";
    static private int port = 8000;
    static private String name, gender;
    static private int age;

    static private Persona persona;

    public static void main(String[] args) {
	// write your code here


        try {
            System.out.println("Conectando al servidor");
            myClient = new Socket(host, port);

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);
            BufferedReader bufferedReader =new BufferedReader(streamSocket);

            //GET(socketWriter, 1);
            //POST(socketWriter);
            //PUT(socketWriter);
            DELETE(socketWriter,3);

            String response = "";

            for(int i = 0; i<5; i++){
                response+=bufferedReader.readLine()+"\n";
            }

            System.out.println(response);


        }catch (Exception ex){
            System.out.println(ex);
        }


    }


    static void GET(PrintWriter socketWriter, int id) throws IOException {
        String peticion = header("GET", Integer.toString(id));

        socketWriter.println(peticion);
        Persona persona = new Persona();
    }

    static void POST(PrintWriter socketWriter) throws IOException {

        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribir la edad: ");
        age = Integer.parseInt( br.readLine() );

        System.out.println("Escribe el sexo: ");
        gender = br.readLine();

        persona = new Persona(name, age, gender);

        String peticion = header("POST", persona.toJson());

        socketWriter.println(peticion);
        //System.out.println( bufferedReader.readLine() );
    }

    static void PUT(PrintWriter socketWriter) throws IOException {
        System.out.println("Escribe el ID: ");
        int id = Integer.parseInt(br.readLine());

        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribir la edad: ");
        age = Integer.parseInt( br.readLine() );

        System.out.println("Escribe el sexo: ");
        gender = br.readLine();

        persona = new Persona(id, name, age, gender);

        String peticion = header("PUT", persona.toJson());

        socketWriter.println(peticion);
        //System.out.println( bufferedReader.readLine() );
    }

    static void DELETE(PrintWriter socketWriter, int id) throws IOException {
        String peticion = header("DELETE", Integer.toString(id));
        socketWriter.println(peticion);
    }


    static String header(String method, String body){
        return method + "\n\n" + body;
    }



}
