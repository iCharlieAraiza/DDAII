package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
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
            //Mensaje
            String sendMessage, receivedMessage;

            sendMessage = "Hola, soy Carlos";
            System.out.println("Enviado mensaje...");
            socketWriter.println(sendMessage);

            System.out.println("Mensaje recibido");
            receivedMessage = socketReader.readLine();
            System.out.println(receivedMessage);

        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
