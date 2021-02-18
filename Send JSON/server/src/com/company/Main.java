package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try{
            int port = 8000;
            ServerSocket myServer = new ServerSocket(port);
            Socket myClient = null;
            System.out.println("Esperando una conexión...");

            myClient = myServer.accept();
            System.out.println("Se ha aceptado la conexión.");

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            BufferedReader socketReader = new BufferedReader(streamSocket);
            PrintWriter socketWritter = new PrintWriter(myClient.getOutputStream(),true);

            String message = socketReader.readLine();
            System.out.println("Mensaje recibido");
            System.out.println(message);

            Persona persona = new Persona();
            persona.fromJson(message);

            System.out.println( persona.toString() );

            try{
                ConnectionDB db = new ConnectionDB();
                db.insertDatabase(persona);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
