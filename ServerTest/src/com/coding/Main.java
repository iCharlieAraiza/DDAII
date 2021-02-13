package com.coding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        int port = 8000;

        try{
            ServerSocket myServer = new ServerSocket(port);
            Socket myClient = null;
            System.out.println("Esperando por una conexión...");

            myClient = myServer.accept();

            System.out.println("Acepta conexión");

            //

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            BufferedReader socketReader = new BufferedReader(streamSocket);

            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);

            //Mensaje
            String sendMessage, receivedMessage;

            System.out.println("Esperando mensaje...");

            receivedMessage = socketReader.readLine();
            System.out.println(receivedMessage);

            socketWriter.println("He recibido tu mensaje!");
            System.out.println("Enviando mensaje");




        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
