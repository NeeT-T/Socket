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
                    byte[] parcial = input.readAllBytes();
                    System.out.println("N° da candidata     Nome da Candidata     N° de Votos\n[69]                Tiny Texie            " + parcial[0] +
                    "\n[345]               Bridget Powers        " + parcial[1] + "\n[1725]              Mini garanhão         " + parcial[2] + "\n" +
                "[8625]              Mini Mya              " + parcial[3] +"\n[43125]             Tanya Tehanna         " + parcial[4] + "\n");
                } else {
                    output.writeUTF("None");
                    System.out.println(getOutputToOrganizer(input.readByte()));
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

    private static String getOutputToOrganizer(byte indexToResponse) {
        switch (indexToResponse) {
            case 9:
                return "Comando não encontrado";
        }
        return null;
    }
}
