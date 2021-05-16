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
                    System.out.println(getOutputToClient(input.readByte()));
                } else if (clientInput.equalsIgnoreCase("Votar")) {
                    output.writeUTF("Votar");
                    if (voto != null) {
                        System.out.println("# Votação para melhor atriz p* anã");
                        output.writeUTF(voto);
                        System.out.println(getOutputToClient(input.readByte()));
                    } else {
                        output.writeUTF("non");
                        System.out.println(getOutputToClient(input.readByte()));
                    }
                } else {
                    output.writeUTF("None");
                    System.out.println(getOutputToClient(input.readByte()));
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

    private static String getOutputToClient(byte indexToResponse) {
        switch (indexToResponse) {
            case 1:
                return "N° da candidata     Nome da Candidata\n[69]                Tiny Texie\n[345]               Bridget Powers\n[1725]              Mini garanhão\n" +
                "[8625]              Mini Mya\n[43125]             Tanya Tehanna\n";
            case 3:
                return "Voto computado para a candidata Tiny Texie";
            case 4:
                return "Voto computado para a candidata Bridget Powers";
            case 5:
                return "Voto computado para a candidata Mini garanhão";
            case 6:
                return "Voto computado para a candidata Mini Mya";
            case 7:
                return "Voto computado para a candidata Tanya Tehanna";
            case 8:
                return "Voto em candidata inexistente";
            case 9:
                return "Comando não encontrado";
        }
        return null;
    }
}