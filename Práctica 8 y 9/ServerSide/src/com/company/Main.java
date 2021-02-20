package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    private static int port = 8000;
    private static ServerSocket myServer;

    static {
        try {
            myServer = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Socket myClient = null;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        int op = 4;
        switch (op){
            case 1:
                practica8();
                break;
            case 2:
                practica9A();
                break;
            case 3:
                practica9B();
                break;
            case 4:
                practica10();
                break;
            default:
                System.out.println("Ups, has agregado una opción no válida.");
        }

    }


    static private void practica8(){
        try{
            System.out.println("Esperando una conexión...");

            myClient = myServer.accept();
            System.out.println("Se ha aceptado la conexión.");

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            BufferedReader socketReader = new BufferedReader(streamSocket),
                            br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter socketWritter = new PrintWriter(myClient.getOutputStream(),true);
            String getMessage = "", sendMessage = "";

            while(!getMessage.equals("ADIOS")){
                System.out.print("🖥 Tú: ");
                sendMessage = br.readLine();;

                socketWritter.println(sendMessage);

                getMessage = socketReader.readLine();
                System.out.println("🤔 Cliente: " + getMessage);
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    static private void practica9A(){

        try{
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
            persona.fromXml(message);

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

    static private void practica9B(){
        try{
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

    static void practica10() throws SQLException, ClassNotFoundException {
        ConnectionDB db = new ConnectionDB();
        ArrayList<Persona> personas = (ArrayList<Persona>) db.getAllPersonas();

        for(Persona persona: personas){
            System.out.println(persona.toString());
        }

        JsonArray jsonArray2 = new Gson().toJsonTree(personas).getAsJsonArray();
        System.out.println(jsonArray2.toString());

        try{
            System.out.println("Esperando una conexión...");

            myClient = myServer.accept();
            System.out.println("Se ha aceptado la conexión.");

            PrintWriter socketWritter = new PrintWriter(myClient.getOutputStream(),true);

            socketWritter.println(jsonArray2.toString());
            System.out.println("Mensaje recibido");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
