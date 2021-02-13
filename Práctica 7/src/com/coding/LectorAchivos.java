package com.coding;
import java.io.*;

public class LectorAchivos {
    String url;

    public LectorAchivos(String url) throws IOException {
        this.url = url;
    }

    public void crear(String texto) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(url) );
        bw.write(texto);
        bw.close();
    }

    public String leer() throws IOException {
        StringBuilder texto = new StringBuilder();
        String s;
        BufferedReader br = new BufferedReader( new FileReader(url) );

        while( (s = br.readLine()) != null){
            texto.append(s);
        }
        br.close();¡
        return texto.toString();
    }
}
