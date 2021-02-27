package dapp.PersonasProyecto;

import java.util.ArrayList;

public class Persona {

	public int id;
	public String nombre;
	public int edad;
	public String genero;
	public boolean estatus;
	
	
	public Persona()
	{
		
	}
	
	public Persona(String nombre,int edad, String genero)
	{
		this.nombre=nombre;
		this.edad=edad;
		this.genero=genero;
		estatus=true;
	}
	
	public Persona(String nombre,int edad, String genero, boolean estatus)
	{
		this.nombre=nombre;
		this.edad=edad;
		this.genero=genero;
		this.estatus=estatus;
	}

	public Persona(int id, String nombre,int edad, String genero)
	{
		this.id = id;
		this.nombre=nombre;
		this.edad=edad;
		this.genero=genero;
		estatus=true;
	}
	
	@Override
	public String toString()
	{	
		return nombre +" "+ edad + " " + genero + " Activo:" + estatus ; 
	}
	
	public String toXml()
	{
		String cadenaXml="<Persona>";
		
		cadenaXml+="<nombre>" + nombre +"</nombre>";
		cadenaXml+="<edad>" + edad +"</edad>";
		cadenaXml+="<genero>" + genero +"</genero>";
		cadenaXml+="<estatus>" + estatus +"</estatus>";
		
		cadenaXml += "</Persona>";
		
		return cadenaXml;
	}
	
	public void fromXml(String cadenaXml)
	{
		int inicio, fin;
		String dato;
		
		inicio=cadenaXml.indexOf("<nombre>")+("<nombre>").length() ;
		fin= cadenaXml.indexOf("</nombre>");
		dato=cadenaXml.substring(inicio,fin);
		nombre= dato;
		
		//System.out.println(inicio + " " + fin);
		//System.out.println(dato);
		
		inicio=cadenaXml.indexOf("<edad>")+("<edad>").length() ;
		fin= cadenaXml.indexOf("</edad>");
		dato=cadenaXml.substring(inicio,fin);
		edad= Integer.parseInt(dato);
		
		inicio=cadenaXml.indexOf("<genero>")+("<genero>").length() ;
		fin= cadenaXml.indexOf("</genero>");
		dato=cadenaXml.substring(inicio,fin);
		genero= dato;
			
		inicio=cadenaXml.indexOf("<estatus>")+("<estatus>").length() ;
		fin= cadenaXml.indexOf("</estatus>");
		dato=cadenaXml.substring(inicio,fin);
		estatus= Boolean.parseBoolean(dato);
			
		
	}
	
	public String toJson()
	{
		String cadenaJson="{";

		cadenaJson+="\"nombre\":\""+nombre +"\",";
		cadenaJson+="\"edad\":" + edad +",";
		cadenaJson+="\"genero\":\""+genero +"\",";
		cadenaJson+="\"estatus\":" + estatus;
		
		cadenaJson+="}";
		
		//{"nombre":"Jose Serrano","edad":27,"genero":"Masculino","estatus":true}
		
		return cadenaJson;
	}
	
	public void fromJson( String cadenaJson)
	{
		cadenaJson=cadenaJson.replace("\"", "");
		cadenaJson=cadenaJson.replace("{", "");
		cadenaJson=cadenaJson.replace("}", "");
		
		String [] valores= cadenaJson.split(",");
		
		int inicio=0,fin=0;
		String dato="",campo="";
		for (String contenido:valores)
		{
			inicio= contenido.indexOf(":") + 1;
			fin= contenido.indexOf(":");
			
			dato = contenido.substring(inicio);
			campo= contenido.substring(0,fin);
			
			switch(campo){
				case "id": 		id = Integer.parseInt(dato);
								break;
				case "nombre":	nombre=dato;
								break;
				case "edad": 	edad=Integer.parseInt(dato);
								break;
				case "genero":	genero=dato;
								break;
				case "estatus":	estatus=Boolean.parseBoolean(dato);
				break;				
			}
			
			//System.out.println(dato);
		}
		
	}
	
	public static String toXmlList(ArrayList<Persona> personas)
	{
		String cadenaXml="<Personas>";
		
		for (Persona persona:personas)
		{
			cadenaXml += persona.toXml();
		}
		
		cadenaXml+="</Personas>";
		return cadenaXml;
	}
	
	public static String toJsonList(ArrayList<Persona> personas)
	{
		String cadenaJson="[";
		int i=0;
		int size = personas.size()-1;
		for (Persona persona:personas)
		{
			
			cadenaJson += persona.toJson();
			
			if (i!=size)
			{
				cadenaJson += ",";
			}
			
			i++;
		}
		
		cadenaJson+="]";
		return cadenaJson;
	}
	
	public static void fromJsonList(String cadenaJson,  ArrayList<Persona> personas)
	{
		
		
		cadenaJson=cadenaJson.replace("[", "");
		cadenaJson=cadenaJson.replace("]", "");
		
		String [] valores= cadenaJson.split("},");
		
		for (String subCadena:valores)
		{
			Persona nuevaPersona = new Persona();
			
			//System.out.println(subCadena);
			nuevaPersona.fromJson(subCadena);
			
			personas.add(nuevaPersona);
		}
		
	}
	
	
}

