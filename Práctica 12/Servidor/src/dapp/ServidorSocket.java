package dapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dapp.Entity.ConnectDB;
import dapp.PersonasProyecto.Persona;
import dapp.ManejoArchivo.ArchivoHelper;
import dapp.Service.PersonService;


public class ServidorSocket {

	


	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		int op = 0;

		if(op == 1){
			mostrarPagina();
		}else{
			REST();
		}


	}


	public static void mostrarPagina(){
		int port = 8000;
		ServerSocket myServer = null;
		try {
			myServer = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Socket myClient = null;

		try{
			System.out.println("Esperando una conexión...");

			myClient = myServer.accept();


			System.out.println("Se ha aceptado la conexión.");

			InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
			BufferedReader socketReader = new BufferedReader(streamSocket),
					br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter socketWritter = new PrintWriter(myClient.getOutputStream(),true);
			String getMessage = "", sendMessage = "";

			StringBuilder contentBuilder = new StringBuilder();
			try {
				BufferedReader in = new BufferedReader(new FileReader("index.html"));
				String str;
				while ((str = in.readLine()) != null) {
					contentBuilder.append(str);
				}
				in.close();
			} catch (IOException e) {
			}
			String content = contentBuilder.toString();

			String mensajeRespuesta = "";
			mensajeRespuesta="HTTP/1.1 200 OK\n";
			mensajeRespuesta+="Content-Type: text/html\n";
			mensajeRespuesta+="Content-Length: " + content.length() + "\n\n" ;
			mensajeRespuesta+= content;


			socketWritter.println(mensajeRespuesta);
			System.out.println("Se ha cerrado la conexión");

		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

	}


	public static void REST() throws SQLException, ClassNotFoundException {
		int puerto=8000;

		ArrayList<Persona> misPersonas=new ArrayList<Persona>() ;
		inicializa(misPersonas);

		//mostrarPagina();

		PersonService personService = new PersonService(new ConnectDB() );

		try {

			ServerSocket miServidor = new ServerSocket(puerto);
			Socket miCliente;

			System.out.println("Esperando Conexion...");

			miCliente= miServidor.accept();

			System.out.println("Recibi Conexion");


			//----- Habilitar lectura y escritura del Socket
			BufferedReader lectorSocket;
			PrintWriter escritorSocket;

			InputStreamReader entradaDatos = new InputStreamReader(miCliente.getInputStream());
			lectorSocket= new BufferedReader(entradaDatos );

			escritorSocket= new PrintWriter(miCliente.getOutputStream(),true);
			//----


			String mensajeRespuesta, mensajeRecibido="",lineaRecibida;
			int opcion=0, totalContenido=0;

			System.out.println("Esperando mensaje....");


			lineaRecibida= lectorSocket.readLine();
			mensajeRecibido=lineaRecibida+"\n";

			lectorSocket.readLine();
			String body = lectorSocket.readLine();



			if (lineaRecibida.contains("GET"))
			{
				opcion=1;
				//Agregar el ID de la persona
				if(body.length()==0){
					ArrayList<Persona> personas = (ArrayList<Persona>) personService.selectAll();
					JsonArray jsonArray2 = new Gson().toJsonTree(personas).getAsJsonArray();
					System.out.println(jsonArray2.toString());
				}
				else{
					System.out.println(personService.select(Integer.parseInt(body)).toJson());
					String contenido = personService.select(Integer.parseInt(body)).toJson();

					mensajeRespuesta="HTTP/1.1 200 OK\n";
					mensajeRespuesta+="Content-Type: text/json\n";
					mensajeRespuesta+="Content-Length: " + contenido.length() + "\n\n" ;
					mensajeRespuesta+= contenido;
					escritorSocket.println(mensajeRespuesta);
				}

				personService.delete(3);


			}
			else if (lineaRecibida.contains("POST"))
			{
				opcion=2;

				Persona persona = new Persona();
				persona.fromJson(body);

				personService.create(persona);

				mensajeRespuesta="HTTP/1.1 200 OK\n";
				mensajeRespuesta+="Content-Type: text/json\n";
				mensajeRespuesta+="Content-Length: " + persona.toString().length() + "\n\n" ;
				mensajeRespuesta+= persona.toJson();

				escritorSocket.println(mensajeRespuesta);

				//escritorSocket.println("✅ Se ha creado correctamente");

			}
			else if (lineaRecibida.contains("PUT"))
			{
				opcion=3;
				Persona persona = new Persona();
				System.out.println(body);
				persona.fromJson(body);

				personService.update(persona);

				mensajeRespuesta="HTTP/1.1 200 OK\n";
				mensajeRespuesta+="Content-Type: text/json\n";
				mensajeRespuesta+="Content-Length: " + persona.toString().length() + "\n\n" ;
				mensajeRespuesta+= persona.toJson();

				escritorSocket.println(mensajeRespuesta);

			}
			else if (lineaRecibida.contains("DELETE")) {
				personService.delete(Integer.parseInt(body));

				mensajeRespuesta="HTTP/1.1 200 OK\n";
				mensajeRespuesta+="Content-Type: text/json\n";
				mensajeRespuesta+="Content-Length: " + 0 + "\n\n" ;
				mensajeRespuesta+= "";

				escritorSocket.println(mensajeRespuesta);

				opcion=4;
			}
			else {
				opcion=-1;
			}

			System.out.println("Opcion: " + opcion);



		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println("FIN del Programa");
	}



	public static void inicializa(ArrayList<Persona> misPersonas)
	{
			
		//String ruta="C:\\Users\\MXE02008906A\\OneDrive - AXA\\01.WinDevice\\Documents\\Ejercicios\\ServidorPersonas.json";
		
		misPersonas.add(new Persona("Ana Alejandra",30,"Femenino"));
			misPersonas.add(new Persona("Carmen Melo",27,"Masculino"));
			misPersonas.add(new Persona("Pedro Granillo",40,"Masculino"));
			misPersonas.add(new Persona("Jose Serrano",27,"Masculino"));
		
			
	}
	
}
