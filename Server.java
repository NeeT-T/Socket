import java.net.*;
import java.text.*;
import java.util.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        final String candidates = "N° da candidata     Nome da Candidata\n[69]                Tiny Texie\n[345]               Bridget Powers\n[1725]              Mini garanhão\n" +
         "[8625]              Mini Mya\n[43125]             Tanya Tehanna\n";
        final int[] parcial = {0, 0, 0, 0, 0};
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
                            output.writeUTF(candidates);
                            break;
                        case "Parcial":
                            output.writeUTF("N° da candidata     Nome da Candidata     N° de Votos\n[69]                Tiny Texie            " + parcial[0] +
                            "\n[345]               Bridget Powers        " + parcial[1] + "\n[1725]              Mini garanhão         " + parcial[2] + "\n" +
                           "[8625]              Mini Mya              " + parcial[3] +"\n[43125]             Tanya Tehanna         " + parcial[4] + "\n");
                            break;
                        case "Votar":
                            switch (input.readUTF()) {
                                case "69":
                                    ++parcial[0];
                                    output.writeUTF("Voto computado para a candidata Tiny Texie");
                                    break;
                                case "345":
                                    ++parcial[1];
                                    output.writeUTF("Voto computado para a candidata Bridget Powers");
                                    break;
                                case "1725":
                                    ++parcial[2];
                                    output.writeUTF("Voto computado para a candidata Mini garanhão");
                                    break;
                                case "8625":
                                    ++parcial[3];
                                    output.writeUTF("Voto computado para a candidata Mini Mya");
                                    break;
                                case "43125":
                                    ++parcial[4];
                                    output.writeUTF("Voto computado para a candidata Tanya Tehanna");
                                    break;
                                default: 
                                    output.writeUTF("Voto em candidata inexistente");
                            }
                            break;
                        default:
                            output.writeUTF("Comando não encontrado");
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