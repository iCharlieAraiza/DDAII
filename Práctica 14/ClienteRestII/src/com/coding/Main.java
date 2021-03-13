package com.coding;

import com.coding.model.Persona;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Main {

    static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static private String name, gender;
    static private int age;

    public static void main(String[] args) throws IOException, InterruptedException {
	// write your code here

        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        int op = 0,
                id = 0;

        do {
            System.out.println("Elige una opcion: \n0) Mostrar todos \n1) GET \n2) POST \n3) PUT \n4) DELETE\n5) Salir\n");
            op = Integer.parseInt(io.readLine());
            switch (op) {
                case 0:
                    GETall();
                    break;
                case 1:
                    System.out.println("Escribe el id: ");
                    id = Integer.parseInt(io.readLine());
                    GET(id);
                    break;
                case 2:
                    POST();
                    break;
                case 3:
                    PUT();
                    break;
                case 4:
                    System.out.println("Escribe el id: ");
                    id = Integer.parseInt(io.readLine());
                    DELETE(id);
                    break;
                case 5:
                    op = -1;
                    break;
                default:
                    System.out.println("Has agregado una opción no válida.");
                    op = -1;
            }
        }while(op >= 0);

    }

    static void GETall() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( URI.create("http://localhost:8080/"))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }


    static void GET(int id) throws IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri( URI.create("http://localhost:8080/api/"+ id))
                .build();

        HttpResponse<String> response2 =
                client.send(request, HttpResponse.BodyHandlers.ofString());


        if(response2.statusCode()==200){

            Persona persona = new Persona();
            persona.fromJson(response2.body());

            System.out.println( (persona.genero.equals("Femenino") ? "👩" : "🧑‍" ) + " " + persona.toString()+"\n" );
        }else{
            System.out.println("¡Ups! Error " + response2.statusCode() + " ❌\n");
        }


    }

    static void POST() throws IOException {
        URL url = new URL("http://localhost:8080/api/");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);


        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribir la edad: ");
        age = Integer.parseInt( br.readLine() );

        System.out.println("Escribe el sexo: ");
        gender = br.readLine();

        Persona persona = new Persona(name, age, gender);

        String peticion = persona.toJson();


        byte[] out = peticion.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

    }

    static void PUT() throws IOException {
        URL url = new URL("http://localhost:8080/api/update");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("PUT"); // PUT is another valid option
        http.setDoOutput(true);

        System.out.println("Escribe el ID: ");
        int id = Integer.parseInt(br.readLine());

        System.out.println("Escribe el nombre: ");
        name = br.readLine();

        System.out.println("Escribir la edad: ");
        age = Integer.parseInt( br.readLine() );

        System.out.println("Escribe el sexo: ");
        gender = br.readLine();

        Persona persona = new Persona(id, name, age, gender);

        String peticion = persona.toJson();

        byte[] out = peticion.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

    }


    static void DELETE(int id) throws IOException, InterruptedException {
        String url = "http://localhost:8080/api/"+id;

        var request = HttpRequest.newBuilder()
                .uri(URI.create(url)).header("Content-Type","application/json")
                .DELETE()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()==200){
            System.out.println("Ok " + response.statusCode());
            System.out.println("Se ha borrado correctamente ✅");
        }
        System.out.println(response.body());
    }



}
