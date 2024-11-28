package socket;
import java.net.*;
import java.io.*;

public class server {
    public static void main(String[] args) {
        try {
            System.out.println("Waiting for client to connect ... ");
            ServerSocket ss = new ServerSocket(9806);
            
            Socket soc = ss.accept();
            System.out.println("Connection with client Established");

            BufferedReader input = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String message = input.readLine();
            System.out.println("Message Recieved from the client " + message);

            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println("Hey I am server, Recieved your message [ " + message + " ]" );
            soc.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
