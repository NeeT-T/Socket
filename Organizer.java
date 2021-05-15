import java.net.*;
import java.io.*;
import java.util.*;

public class Organizer {
    public static void main(String[] args) {
        try {
            String ip = (args.length > 0) ? args[0] : "";
            int port = (args.length > 1) ? Integer.parseInt(args[1]) : -1;
            String clientInput = (args.length > 2) ? args[2] : null;
            if (ip != "" && port >= 0 && port <= 65536) {
                Socket client = new Socket(ip, port);
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                DataInputStream input = new DataInputStream(client.getInputStream());
                if (clientInput.equalsIgnoreCase("Parcial")) {
                    output.writeUTF("Parcial");
                    System.out.println(input.readUTF());
                } else {
                    output.writeUTF("None");
                    System.out.println(input.readUTF());
                }
                client.close();
            } else {
                System.out.println("Erro na conexão com servidor verifique os parâmetros passados");
                return;
            }
        } catch (UnknownHostException e) {
            System.out.println("O host: " + e.getMessage() + " não é um ip valido");
        } catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
