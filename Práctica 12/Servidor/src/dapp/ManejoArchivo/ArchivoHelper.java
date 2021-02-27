package dapp.ManejoArchivo;

import java.io.*;


public class ArchivoHelper {

	
	public static String leerArchivo(String ruta)
	{
		String contenido="";
		String linea="";
		
		File miArchivo = new File(ruta);
		
		try {
			
			if (miArchivo.exists())
			{
				
					FileReader miLectorArchivo= new FileReader(miArchivo);
					BufferedReader bufferArchivo = new BufferedReader(miLectorArchivo);
				
					while ((linea=bufferArchivo.readLine())!=null)
					{
						contenido+=linea + "\n";
					}
					
			}
			
		
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
		return contenido;
	}
	
	public static void escribirArchivo(String ruta, String contenido)
	{
		try {
			
			FileWriter escritorArchivo = new FileWriter(ruta);
			PrintWriter impresorArchivo = new PrintWriter(escritorArchivo);
			
			impresorArchivo.println(contenido);
			
			impresorArchivo.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	public static byte[] leerArchivoBinario(String ruta) throws Exception
	{
		byte[] contenido=null;
		
		FileInputStream archivo = new FileInputStream(ruta) ;
		DataInputStream datos = new DataInputStream(archivo);
		
		int total= datos.available();
		
		contenido= new byte[total];
		
		datos.read(contenido);
		
		return contenido;
	}
}
