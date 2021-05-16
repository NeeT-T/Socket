import java.net.*;
import java.text.*;
import java.util.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        final byte[] parcial = {0, 0, 0, 0, 0};
        try {
            String pattern = "dd/MM/yyyy HH:mm:ss";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            int port = (args.length > 0) ? Integer.parseInt(args[0]) : -1;
            if (port >= 0 && port <= 65536) {
                ServerSocket server = new ServerSocket(port);
                System.out.println("# Votação da melhor atriz p* anã");
                System.out.println("# O servidor está ativo. Ouvindo na porta " + port);
                while(true) {
                    System.out.println("# Esperando Conexão...");
                    Socket client = server.accept();
                    Date currentTime = Calendar.getInstance().getTime();
                    String timeToSaveInLog = dateFormat.format(currentTime);
                    System.out.println("Cliente: " + client.getInetAddress().getHostAddress() + " Conectado as: " + timeToSaveInLog);
                    DataOutputStream output = new DataOutputStream(client.getOutputStream());
                    DataInputStream input = new DataInputStream(client.getInputStream());
                    String inputClient = input.readUTF();
                    switch (inputClient) {
                        case "Listar":
                            output.writeByte(1);
                            break;
                        case "Parcial":
                            output.write(parcial);
                            break;
                        case "Votar":
                            switch (input.readUTF()) {
                                case "69":
                                    ++parcial[0];
                                    output.writeByte(3);
                                    break;
                                case "345":
                                    ++parcial[1];
                                    output.writeByte(4);
                                    break;
                                case "1725":
                                    ++parcial[2];
                                    output.writeByte(5);
                                    break;
                                case "8625":
                                    ++parcial[3];
                                    output.writeByte(6);
                                    break;
                                case "43125":
                                    ++parcial[4];
                                    output.writeByte(7);
                                    break;
                                default: 
                                    output.writeByte(8);
                            }
                            break;
                        default:
                            output.writeByte(9);
                    }
                    System.out.println("Conexão encerrada com: " + client.getInetAddress().getHostAddress());
                    input.close();
                    output.close();
                    client.close();
                }
            } else {
                System.out.println("Por segurança é preciso que você escolha qual porta o servidor usará: 'java Server porta'");
            }
        } catch(IOException e) {
            System.out.println("A porta que você tentou acessar já está em uso");
            // e.printStackTrace();
        }
    }
}