import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            String ip = (args.length > 0) ? args[0] : "";
            int port = (args.length > 1) ? Integer.parseInt(args[1]) : -1;
            String clientInput = (args.length > 2) ? args[2] : "";
            String voto = (args.length > 3) ? args[3] : null;
            if (ip != "" && port >= 0 && port <= 65536) {
                Socket client = new Socket(ip, port);
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                DataInputStream input = new DataInputStream(client.getInputStream());
                if (clientInput.equalsIgnoreCase("Listar")) {
                    System.out.println("# Votação da melhor atriz p* anã");
                    output.writeUTF("Listar");
                    System.out.println(input.readUTF());
                } else if (clientInput.equalsIgnoreCase("Votar")) {
                    output.writeUTF("Votar");
                    if (voto != null) {
                        output.writeUTF(voto);
                        System.out.println("# Votação para melhor atriz p* anã");
                        System.out.println(input.readUTF());
                    } else {
                        output.writeUTF("non");
                        System.out.println(input.readUTF());
                    }
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