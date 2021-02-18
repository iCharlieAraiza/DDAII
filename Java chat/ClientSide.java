import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSide {

    public static void main(String[] args) {
        int port = 8000;

        try{
            ServerSocket myServer = new ServerSocket(port);
            Socket myClient = null;
            System.out.println("Esperando por una conexión...");

            myClient = myServer.accept();

            System.out.println("Acepta conexión");

            InputStreamReader streamSocket = new InputStreamReader(myClient.getInputStream());
            BufferedReader socketReader = new BufferedReader(streamSocket);

            PrintWriter socketWriter =new PrintWriter( myClient.getOutputStream(), true);

            BufferedReader bufferInput = new BufferedReader(new InputStreamReader(System.in) );

            String sendMessage, receivedMessage="";

            System.out.println("Esperando mensaje...");

            while(!receivedMessage.equals("adios")){
                receivedMessage = socketReader.readLine();
                System.out.println("Cliente: " + receivedMessage );
                sendMessage = bufferInput.readLine();
                socketWriter.println(sendMessage);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
