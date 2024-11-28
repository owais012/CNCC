package socket;

import java.net.*;
import java.io.*;

public class client {
    public static void main(String[] args) {
        try {

            System.out.println("Client Started");
            Socket soc = new Socket("localhost", 9806);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write your message here : ");
            String str = userInput.readLine();

            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
            out.println("message sent : " + str);

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String serverResponse = in.readLine();
            System.out.println("Response from the sever : " + serverResponse);
            
            soc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
