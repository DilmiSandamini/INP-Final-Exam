package lk.ijse.gdse72.commandexecutionsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client {
    public static void main (String [] args){
        try {
            Socket socket = new Socket("localhost", 4000);
            System.out.println("Connected to Server");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Client: ");

                String message = scanner.nextLine();

                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();

                if (message.equalsIgnoreCase("TIME")) {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    break;
                }

                if (message.equalsIgnoreCase("DATE")) {
                    System.out.println();
                    break;
                }

                if (message.equalsIgnoreCase("UPTIME")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                if (message.equalsIgnoreCase("BYE")) {
                    System.out.println("Disconnected from server.");
                    break;
                }
                String response = dataInputStream.readUTF();
                System.out.println("Server: " + response);
            }

            socket.close();
            scanner.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
